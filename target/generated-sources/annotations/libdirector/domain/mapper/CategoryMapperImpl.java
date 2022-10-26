package libdirector.domain.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-26T00:42:55+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category categorySaveDTOToCategory(CategorySaveDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setBuiltIn( categoryDTO.getBuiltIn() );
        category.setSequence( categoryDTO.getSequence() );

        return category;
    }
}
