package libdirector.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.lang.annotation.Annotation;

@Entity
@Table(name="sequence_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySequence implements SequenceGenerator{


        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.TABLE,
                generator = "table-generator")
        @TableGenerator(name = "table-generator",
                table = "category_sequence_relations",
                pkColumnName = "category_id",
                valueColumnName = "sequence_value")
       private Integer sequence;

    @Override
    public String name() {
        return "";
    }

    @Override
    public String sequenceName() {
        return null;
    }

    @Override
    public String catalog() {
        return null;
    }

    @Override
    public String schema() {
        return null;
    }

    @Override
    public int initialValue() {
        return 0;
    }

    @Override
    public int allocationSize() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}

