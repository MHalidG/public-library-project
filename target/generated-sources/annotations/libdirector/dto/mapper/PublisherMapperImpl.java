package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Publisher;
import libdirector.domain.dto.PublisherDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-16T22:25:52+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher publisherDTOToPublisher(PublisherDTO publisherDTO) {
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