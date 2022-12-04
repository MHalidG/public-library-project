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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 80)
	private String name;

	@Column(nullable = false)
	private Boolean builtIn = false;

	@Column
	private Integer sequence;

	@JsonIgnore
	@OneToMany(mappedBy = "categoryId")
	private List<Book> categoryBooks = new ArrayList<>();

}