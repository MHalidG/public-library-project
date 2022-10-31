package libdirector.service;

import libdirector.domain.entities.Role;
import libdirector.domain.entities.User;
import libdirector.domain.enums.RoleType;
import libdirector.domain.mapper.UserMapper;
import libdirector.domain.requestdto.CategorySaveDTO;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.domain.requestdto.RegisterRequest;
import libdirector.domain.requestdto.UserDTO;
import libdirector.exception.ConflictException;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.LoanRepository;
import libdirector.repository.RoleRepository;
import libdirector.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class UserService {
	
	 private LoanRepository loanRepository;
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

public Page<User> getAllUsers(Pageable pageable){
		Page<User> users=userRepository.findAllUserPageable(pageable);

		 return users;
}


    public User getAuthUser(Long authUserId) {
		User authUser = userRepository.findById(authUserId).orElseThrow(()->new RuntimeException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE)));
		return authUser;
	}

	public Page<LoanSaveDTO> getAuthUserLoans(Pageable pageable, Long authUserId) {
		User authUser = userRepository.findById(authUserId).orElseThrow(()->new RuntimeException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE)));
		Page page=loanRepository.getLoansByUserIdOrderByExpireDate(pageable,authUserId);
		return page;
	}

	public User getUserById(Long id) {
		 return userRepository.findById(id).orElseThrow(()->new RuntimeException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE)));
	}

	public User createSuperUser(User user) {
		 return userRepository.save(user);
	}
/*
	public User createMemberUser(User user) {
		Role role=new Role();
		role.setName(RoleType.ROLE_MEMBER);

		}*/
}
