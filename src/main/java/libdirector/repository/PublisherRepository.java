package libdirector.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import libdirector.domain.entities.Publisher;

import java.util.Map;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long>{


    @Query(value = "SELECT Publisher b FROM tbl_publishers l  WHERE CONTAINS(l.name, :query) and (l.Publisher_id=:publisher )", nativeQuery = true)
    Page<Map<String,String>> findAllBookWithPage(Pageable pageable, @Param("publish") Long publisher, @Param("query") String query);


}
