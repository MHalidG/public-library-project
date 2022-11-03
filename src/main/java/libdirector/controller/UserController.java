package libdirector.controller;

import libdirector.domain.entities.User;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    //49
    @GetMapping("s")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Page<User>> getAllUsers( @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                   @RequestParam(required = false,value = "size", defaultValue = "20") int size,
                                                   @RequestParam(required = false,value = "sort", defaultValue = "createDate") String prop,
                                                   @RequestParam(required = false,value = "type", defaultValue = "DESC") Sort.Direction direction
    ){
        Pageable pageable=PageRequest.of(page,size,Sort.by(direction,prop));
        Page<User> users=userService.getAllUsers(pageable);

        return ResponseEntity.ok(users);

    }
    //47
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
    public ResponseEntity<User> getAuthenticatedUser(HttpServletRequest request){
        Long authUserId= (Long) request.getAttribute("id");
        User authUser=userService.getAuthUser(authUserId);
        return ResponseEntity.ok(authUser);
    }

    //48
    @GetMapping("/loans")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF') or hasRole('MEMBER')")
    public ResponseEntity<Page<LoanSaveDTO>> getLoansAuthenticatedUser(HttpServletRequest request,
                                                      @RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                      @RequestParam(required = false,value = "size", defaultValue = "20") int size,
                                                      @RequestParam(required = false,value = "sort", defaultValue = "createDate") String prop,
                                                      @RequestParam(required = false,value = "type", defaultValue = "DESC") Sort.Direction direction){
        Long authUserId= (Long) request.getAttribute("id");
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<LoanSaveDTO> loanDTOPage = userService.getAuthUserLoans(pageable,authUserId);
        return  ResponseEntity.ok(loanDTOPage);
    }

    //50
    @GetMapping("s/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);

        return ResponseEntity.ok(user);

    }

    //51
    @PostMapping("s")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createSuperUser(@RequestBody @Valid User user){
        userService.createSuperUser(user);
        return ResponseEntity.ok(user);

        //Role Set mekanizmasini ayarlamam lazim sonraki method icin de gerekli
        //UserRole Mekanizmalarini ve Repo queryleri coz
    }
    //52
    /*
    @PostMapping("s")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<User> createMemberUser(@RequestBody @Valid User user){
        userService.createMemberUser(user);
        return ResponseEntity.ok(user);
    }*/


    //52


}
