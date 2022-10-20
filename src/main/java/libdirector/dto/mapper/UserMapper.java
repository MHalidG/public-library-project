package libdirector.dto.mapper;

import libdirector.domain.requestdto.UserDTO;
import org.mapstruct.Mapper;

import libdirector.domain.entities.User;
import libdirector.domain.requestdto.request.RegisterRequest;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

	User registerRequestToUser(RegisterRequest userRegisterDTO);

	List<UserDTO>  map(List<User> user);

	UserDTO userToUserDTO(User user);
}
