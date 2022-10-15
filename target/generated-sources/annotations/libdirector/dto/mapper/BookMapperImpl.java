package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Book;
import libdirector.dto.BookDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-15T04:20:02+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setFeatured( bookDTO.getFeatured() );
        book.setImage( bookDTO.getImage() );
        book.setIsbn( bookDTO.getIsbn() );
        book.setName( bookDTO.getName() );
        book.setPageCount( bookDTO.getPageCount() );
        book.setPublishDate( bookDTO.getPublishDate() );
        book.setShelfCode( bookDTO.getShelfCode() );

        return book;
    }
}
