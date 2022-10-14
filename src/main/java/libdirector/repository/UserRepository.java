package libdirector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import libdirector.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
