package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import libdirector.domain.entities.Loan;

import java.time.LocalDateTime;
import java.util.Map;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

    /*
		Girdiler
		userin Id gidecek

		Filtreler
		son teslim saati gecmis ama today iade edilmemis kitap var mi?

		 */
    @Query(
    value = "SELECT expire_date, book_id FROM tbl_loans l WHERE (l.return_date IS NULL and l.expire_date<= :today and l.user_id= :id)", nativeQuery = true)
    Map<String,String> notReturnedInTimeJetz(@Param("id") Long id, @Param("today")LocalDateTime today);


}
