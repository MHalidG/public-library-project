package libdirector.dto.mapper;

import javax.annotation.processing.Generated;
import libdirector.domain.Loan;
import libdirector.dto.LoanDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-15T04:20:02+0200",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.15.1 (Oracle Corporation)"
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
