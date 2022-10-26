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