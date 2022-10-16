package libdirector.dto.mapper;

import org.mapstruct.Mapper;

import libdirector.domain.Publisher;
import libdirector.domain.dto.PublisherDTO;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

	Publisher publisherDTOToPublisher(PublisherDTO publisherDTO);

}
