package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.Category;
import libdirector.domain.dto.CategoryDTO;

@Mapper(componentModel="spring")
public interface CategoryMapper {

	Category CategoryDTOToCategory(CategoryDTO categoryDTO);
	
}
