package libdirector.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String isbn;

	@Column
	private Integer pageCount;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "authorId", nullable = false)
	private Author authorId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "publisherId", nullable = false)
	private Publisher publisherId;

	@Column
	private Integer publishDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoryId", nullable = false)
	private Category categoryId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_image", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "imfile_id"))
	private Set<ImageFile> image;


	@Column(nullable = false)
	private Boolean loanable = true;

	@Column(nullable = false)
	private String shelfCode;

	@Column(nullable = false)
	private Boolean active = true;

	@Column(nullable = false)
	private Boolean featured = false;

	@Column(nullable = false)
	private LocalDateTime createDate;

	@Column(nullable = false)
	private Boolean builtIn = false;

	@JsonIgnore
	@OneToMany(mappedBy = "bookId")
	private List<Loan> loanedBooks = new ArrayList<>();

}