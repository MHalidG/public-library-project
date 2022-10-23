package libdirector.domain.requestdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanFinishDTO {

        private LocalDateTime expireDate;

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm:ss",timezone= "Turkey")
        private LocalDateTime returnDate;

        @Size(max = 300)
        private String notes;




}
