package libdirector.controller;

import javax.validation.Valid;

import libdirector.domain.requestdto.AuthorSaveDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import libdirector.domain.entities.Author;
import libdirector.service.AuthorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody AuthorSaveDTO authorDTO) {

        return new ResponseEntity<>(authorService.saveAuthor(authorDTO),HttpStatus.CREATED);

    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<>
*/


}