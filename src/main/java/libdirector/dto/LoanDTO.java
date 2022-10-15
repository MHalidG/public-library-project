package libdirector.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import libdirector.domain.Book;
import libdirector.domain.User;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.BookRepository;
import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;

	@NotNull(message = "User can not null")
	private Long userLoan;

	@NotNull(message = "Book can not null")
	private Long loanedBooks;
	/*
	 * @NotNull(message= "Please provide loan date")
	 * 
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",
	 * timezone= "Turkey") private LocalDateTime loanDate;
	 * 
	 * @NotNull(message= "Please provide expire date")
	 * 
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",
	 * timezone= "Turkey") private LocalDateTime expireDate;
	 * 
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",
	 * timezone= "Turkey") private LocalDateTime returnDate;
	 */
	@Size(max = 300)
	private String notes;

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

}
