/*package libdirector.controller;

import libdirector.domain.entities.Role;
import libdirector.domain.dto.RoleDTO;
import libdirector.repository.RoleRepository;
import libdirector.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController{

	private RoleRepository roleRepository;
	private RoleService roleService;


	@PostMapping
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> createRole(@Valid @RequestBody RoleDTO roleDTO){
		String role=roleService.saveRole(roleDTO);
		return ResponseEntity.ok(role);
	}
	
}
*/