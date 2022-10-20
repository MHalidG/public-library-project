package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategoryDTO;

@Mapper(componentModel="spring")
public interface CategoryMapper {

	Category CategoryDTOToCategory(CategoryDTO categoryDTO);
	
}
