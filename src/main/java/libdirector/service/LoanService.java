package libdirector.service;

import libdirector.domain.entities.Book;
import libdirector.domain.entities.Loan;
import libdirector.domain.entities.User;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.domain.responsedto.LoanResponseDTO;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;
import libdirector.repository.LoanRepository;
import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
@AllArgsConstructor
public class LoanService {

	private UserRepository userRepository;
	private BookRepository bookRepository;
	private LoanRepository loanRepository;



	//Loan Creating Method
	public Loan createLoan(@Valid LoanSaveDTO loanSaveDTO) {


		Loan loan = new Loan();

		Book book=bookRepository.findById(loanSaveDTO.getBookId().getId()).
				orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE, loanSaveDTO.getBookId())));
		User user=userRepository.findById(loanSaveDTO.getUserId().getId()).
				orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, loanSaveDTO.getUserId())));

		//Check to isn't returned books  in time
		checkNotReturnedBook(user);
		//Check User Score
		userScoreCheck(user);


		//Check to book is loanable
		IsLoanable(book);
		loan.setBookId(book);

		//Creating to LoanDate
		loan.setLoanDate(LocalDateTime.now());

		loan.setUserId(user);
		loan.setNotes(loanSaveDTO.getNotes());

		//Expire Date calculating and setting
		loan.setExpireDate(expiredDateSetting(loan.getUserId().getScore()));

		//Book Loanable false
		book.setLoanable(false);

		bookRepository.save(book);
		loanRepository.save(loan);
		return loan;
	}







	//Kleine Methods
	private void checkNotReturnedBook(User userLoan) {

		LocalDateTime today=LocalDateTime.now();
		Map<String,String> bookId=loanRepository.notReturnedInTime(userLoan.getId(),today);
		if (bookId.size()>0){
			throw new ResourceNotFoundException(String.format(ErrorMessage.BOOK_NOT_RETURNED_IN_TIME,bookId.values()));
		}
	}

	private LocalDateTime expiredDateSetting(Integer score) {
		LocalDateTime now = LocalDateTime.now();

		if (score == 0) {
			return ChronoUnit.DAYS.addTo(now, 10);
		} else if (score == 1) {
			return ChronoUnit.DAYS.addTo(now, 15);
		} else if (score == 2) {
			return ChronoUnit.DAYS.addTo(now, 20);
		} else if (score == -1) {
			return ChronoUnit.DAYS.addTo(now, 6);
		} else if (score == -2) {
			return ChronoUnit.DAYS.addTo(now, 3);
		} else {
			throw new ResourceNotFoundException(ErrorMessage.EXPIRE_DATE_CANT_CALCULATED_MESSAGE);
		}
	}

	private void userScoreCheck(User user) {
		int kS = user.getUserBooks().size();
		int uS=user.getScore();
		if (uS == 0 && kS >= 3) {
			kM(uS);
		} else if (uS == 1 && kS >= 4) {
			kM(uS);
		} else if (uS == 2 && kS >= 5) {
			kM(uS);
		} else if (uS == -1 && kS >= 2) {
			kM(uS);
		} else if (uS == -2 && kS >= 1) {
			kM(uS);
		}
	}
	void kM(Integer score){
		throw new ResourceNotFoundException(
				String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, score));
	}

	private void IsLoanable(Book loanedBooks) {

		if (!loanedBooks.getLoanable()) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.BOOK_NOT_AVAILABLE_MESSAGE, loanedBooks.getName()));
		}
	}

	public Loan updateLoan(LoanSaveDTO loanFinishDTO, Long id) {

		Loan loan=loanRepository.findById(id).orElseThrow(() -> new
				ResourceNotFoundException(String.format(ErrorMessage.LOAN_NOT_FOUND_MESSAGE, id)));

		User user=loan.getUserId();

		Book book=loan.getBookId();

		if (loanFinishDTO.getReturnDate()!=null) {
			//Double checker method
			//1.Checking return Date is After from Loan Date
			//2.Return date's effects on user's score and setting new User's score
			user.setScore(user.getScore()+returnDateAndUpdateScore(loanFinishDTO,loan));
			loan.setReturnDate(loanFinishDTO.getReturnDate());
			book.setLoanable(true);

			bookRepository.save(book);
			loanRepository.save(loan);
			userRepository.save(user);

			return loan;
		}else{
			loan.setNotes(loanFinishDTO.getNotes());
			loan.setExpireDate(loanFinishDTO.getExpireDate());

			loanRepository.save(loan);
			return loan;
		}
	}

	private Integer returnDateAndUpdateScore(LoanSaveDTO loanFinishDTO, Loan loan) {
		if(loanFinishDTO.getReturnDate().isBefore(loan.getLoanDate())){
			throw new RuntimeException(String.format(ErrorMessage.RETURN_TIME_INCORRECT_MESSAGE));
		}else if(loanFinishDTO.getReturnDate().isAfter(loan.getExpireDate())){
			return -1;
		}else
			return 1;
	}

	public LoanResponseDTO getUserLoanById(Long userId, Long loanId) {
		Loan loan=loanRepository.findById(loanId).orElseThrow(() -> new
				ResourceNotFoundException(String.format(ErrorMessage.LOAN_NOT_FOUND_MESSAGE, loanId)));
		if(!loan.getUserId().getId().equals(userId)){
			throw new RuntimeException(String.format(ErrorMessage.REQUEST_REJECTED,loanId,userId));
		}

		LoanResponseDTO loanReturnDTO=new LoanResponseDTO();
		loanReturnDTO.setLoanDate(loan.getLoanDate().toString());
		loanReturnDTO.setReturnDate(loan.getReturnDate().toString());
		loanReturnDTO.setBookId(loan.getBookId());
		loanReturnDTO.setExpireDate(loan.getExpireDate().toString());
		return loanReturnDTO;
	}

	public Loan getLoanById(Long loanId) {
		return loanRepository.findById(loanId).orElseThrow(() -> new
				ResourceNotFoundException(String.format(ErrorMessage.LOAN_NOT_FOUND_MESSAGE, loanId)));

	}


	public Page<LoanResponseDTO> getUserLoans(Pageable pageable,Long userId) {
		return loanRepository.getLoansByUserIdOrderByBookId(pageable,userId);

	}

	public Page<Loan> getUserLoansByAdmin(Pageable pageable, Long userId) {
		return loanRepository.getLoansByUserId(pageable,userId);
	}

	public Page<Book> getBookLoansByAdmin(Pageable pageable, Long bookId) {
		return loanRepository.getLoansByBookId(pageable,bookId);
	}
}
