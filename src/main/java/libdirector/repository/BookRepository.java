package libdirector.repository;

import libdirector.domain.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import libdirector.domain.Book;

import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{


    @Query(value = "SELECT Book FROM tbl_books l  WHERE CONTAINS(l.books_name,:query) OR CONTAINS(l.books_name,:query) AND l.category_id= :cat OR l.author_id= :author OR l.publisher_id= :publish)", nativeQuery = true)
    Page<Book> findAllBookWithPage(Pageable pageable, @Param("cat") Long cat, @Param("publish") Long publisher, @Param("author")Long author, @Param("query") String query);
}


// value = "SELECT expire_date, book_id FROM tbl_loans l WHERE (l.return_date IS NULL and l.expire_date<= :today and l.user_id= :id)", nativeQuery = true)

//q parametresinin değeri , yazar adı, isbn ve yayınevi adına yazılır.   ,tbl_authors l ,tbl_publisher l