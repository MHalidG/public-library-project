package libdirector.controller;

import javax.validation.Valid;

import libdirector.domain.requestdto.LoanFinishDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import libdirector.domain.entities.Loan;
import libdirector.domain.requestdto.LoanSaveDTO;
import libdirector.service.LoanService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

	private LoanService loanService;

	@PostMapping
	@PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
	public ResponseEntity<Loan> makeLoan(@Valid @RequestBody LoanSaveDTO loanSaveDTO) {

		Loan loan=loanService.createLoan(loanSaveDTO);
		return ResponseEntity.ok(loan);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
	public ResponseEntity<Loan> updateLoan(@Valid @RequestBody LoanFinishDTO loanFinishDTO, @PathVariable Long id){

		Loan loan=loanService.updateLoan(loanFinishDTO,id);

		return ResponseEntity.ok(loan);

	}





}
