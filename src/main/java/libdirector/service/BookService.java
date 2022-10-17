package libdirector.service;

import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.AuthorRepository;
import libdirector.repository.CategoryRepository;
import libdirector.repository.PublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import libdirector.domain.Book;
import libdirector.domain.dto.BookDTO;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class BookService {

	private AuthorRepository authorRepository;

	private PublisherRepository publisherRepository;

	private CategoryRepository categoryRepository;
	private BookRepository bookRepository;

	public Book saveBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setCreateDate(LocalDateTime.now());
		book.setBookAuthor(authorRepository.findById(bookDTO.getBookAuthor()).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE, bookDTO.getBookAuthor()))));

		book.setBookCategory(categoryRepository.findById(bookDTO.getBookCategory()).orElseThrow(() -> new ResourceNotFoundException(
				String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE, bookDTO.getBookCategory()))));

		book.setBookPublisher( publisherRepository.findById(bookDTO.getBookPublisher())
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(ErrorMessage.PUBLISHER_NOT_FOUND_MESSAGE, bookDTO.getBookPublisher()))));
		book.setIsbn(bookDTO.getIsbn());
		book.setPageCount(bookDTO.getPageCount());
		book.setName(bookDTO.getName());
		book.setShelfCode(bookDTO.getShelfCode());
		bookRepository.save(book);
		return book;
	}

	public Page<Book> findAllWithPage(Pageable pageable, Long cat, Long publisher, Long author, String query) {

		return bookRepository.findAllBookWithPage(pageable,cat,publisher,author,query);
		//return authorRepository.findAllBookWithPage(pageable,author,query);
		//return publisherRepository.findAllBookWithPage(pageable,publisher,query);
	}




/*
	public Author declareAuthor() {

		Author author = authorRepository.findById(bookAuthor).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE, bookAuthor)));

		return author;
	}

	public Publisher declarePublisher() {

		Publisher publisher = publisherRepository.findById(bookPublisher)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(ErrorMessage.PUBLISHER_NOT_FOUND_MESSAGE, bookPublisher)));

		return publisher;
	}

	public Category declareCategory() {

		Category category = categoryRepository.findById(bookCategory).orElseThrow(() -> new ResourceNotFoundException(
				String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE, bookCategory)));

		return category;
	}
	*/


}