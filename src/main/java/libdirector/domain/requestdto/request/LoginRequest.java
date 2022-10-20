package libdirector.domain.requestdto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@Email(message="Please Provide A Correct Email")
	private String email;
	
	@NotNull(message="Please Provide A Password")
	private String password;
	
	
}
