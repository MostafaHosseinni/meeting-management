package ir.mine.project.domain;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.base.fileManagment.converter.FileConverter;
import ir.mine.project.base.fileManagment.dto.FileDetail;
import ir.mine.project.domain.convertor.StringListConvertor;
import lombok.Getter;
import lombok.Setter;

/**
 * A approvals.
 */
@Entity
@Table(name = "approvals")
@Getter
@Setter

public class Approvals extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = StringListConvertor.class)
	@Column(length = 4000)
	private List<String> outOfApproval;

	private ZonedDateTime createDate;

	private Integer startTime;

	private Integer endTime;

	@OneToOne
	private Meeting meeting;

	@Convert(converter = FileConverter.class)
	@Column(length = 4000)
	private FileDetail approvalFile;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Agenda> approvalRul;

	@ManyToOne
	private Profile creator;

	@ManyToMany
	@JoinTable(name = "approvals_presents", joinColumns = @JoinColumn(name = "approvals_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "presents_id", referencedColumnName = "id"))
	private Set<Profile> presents = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "approvals_absents", joinColumns = @JoinColumn(name = "approvals_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "absents_id", referencedColumnName = "id"))
	private Set<Profile> absents = new HashSet<>();

}
