package libdirector.domain.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.entities.Author;
import libdirector.domain.requestdto.AuthorSaveDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-26T00:42:55+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author authorSaveDTOToAuthor(AuthorSaveDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setName( authorDTO.getName() );
        author.setBuiltIn( authorDTO.getBuiltIn() );

        return author;
    }
}
