package libdirector.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.domain.Loan;
import libdirector.domain.User;
import libdirector.domain.dto.LoanDTO;
import libdirector.dto.mapper.LoanMapper;
import libdirector.exception.BadRequestException;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;
import libdirector.repository.LoanRepository;

@Service
@AllArgsConstructor
public class LoanService {

	private UserRepository userRepository;
	private BookRepository bookRepository;
	private LoanRepository loanRepository;

	public Loan createLoan(LoanDTO loanDTO) {


		Loan loan = new Loan();

		Book book=bookRepository.findById(loanDTO.getLoanedBooks()).
				orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE, loanDTO.getLoanedBooks())));
		User user=userRepository.findById(loanDTO.getUserLoan()).
				orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, loanDTO.getUserLoan())));


		IsLoanable(book);
		loan.setLoanedBooks(book);

		checkNotReturnedBook(user);
		userScoreCheck(user);

		loan.setLoanDate(LocalDateTime.now());
		loan.setUserLoan(user);
		loan.setNotes(loanDTO.getNotes());
		loan.setExpireDate(expiredDateSetting(loan.getUserLoan().getScore()));

		book.setLoanable(false);
		bookRepository.save(book);

		loanRepository.save(loan);

		return loan;
	}

	private void checkNotReturnedBook(User userLoan) {

		/*
		Girdiler
		userin Id gidecek

		Filtreler
		son teslim saati gecmis ama today iade edilmemis kitap var mi?

		 */
		LocalDateTime today=LocalDateTime.now();
		Map<String,String> bookId=loanRepository.notReturnedInTimeJetz(userLoan.getId(),today);
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
		}else if(uS == -2 && kS >= 1){
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
/*
	private void checkLoanTimeIsCorrect(LocalDateTime loanDate) {
		LocalDateTime now = LocalDateTime.now();
		boolean isBefore = loanDate.isBefore(now) ? true : false;
		if (!isBefore) {
			throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
		}
	}

	private User getUserLoan() {

		User user = userRepository.findById(userLoan).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, userLoan)));

		return user;
	}

	private Book getLoanedBooks() {

		Book book = bookRepository.findById(loanedBooks).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE, loanedBooks)));

		return book;
	}

*/

}
