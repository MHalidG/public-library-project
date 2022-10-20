package libdirector.domain.requestdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

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



}
