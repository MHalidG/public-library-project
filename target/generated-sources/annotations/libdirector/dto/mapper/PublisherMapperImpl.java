package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Publisher;
import libdirector.dto.PublisherDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-15T04:20:02+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher publisherDTOToPublisher(PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher publisher = new Publisher();

        publisher.setBuiltIn( publisherDTO.getBuiltIn() );
        publisher.setId( publisherDTO.getId() );
        publisher.setName( publisherDTO.getName() );

        return publisher;
    }
}
