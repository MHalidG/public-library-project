package libdirector.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	@Column
	@GeneratedValue(SequenceGenerator.class)
	private Integer sequence;

	@OneToOne
	@JoinTable(name = "category_sequence_relations", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "sequence"))
	private CategorySequence sequences;

	@JsonIgnore
	@OneToMany(mappedBy = "categoryId")
	private List<Book> categoryBooks = new ArrayList<>();

}