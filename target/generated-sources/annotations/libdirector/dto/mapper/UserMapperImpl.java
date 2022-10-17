package libdirector.dto.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import libdirector.domain.Role;
import libdirector.domain.User;
import libdirector.domain.dto.UserDTO;
import libdirector.domain.dto.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-16T22:25:52+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User registerRequestToUser(RegisterRequest userRegisterDTO) {
        if ( userRegisterDTO == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( userRegisterDTO.getFirstName() );
        user.setLastName( userRegisterDTO.getLastName() );
        user.setAddress( userRegisterDTO.getAddress() );
        user.setPhone( userRegisterDTO.getPhone() );
        user.setBirthDate( userRegisterDTO.getBirthDate() );
        user.setEmail( userRegisterDTO.getEmail() );
        user.setPassword( userRegisterDTO.getPassword() );
        user.setBuiltIn( userRegisterDTO.getBuiltIn() );

        return user;
    }

    @Override
    public List<UserDTO> map(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( user.size() );
        for ( User user1 : user ) {
            list.add( userToUserDTO( user1 ) );
        }

        return list;
    }

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        if ( user.getScore() != null ) {
            userDTO.setScore( user.getScore() );
        }
        userDTO.setAddress( user.getAddress() );
        userDTO.setPhone( user.getPhone() );
        if ( user.getBirthDate() != null ) {
            userDTO.setBirthDate( LocalDateTime.ofInstant( user.getBirthDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        userDTO.setEmail( user.getEmail() );
        userDTO.setCreateDate( user.getCreateDate() );
        userDTO.setResetPasswordCode( user.getResetPasswordCode() );
        userDTO.setBuiltIn( user.getBuiltIn() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDTO.setRoles( new HashSet<Role>( set ) );
        }

        return userDTO;
    }
}