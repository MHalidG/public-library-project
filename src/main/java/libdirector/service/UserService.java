package libdirector.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import libdirector.domain.dto.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import libdirector.domain.Role;
import libdirector.domain.User;
import libdirector.domain.dto.request.RegisterRequest;
import libdirector.domain.enums.RoleType;
import libdirector.dto.mapper.UserMapper;
import libdirector.exception.ConflictException;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.RoleRepository;
import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserService {
	
	
	 private UserRepository userRepository;
	 private RoleRepository roleRepository;	 
	 private PasswordEncoder passwordEncoder;
	 private UserMapper userMapper;
	 
	 public void register(RegisterRequest registerRequest) {
     if(userRepository.existsByEmail(registerRequest.getEmail())) {
    	 throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, registerRequest.getEmail()));
     }
		 
     
     String encodedPassword=passwordEncoder.encode(registerRequest.getPassword());
		 
	Role role=roleRepository.findByName(RoleType.ROLE_MEMBER).orElseThrow(()->new 
	ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_MEMBER.name())));
	
	Set<Role> roles=new HashSet<>();
	roles.add(role);
	User user=new User();
	user.setFirstName(registerRequest.getFirstName());
	user.setLastName(registerRequest.getLastName());
	user.setEmail(registerRequest.getEmail());
	user.setAddress(registerRequest.getAddress());
	user.setBirthDate(registerRequest.getBirthDate());
	user.setBuiltIn(registerRequest.getBuiltIn());
	user.setPassword(encodedPassword);
	user.setPhone(registerRequest.getPhone());
	user.setRoles(roles);
	user.setCreateDate(LocalDateTime.now());
	
	userRepository.save(user);
	
}

public List<UserDTO> getAllUsers(){
		List<User> users=userRepository.findAll();

		List<UserDTO> usersDTO=new ArrayList<>();
	for (User user:users
		 ) {
		UserDTO userDTO=userMapper.userToUserDTO(user);
		usersDTO.add(userDTO);
	}
		 return usersDTO;
}


}
