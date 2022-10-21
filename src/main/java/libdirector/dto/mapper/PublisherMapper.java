package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.entities.Publisher;
import libdirector.domain.requestdto.PublisherSaveDTO;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

	Publisher publisherDTOToPublisher(PublisherSaveDTO publisherDTO);

}
