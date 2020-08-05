package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;//package vezarat.behdasht.yas.base.authenticate.sec.authenticate.user.server.user;
//
//import vezarat.behdasht.yas.base.domin.BaseEntity;
//
//import javax.persistence.*;
//
//@Table(name = "CORE_BASEUSER")
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//public class BaseUserEntity extends BaseEntity<Long> {
//
//	private String expireDate;
//
//	private String username;
//	private String password;
//
//
//	@Column(unique = true, nullable = false)
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	@Id
//	@GeneratedValue
//	public Long getId() {
//		return super.getId();
//	}
//
//	@Override
//	public void setId(Long id) {
//		super.setId(id);
//	}
//
//	public String getExpireDate() {
//		return expireDate;
//	}
//
//	public void setExpireDate(String expireDate) {
//		this.expireDate = expireDate;
//	}
//
//}
