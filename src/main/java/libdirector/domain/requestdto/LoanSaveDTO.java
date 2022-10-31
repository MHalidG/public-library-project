package libdirector.domain.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import libdirector.domain.entities.Book;
import libdirector.domain.entities.Loan;
import libdirector.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSaveDTO {

	@NotNull(message = "User can not null")
	private User userId;

	@NotNull(message = "Book can not null")
	private Book bookId;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",timezone= "Turkey")
	private LocalDateTime loanDate;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",timezone= "Turkey")
	private LocalDateTime returnDate;


	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",timezone= "Turkey")
	private LocalDateTime expireDate;

	@Size(max = 300)
	private String notes;


	LoanSaveDTO(Loan loan){
		this.bookId= loan.getBookId();
		this.expireDate=loan.getExpireDate();
		this.userId=loan.getUserId();
		this.returnDate=loan.getReturnDate();
		this.loanDate=loan.getLoanDate();
		
	}


}
