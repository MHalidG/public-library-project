package libdirector.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

	private Long id;

	@NotNull(message = "Please Provide Category Name")
	@Size(min = 2, max = 80, message = "Category Name '${validatedValue}' must be between {min} and {max} chars long")
	private String name;

	@NotNull(message = "Please provide BuiltIn")
	private Boolean builtIn = false;

	private Integer sequence;

}
