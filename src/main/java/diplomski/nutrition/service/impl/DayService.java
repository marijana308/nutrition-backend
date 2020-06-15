package diplomski.nutrition.service.impl;

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
}
