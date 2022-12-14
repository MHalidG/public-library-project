package libdirector.repository;

import libdirector.domain.entities.Category;
import libdirector.domain.requestdto.CategorySaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{


    @Query("SELECT new libdirector.domain.requestdto.CategorySaveDTO(category) FROM Category category")
    Page<CategorySaveDTO> findAllCategoriesWithPage(Pageable page);


}
