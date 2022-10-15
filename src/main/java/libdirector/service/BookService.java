package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.dto.BookDTO;
import libdirector.dto.mapper.BookMapper;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

	private BookRepository bookRepository;
	private BookMapper bookMapper;

	public Book saveBook(BookDTO bookDTO) {
		Book book = bookMapper.bookDTOToBook(bookDTO);
		bookRepository.save(book);
		return book;
	}

}