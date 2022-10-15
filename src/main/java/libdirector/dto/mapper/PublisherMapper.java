package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.Publisher;
import libdirector.dto.PublisherDTO;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

	Publisher publisherDTOToPublisher(PublisherDTO publisherDTO);

}
