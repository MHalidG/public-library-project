package libdirector.repository;

import libdirector.domain.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import libdirector.domain.Author;

import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{


    @Query(value = "SELECT Author b FROM tbl_authors l  WHERE CONTAINS(l.name, '$:query') and (l.author_id= :author)", nativeQuery = true)
    Page<Map<String,String>> findAllBookWithPage(Pageable pageable, @Param("author")Long author, @Param("query") String query);


}
