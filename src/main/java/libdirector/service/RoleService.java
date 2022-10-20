/*package libdirector.service;

import libdirector.domain.entities.Role;
import libdirector.domain.dto.RoleDTO;
import libdirector.domain.enums.RoleType;
import libdirector.repository.RoleRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.hibernate.type.EnumType;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.EnumSet;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;
    public Role saveRole(@Valid RoleDTO roleDTO) {

        String role="ROLE_"+roleDTO.getName().toUpperCase();


    return roleRepository.save(role.);
    }
}
*/