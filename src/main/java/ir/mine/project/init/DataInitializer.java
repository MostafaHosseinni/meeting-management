package ir.mine.project.init;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.WeekDays;
import ir.mine.project.service.WorkingHourService;

@Component
public class DataInitializer {

	@Autowired
	private WorkingHourService workingHourService;

	@PostConstruct
	public void initData() throws IOException {
		Long count = workingHourService.countAllNoteSecure();

		if (count == 0) {
			initWorkingHour();
		}
	}

	private void initWorkingHour() {

		WeekDays[] week = WeekDays.values();
		for (int i = 0; i < week.length; i++) {
			WorkingHour work = new WorkingHour();
			work.setWorkDay(week[i]);
			workingHourService.saveNotSecure(work);

		}
	}
}
