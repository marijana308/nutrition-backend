package diplomski.nutrition.service;

import java.util.Date;

import diplomski.nutrition.entity.Day;

public interface DayServiceInterface {
	
	Day save(Day day);

	Day findDayByDateAndUsername(Date date, String username);
}
