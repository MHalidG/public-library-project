package libdirector.domain.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.entities.Publisher;
import libdirector.domain.requestdto.PublisherSaveDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-05T20:36:17+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher publisherSaveDTOToPublisher(PublisherSaveDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher publisher = new Publisher();

        publisher.setId( publisherDTO.getId() );
        publisher.setName( publisherDTO.getName() );
        publisher.setBuiltIn( publisherDTO.getBuiltIn() );

        return publisher;
    }
}
