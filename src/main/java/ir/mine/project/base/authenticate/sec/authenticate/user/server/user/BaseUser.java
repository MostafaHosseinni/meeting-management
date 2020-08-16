package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.domin.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "base_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public class BaseUser extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username" , unique= true)
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "password")
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "expire_date")
	private Date expireDate;

	@Column(name = "super_user")
	private Boolean superUser;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@ManyToMany
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

}
