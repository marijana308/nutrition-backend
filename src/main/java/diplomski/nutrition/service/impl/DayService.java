package diplomski.nutrition.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Day;
import diplomski.nutrition.repository.DayRepository;
import diplomski.nutrition.service.DayServiceInterface;

@Service
public class DayService implements DayServiceInterface{
	
	@Autowired
	DayRepository dayRepository;

	@Override
	public Day save(Day day) {
		return dayRepository.save(day);
	}
	
	private boolean sameDay(Date date1, Date date2) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(date1).equals(fmt.format(date2));
	}

	@Override
	public Day findDayByDateAndUsername(Date date, String username) {
		for (Day day : dayRepository.findAll()) {
			if(day.getUser().getUsername().equals(username) && sameDay(day.getDate(), date)) {
				return day;
			}
		}
		return null;
	}
}
