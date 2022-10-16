package libdirector.dto.mapper;

import libdirector.domain.dto.UserDTO;
import org.mapstruct.Mapper;

import libdirector.domain.User;
import libdirector.domain.dto.request.RegisterRequest;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

	User registerRequestToUser(RegisterRequest userRegisterDTO);

	List<UserDTO>  map(List<User> user);

	UserDTO userToUserDTO(User user);
}
