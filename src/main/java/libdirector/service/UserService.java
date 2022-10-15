package libdirector.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import libdirector.domain.Role;
import libdirector.domain.User;
import libdirector.domain.enums.RoleType;
import libdirector.dto.UserRegisterDTO;
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
	 
	 public void register(UserRegisterDTO userRegDTO) {
     if(userRepository.existsByEmail(userRegDTO.getEmail())) {
    	 throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST, userRegDTO.getEmail()));
     }
		 
     
     String encodedPass=passwordEncoder.encode(userRegDTO.getPassword());
		 
	Role role=roleRepository.findByName(RoleType.ROLE_MEMBER).orElseThrow(()->new 
	ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_MEMBER.name())));
	
	Set<Role> roles=new HashSet<>();
	roles.add(role);
	User user=userMapper.UserRegisterDTOToUser(userRegDTO);
	user.setPassword(encodedPass);
	user.setRoles(roles);
	
	userRepository.save(user);
	
}

}
