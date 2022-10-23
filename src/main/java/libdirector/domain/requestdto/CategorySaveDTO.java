package libdirector.domain.requestdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import libdirector.domain.entities.Category;
import libdirector.domain.entities.CategorySequence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategorySaveDTO {

	private Long id;

	@NotNull(message = "Please Provide Category Name")
	@Size(min = 2, max = 80, message = "Category Name '${validatedValue}' must be between {min} and {max} chars long")
	private String name;

	private CategorySequence sequence;

	@NotNull(message = "Please provide BuiltIn")
	private Boolean builtIn = false;


	public CategorySaveDTO(Category category){
		this.id=category.getId();
		this.name= category.getName();
		this.sequence= category.getSequence();
	}
}
