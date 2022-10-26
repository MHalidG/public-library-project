package libdirector.domain.mapper;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CategoryMapper {

	Category categorySaveDTOToCategory(CategorySaveDTO categoryDTO);
	
}
