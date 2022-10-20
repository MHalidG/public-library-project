package libdirector.domain.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {


    private Long id;

    @NotNull(message="Please provide role name")
    @Size(max=12,min=3)
    private String name;


}
