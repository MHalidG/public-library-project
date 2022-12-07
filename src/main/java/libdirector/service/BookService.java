package libdirector.service;

import libdirector.domain.entities.Book;
import libdirector.domain.entities.Publisher;
import libdirector.domain.requestdto.BookSaveDTO;
import libdirector.exception.ResourceNotFoundException;
import libdirector.exception.message.ErrorMessage;
import libdirector.repository.AuthorRepository;
import libdirector.repository.BookRepository;
import libdirector.repository.CategoryRepository;
import libdirector.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BookService {

	private PublisherService publisherService;
	private AuthorRepository authorRepository;

	private PublisherRepository publisherRepository;

	private CategoryRepository categoryRepository;
	private BookRepository bookRepository;

	public Book saveBook(BookSaveDTO bookDTO) {
		Book book = new Book();
		book.setCreateDate(LocalDateTime.now());
		book.setAuthorId(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE, bookDTO.getAuthorId()))));

		book.setCategoryId(categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(
				String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE, bookDTO.getCategoryId()))));

		book.setPublisherId( publisherRepository.findById(bookDTO.getPublisherId())
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(ErrorMessage.PUBLISHER_NOT_FOUND_MESSAGE, bookDTO.getPublisherId()))));
		book.setIsbn(bookDTO.getIsbn());
		book.setPageCount(bookDTO.getPageCount());
		book.setName(bookDTO.getName());
		book.setShelfCode(bookDTO.getShelfCode());
		bookRepository.save(book);
		return book;
	}
/*
	public void /*Page<Book> findAllWithPage(Pageable pageable, Long cat, Long publisher, Long author, String query) {


		//return bookRepository.findAllBookWithPage(pageable,cat,publisher,author,query);

		//return authorRepository.findAllBookWithPage(pageable,author,query);
		//return publisherRepository.findAllBookWithPage(pageable,publisher,query);
	}
*/
    public Book getBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE)));
    }

	public Book deleteBookById(Long bookId) {
		Book book=bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE)));
		if (!book.getLoanable()){
			throw new RuntimeException(String.format(ErrorMessage.BOOK_USED_BY_LOAN_MESSAGE));
		}else bookRepository.deleteById(bookId);

	return book;
	}

	public Book updateBook(BookSaveDTO bookDTO, Long bookId) {
		Book book=bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.BOOK_NOT_FOUND_MESSAGE)));

		book.setPublisherId(publisherRepository.findById(bookDTO.getPublisherId()).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.PUBLISHER_NOT_FOUND_MESSAGE))));
		book.setLoanable(bookDTO.getLoanable());
		book.setAuthorId(authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.AUTHOR_NOT_FOUND_MESSAGE))));
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setCategoryId(categoryRepository.findById(bookDTO.getCategoryId()).orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE))));
		book.setPageCount(bookDTO.getPageCount());
		book.setShelfCode(bookDTO.getShelfCode());
		book.setActive(bookDTO.getActive());
		//book.setImage(bookDTO.getImage());
		book.setPublishDate(bookDTO.getPublishDate());
		book.setFeatured(bookDTO.getFeatured());
		bookRepository.save(book);
		return book;
	}
}