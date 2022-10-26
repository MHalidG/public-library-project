package libdirector.domain.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSaveDTO {


	private Long id;

	@Size(min = 2, max = 80, message = "Size is exceeded")
	@NotNull(message = "Please provide book name")
	private String name;

	@NotNull(message = "Please provide isbn")
	@Size(max = 17)
	@Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d$", message = "Please provide valid isbn number")
	private String isbn;

	private Integer pageCount;

	@NotNull(message = "Please provide a Author id")
	private Long authorId;

	@NotNull(message = "Please provide a Publichser id")
	private Long publisherId;

	@Pattern(regexp = "^\\d{4}", message="(Just enter the year it was published)")
	private Integer publishDate;

	@NotNull(message = "Please provide A Category id")
	private Long categoryId;

	private Boolean loanable=true;

	private Boolean active=true;

	private Boolean featured=false;

	private Set<String> image;

	@NotNull(message = "Please provide shelf Code")
	@Size(min = 6, max = 6)
	@Pattern(regexp = "^[A-Z]{2}-\\d{3}$", message = "Please provide a valid shelf Code")
	private String shelfCode;

	private Boolean builtIn=false;



}
