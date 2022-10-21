package libdirector.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tbl_categories")
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, length = 80)
	private String name;

	@Column(nullable = false)
	private Boolean builtIn = false;

	@Column(nullable = false)
	@OneToOne
	@JoinTable(name = "tbl_category_sequence", joinColumns = @JoinColumn(name = "category_sequence"), inverseJoinColumns = @JoinColumn(name = "sequence"))
	private CategorySequence sequence;

	@JsonIgnore
	@OneToMany(mappedBy = "categoryId")
	private List<Book> categoryBooks = new ArrayList<>();

}