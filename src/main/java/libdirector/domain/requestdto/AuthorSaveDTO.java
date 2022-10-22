package libdirector.domain.requestdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import libdirector.domain.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveDTO {

    private Long id;

    @NotNull(message = "Please provide Author Name")
    @Size(min = 4, max = 70, message = "Author Name '${validatedValue}' must be between {min} and {max} chars long")
    private String name;

    @NotNull(message = "Please provide BuiltIn")
    private Boolean builtIn = false;

    public AuthorSaveDTO(Author author){
        this.id= author.getId();
        this.name= author.getName();
    }



}
