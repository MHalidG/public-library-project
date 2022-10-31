package libdirector.controller;

import libdirector.domain.entities.Book;
import libdirector.domain.requestdto.BookSaveDTO;
import libdirector.service.BookService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;
//13-17 Requirement Document
//13 ve 16 da eksikler var

    //15
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> createBook(@Valid @RequestBody BookSaveDTO bookDTO)  {

        bookService.saveBook(bookDTO);

        Map<String,String> map=new HashMap<>();
        map.put("message", "Book Successfully Created");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }
    //13 Tamamlanmadi
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
    //14
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){

        Book book=bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    //17
    @DeleteMapping("{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long bookId){
        Book book=bookService.deleteBookById(bookId);
    return ResponseEntity.ok(book);
    }

    //16 Eksik Var ImageFile Map edilmedi
    @PutMapping("{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookSaveDTO bookDTO)  {

        Book book=bookService.updateBook(bookDTO,bookId);

        return ResponseEntity.ok(book);

    }


}
