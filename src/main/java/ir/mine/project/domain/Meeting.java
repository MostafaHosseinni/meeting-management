package ir.mine.project.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.domain.convertor.StringListConvertor;
import ir.mine.project.domain.enumeration.MeetingPosition;
import ir.mine.project.domain.enumeration.MeetingStatus;
import ir.mine.project.domain.enumeration.MeetingType;
import lombok.Getter;
import lombok.Setter;

/**
 * A meeting.
 */
@Entity
@Table(name = "meeting")
@Getter
@Setter

public class Meeting extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	private ZonedDateTime createDate;

	private ZonedDateTime lastModifiedDate;

	@Column(nullable = false)
	private ZonedDateTime meetingDate;

	private String meetingDateStr;

	@Column(nullable = false)
	private Integer startTime;

	@Column(nullable = false)
	private Integer endTime;

	private MeetingStatus meetingStatus;

	private MeetingType meetingType;

	private MeetingPosition meetingPosition;

	private String meetingService;

	@Convert(converter = StringListConvertor.class)
	@Column(length = 4000)
	private List<String> agenda;

	@ManyToOne
	private MeetingRoom meetingRoom;

	@ManyToOne
	private Profile creator;

	@ManyToOne
	private Profile Secretary;

	@ManyToOne
	private Profile boss;

	@ManyToMany
	@JoinTable(name = "meeting_invitees", joinColumns = @JoinColumn(name = "meeting_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "invitees_id", referencedColumnName = "id"))
	private Set<Profile> invitees = new HashSet<>();

}
