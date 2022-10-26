package libdirector.domain.mapper;

import libdirector.domain.entities.User;
import libdirector.domain.requestdto.RegisterRequest;
import libdirector.domain.requestdto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

	User registerRequestToUser(RegisterRequest userRegisterDTO);

	List<UserDTO>  map(List<User> user);

	UserDTO userToUserDTO(User user);
}
