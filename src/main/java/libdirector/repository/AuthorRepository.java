package libdirector.repository;

import libdirector.domain.requestdto.AuthorSaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import libdirector.domain.entities.Author;

import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{



    @Query("SELECT new libdirector.domain.requestdto.AuthorSaveDTO(author) FROM Author author")
    Page<AuthorSaveDTO> findAllAuthorsWithPage(Pageable page);


}
