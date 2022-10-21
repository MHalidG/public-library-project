package libdirector.domain.entities;

import javax.persistence.*;

@Entity
public class CategorySequence {


        @Id
        @GeneratedValue(strategy = GenerationType.TABLE,
                generator = "table-generator")
        @TableGenerator(name = "table-generator",
                table = "tbl_category_sequence",
                pkColumnName = "category_id",
                valueColumnName = "sequence_value")
       private Integer sequence;


    }

