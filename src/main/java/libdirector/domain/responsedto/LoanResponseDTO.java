package libdirector.domain.responsedto;

import libdirector.domain.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDTO {

        private Book bookId;

        private String loanDate;

        private String expireDate;

        private String returnDate;





}
