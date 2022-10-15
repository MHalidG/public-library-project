package libdirector.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import libdirector.domain.Book;
import libdirector.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
	
	private Long id;

	@NotNull(message = "Please provide Author Name")
	@Size(min = 4, max = 70, message = "Author Name '${validatedValue}' must be between {min} and {max} chars long")
	private String name;

	@NotNull(message = "Please provide BuiltIn")
	private Boolean builtIn = false;
/*
	private List<String> authorBooks;
	
	private void setAuthorBooks(List<Book> books) {
		List<Long> booksId=books.stream().map(z -> z.getId())
				.collect(Collectors.toList());
		
		List<String> authorBooks= new ArrayList<>();
		for (Long Ids: booksId) {
			
			name=bookRepository.findById(Ids).get().getName();
			authorBooks.add(name);
		}

		
	}
	

	public AuthorDTO(Author author) {

		this.id = author.getId();
		this.name = author.getName();
		this.builtIn = author.getBuiltIn();
		this.authorBooks = getBooks(author.getAuthorBooks());

	}

	public List<String> getBooks(List<Book> authorBooks) {
		List<String> bookStrSet = authorBooks.stream().map(z -> z.getId().toString())
				.collect(Collectors.toList());
		return bookStrSet;
	}*/

}
