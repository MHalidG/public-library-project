package libdirector.repository;

import libdirector.domain.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
/*

    @Query(value = "SELECT Book FROM tbl_books l  WHERE CONTAINS(l.books_name,:query) " +
            "OR CONTAINS(l.books_name,:query) AND l.category_id= :cat OR l.author_id= :author " +
            "OR l.publisher_id= :publish)", nativeQuery = true)
    Page<Book> findAllBookWithPage(Pageable pageable,
                                   @Param("cat") Long cat,
                                   @Param("publish") Long publisher,
                                   @Param("author")Long author,
                                   @Param("query") String query);

*/
}

/*Yazilmasi gereken sorgu
1- q parametresi alacak bu sorgulanacak kelime olacak ve bu book name author name isbn ve publisher name icerisinde aranacak
2-Eger category idsi girilmisse sadece o kategori icerisnde icerisinde aranacak
3-Eger yayinci idsi girilmisse sadece o yayincinin kitaplari icerisinde aranacak
4-Eger Yazar Idsi girilmisse sadece o yazarin kitaplari icerisinde aranacak
5-Aktif alanı doğru olan kitapları alın, ancak yönetici için tüm kitapları alın

Yazarlarin,Kategorilerin, Yayincinin ve bookun isimlerinin bulundugu bir tablo sorgusu yazilirsa tek seferde komple sorgu onun ustune yazilabilir.



 */



// value = "SELECT expire_date, book_id FROM tbl_loans l WHERE (l.return_date IS NULL and l.expire_date<= :today and l.user_id= :id)", nativeQuery = true)

//q parametresinin değeri , yazar adı, isbn ve yayınevi adına yazılır.   ,tbl_authors l ,tbl_publisher l