package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Loan;
import libdirector.domain.dto.LoanDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-16T03:59:39+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public Loan loanDTOToLoan(LoanDTO loanDTO) {
        if ( loanDTO == null ) {
            return null;
        }

        Loan loan = new Loan();

        loan.setNotes( loanDTO.getNotes() );

        return loan;
    }
}
