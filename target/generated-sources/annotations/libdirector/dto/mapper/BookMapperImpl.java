package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Book;
import libdirector.domain.dto.BookDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-16T03:59:39+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setName( bookDTO.getName() );
        book.setIsbn( bookDTO.getIsbn() );
        book.setPageCount( bookDTO.getPageCount() );
        book.setBookAuthor( bookDTO.getBookAuthor() );
        book.setBookPublisher( bookDTO.getBookPublisher() );
        book.setPublishDate( bookDTO.getPublishDate() );
        book.setBookCategory( bookDTO.getBookCategory() );
        book.setImage( bookDTO.getImage() );
        book.setShelfCode( bookDTO.getShelfCode() );
        book.setFeatured( bookDTO.getFeatured() );

        return book;
    }
}
