package libdirector.controller;

import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorSaveDTO;
import libdirector.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;

    //36
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorSaveDTO authorSaveDTO){


        return new ResponseEntity<>(authorService.updateAuthor(authorSaveDTO,id),HttpStatus.CREATED);
    }

    //35
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody AuthorSaveDTO authorSaveDTO) {

        return new ResponseEntity<>(authorService.saveAuthor(authorSaveDTO),HttpStatus.CREATED);

    }

    //34
    @GetMapping("/{id}")
    public ResponseEntity<AuthorSaveDTO> getAuthorById(@PathVariable Long id,AuthorSaveDTO authorSaveDTO){
        authorService.getAuthorById(id,authorSaveDTO);


        return new ResponseEntity<>(authorService.getAuthorById(id,authorSaveDTO),HttpStatus.OK);

    }

    //37
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id){

        return new ResponseEntity<>(authorService.deleteAuthor(id),HttpStatus.OK);
    }

    //33
    @GetMapping
    public ResponseEntity<Page<AuthorSaveDTO>> getAllAuthorsByPage(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(required = false,value = "size", defaultValue = "20") int size,
                                                                   @RequestParam(required = false,value = "sort", defaultValue = "name") String prop,
                                                                   @RequestParam(required = false,value = "type", defaultValue = "ASC") Sort.Direction direction
    ){
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<AuthorSaveDTO> authorDTOPage = authorService.getAuthorsPage(pageable);
        return ResponseEntity.ok(authorDTOPage);
    }

}