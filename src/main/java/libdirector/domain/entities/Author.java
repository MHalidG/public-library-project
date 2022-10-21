package libdirector.domain.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_authors")
public class Author {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//Defaultu AUTOdur
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean builtIn=false;
    @JsonIgnore
    @OneToMany(mappedBy = "authorId")
    private List<Book> authorBooks=new ArrayList<>();

	
}