package libdirector.domain.mapper;

import libdirector.domain.entities.Publisher;
import libdirector.domain.requestdto.PublisherSaveDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

	Publisher publisherSaveDTOToPublisher(PublisherSaveDTO publisherDTO);

}
