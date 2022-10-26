package libdirector.repository;

import libdirector.domain.entities.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

    @Query(
    value = "SELECT expire_date, book_id FROM tbl_loans l WHERE (l.return_date IS NULL and l.expire_date<= :today and l.user_id= :id)", nativeQuery = true)
    Map<String,String> notReturnedInTime(@Param("id") Long id, @Param("today")LocalDateTime today);
/*
    @Query("SELECT new libdirector.domain.entities.Loan FROM tbl_loans")
    Page<Loan> findUserLoansWithPage(Pageable page);

*/

    Page<Loan> getLoansByUserId(Pageable pageable, Long userId);



}
