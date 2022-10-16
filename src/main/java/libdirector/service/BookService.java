package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.domain.dto.BookDTO;
import libdirector.dto.mapper.BookMapper;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookService {

	private BookRepository bookRepository;
	private BookMapper bookMapper;

	public Book saveBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setCreateDate(LocalDateTime.now());
		book.setBookAuthor(bookDTO.getBookAuthor());
		book.setBookCategory(bookDTO.getBookCategory());
		book.setBookPublisher(bookDTO.getBookPublisher());
		book.setIsbn(bookDTO.getIsbn());
		book.setPageCount(bookDTO.getPageCount());
		book.setName(bookDTO.getName());
		book.setShelfCode(bookDTO.getShelfCode());
		bookRepository.save(book);
		return book;
	}

}