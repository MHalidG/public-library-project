package libdirector.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import libdirector.domain.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import libdirector.domain.requestdto.BookDTO;
import libdirector.service.BookService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> createBook(@Valid @RequestBody BookDTO bookDTO)  {

        bookService.saveBook(bookDTO);

        Map<String,String> map=new HashMap<>();
        map.put("message", "Book Successfully Created");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllWithPage(@RequestParam String query,
                                                     @RequestParam("cat") Long cat,
                                                     @RequestParam("author") Long author,
                                                     @RequestParam("publisher") Long publisher,
                                                     @RequestParam("sort") String prop,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("direction") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(size,page,direction,prop);
        Page<Book> bookPage = bookService.findAllWithPage(pageable,cat,publisher,author,query);

        return ResponseEntity.ok(bookPage);
    }




}
