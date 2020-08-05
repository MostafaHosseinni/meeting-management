package ir.mine.project.domain;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.domain.enumeration.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A Profile.
 */
@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Profile extends BaseUser {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private ProfileType profileType;
	
	private String post;
	
	private ZonedDateTime lastLoginTime;

	private ZonedDateTime previousLoginTime;


}
