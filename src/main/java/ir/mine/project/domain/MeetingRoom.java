package ir.mine.project.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * A meetingroom.
 */
@Entity
@Table(name = "meetingroom")
@Getter
@Setter

public class MeetingRoom extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String roomName;

	@Column(nullable = false)
	private String capacity;

	private String address;

	@Column(nullable = false)
	private ZonedDateTime expireDate;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<RoomService> service = new HashSet<>();

}
