package libdirector.domain.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;

@Mapper(componentModel="spring")
public interface CategoryMapper {

	Category CategoryDTOToCategory(CategorySaveDTO categoryDTO);
	
}
