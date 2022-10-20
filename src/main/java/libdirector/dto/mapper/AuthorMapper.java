package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorDTO;
//@Mapper
@Mapper(componentModel="spring")
public interface AuthorMapper {

	//AuthorMapper INSTANCE=Mappers.getMapper(AuthorMapper.class);
	
	Author authorDTOToAuthor(AuthorDTO authorDTO);
	
}
