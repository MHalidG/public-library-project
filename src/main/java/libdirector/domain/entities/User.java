package libdirector.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable=false)
	private String firstName;

	@Column(nullable=false)
	private String lastName;

	@Column(nullable=false)
	private Integer score=0;

	@Column(length = 100,nullable=false)
	private String address;

	@Column(nullable=false)
	private String phone;
	
	@Column
	private Date birthDate;

	@Column(length = 80, nullable = false, unique = true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private LocalDateTime createDate;
	
	@Column
	private String resetPasswordCode;
	
	@Column(nullable=false)
	private Boolean builtIn=false;


	@JsonIgnore
	@OneToMany(mappedBy = "userId")
	private List<Loan> userBooks=new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="tbl_userRoles",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles=new HashSet<>();

	
}