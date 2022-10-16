package libdirector.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libdirector.domain.Loan;
import libdirector.domain.dto.LoanDTO;
import libdirector.service.LoanService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

	private LoanService loanService;

	@PostMapping
	// @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
	public ResponseEntity<Loan> makeLoan(@Valid @RequestBody LoanDTO loanDTO) {

		return new ResponseEntity<>(loanService.createLoan(loanDTO), HttpStatus.CREATED);
	}

}
