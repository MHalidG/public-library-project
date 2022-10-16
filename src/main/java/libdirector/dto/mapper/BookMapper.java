package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.Book;
import libdirector.domain.dto.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {

	Book bookDTOToBook(BookDTO bookDTO);

}
