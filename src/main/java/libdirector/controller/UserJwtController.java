package libdirector.controller;

import libdirector.domain.requestdto.LoginRequest;
import libdirector.domain.requestdto.RegisterRequest;
import libdirector.domain.responsedto.LibResponse;
import libdirector.domain.responsedto.LoginResponse;
import libdirector.domain.responsedto.ResponseMessage;
import libdirector.security.jwt.JwtUtils;
import libdirector.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJwtController {

	private AuthenticationManager authManager;
	private UserService userService;
	private JwtUtils jwtUtils;
	
	//Spring containerin conteynirina iki turlu yapi yerlestirebiliyoruz
	//1-Component Anotasyonu
	//2-Bean olusturarak

	//46
	@PostMapping("/register")
	public ResponseEntity<LibResponse> register(@Valid @RequestBody RegisterRequest registerRequest){


		userService.register(registerRequest);
		
		LibResponse response=new LibResponse();
		response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	//45
	@PostMapping("/singnin")
	public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest){
				Authentication authentication=authManager.authenticate(new
				UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
	String token=jwtUtils.generateJwtToken(authentication);
	
	LoginResponse response=new LoginResponse();
	response.setToken(token);
	
	return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
				
}
