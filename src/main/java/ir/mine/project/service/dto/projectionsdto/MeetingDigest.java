package ir.mine.project.service.dto.projectionsdto;

import java.time.ZonedDateTime;

import ir.mine.project.domain.enumeration.MeetingPosition;
import ir.mine.project.domain.enumeration.MeetingType;

/**
 * A DTO for the Transit entity.
 */
public interface MeetingDigest {

	Long getId();

	String getTitle();

	ZonedDateTime getMeetingDate();

	MeetingType getMeetingType();
	
	MeetingPosition getMeetingPosition();
	
	MeetingRoomDigestDTO getMeetingRoom();

}
