package libdirector.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.dto.BookDTO;
import libdirector.repository.AuthorRepository;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {


    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;


    public Book saveBook(BookDTO bookDTO) {


        Book book = new Book();

        book.setName(bookDTO.getName());
        book.setIsbn(bookDTO.getIsbn());
        book.setPageCount(bookDTO.getPageCount());
        book.setPublishDate(bookDTO.getPublishDate());

        book.setBookAuthor(authorService.getAuthorById(bookDTO.getBookAuthor()));
        book.setBookPublisher(publisherService.getPublisherById(bookDTO.getBookPublisher()));
        book.setBookCategory(categoryService.getCategoryById(bookDTO.getBookCategory()));

        book.setLoanable(true);
        book.setShelfCode(bookDTO.getShelfCode());
        book.setActive(true);
        book.setFeatured(bookDTO.getFeatured());

        LocalDateTime today = LocalDateTime.now();

        book.setCreateDate(today);

        book.setBuiltIn(false);


        bookRepository.save(book);

        return book;

    }


}