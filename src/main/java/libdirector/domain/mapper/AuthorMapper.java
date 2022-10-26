package libdirector.domain.mapper;

import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorSaveDTO;
import org.mapstruct.Mapper;
//@Mapper
@Mapper(componentModel="spring")
public interface AuthorMapper {

	//AuthorMapper INSTANCE=Mappers.getMapper(AuthorMapper.class);
	
	Author authorSaveDTOToAuthor(AuthorSaveDTO authorDTO);
	
}
