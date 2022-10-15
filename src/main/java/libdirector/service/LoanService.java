package libdirector.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.domain.Loan;
import libdirector.domain.User;
import libdirector.dto.LoanDTO;
import libdirector.dto.mapper.LoanMapper;
import libdirector.exception.BadRequestException;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;
import libdirector.repository.LoanRepository;

@Service
public class LoanService {

	private BookRepository bookRepository;
	private LoanMapper loanMapper;
	private LoanRepository loanRepository;

	public Loan createLoan(LoanDTO loanDTO) {

		Loan loan = loanMapper.loanDTOToLoan(loanDTO);

		checkLoanTimeIsCorrect(loan.getLoanDate());

		bookIsLoanable(loan.getLoanedBooks());

		userScoreCheck(loan.getUserLoan());

		loan.setExpireDate(expiredDateSetting(loan.getUserLoan().getScore()));

		Book book = bookRepository.findById(loan.getLoanedBooks().getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE, loan.getLoanedBooks().getId())));
		book.setLoanable(false);
		bookRepository.save(book);
		
		loan.setLoanDate(LocalDateTime.now());
		loanRepository.save(loan);
		return loan;
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
		int kitapSayisi = user.getUserBooks().size();

		if (user.getScore() == 0 && kitapSayisi >= 3) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, user.getScore()));
		} else if (user.getScore() == 1 && kitapSayisi >= 4) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, user.getScore()));
		} else if (user.getScore() == 2 && kitapSayisi >= 5) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, user.getScore()));
		} else if (user.getScore() == -1 && kitapSayisi >= 2) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, user.getScore()));
		} else if (user.getScore() == -2 && kitapSayisi >= 1) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.USER_SCORE_NOT_ENOUGH_MESSAGE, user.getScore()));
		}
	}

	private void bookIsLoanable(Book loanedBooks) {

		if (!loanedBooks.getLoanable()) {
			throw new ResourceNotFoundException(
					String.format(ErrorMessage.BOOK_NOT_AVAILABLE_MESSAGE, loanedBooks.getName()));
		}

	}

	private void checkLoanTimeIsCorrect(LocalDateTime loanDate) {
		LocalDateTime now = LocalDateTime.now();
		boolean isBefore = loanDate.isBefore(now) ? true : false;
		if (!isBefore) {
			throw new BadRequestException(ErrorMessage.LOAN_TIME_INCORRECT_MESSAGE);
		}
	}

}
