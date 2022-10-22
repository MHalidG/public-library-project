package libdirector.domain.requestdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSaveDTO {

	@NotNull(message = "User can not null")
	private Long userId;

	@NotNull(message = "Book can not null")
	private Long bookId;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",timezone= "Turkey")
	private LocalDateTime returnDate;

	@Size(max = 300)
	private String notes;



}
