package libdirector.repository;

import libdirector.domain.entities.User;
import libdirector.domain.requestdto.CategorySaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);

	Page<User> findAllUserPageable(Pageable pageable);
/*
	@Query("SELECT new libdirector.domain.requestdto.LoanSaveDTO(loan) FROM Loan loan WHERE user_Id= :id")
	Page<CategorySaveDTO> findAllUserLoansWithPage(Pageable page,Long id);
*/


}
