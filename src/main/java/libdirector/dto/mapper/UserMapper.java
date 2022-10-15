package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.User;
import libdirector.dto.UserRegisterDTO;

@Mapper(componentModel="spring")
public interface UserMapper {

	User UserRegisterDTOToUser(UserRegisterDTO userRegisterDTO);
	
}
