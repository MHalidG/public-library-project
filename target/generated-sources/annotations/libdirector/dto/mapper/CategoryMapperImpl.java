package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Category;
import libdirector.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-15T04:20:02+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category CategoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setBuiltIn( categoryDTO.getBuiltIn() );
        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setSequence( categoryDTO.getSequence() );

        return category;
    }
}
