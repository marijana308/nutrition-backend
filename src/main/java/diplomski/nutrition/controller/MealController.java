package diplomski.nutrition.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.MealDTO;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.service.impl.MealService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/meals")
public class MealController {
	
	@Autowired
	MealService mealService;

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "{dayid}",method = RequestMethod.GET)
	public ResponseEntity<List<MealDTO>> getMealsByDayId(@PathVariable Long dayid){
		List<Meal> meals = mealService.findMealsByDayId(dayid);
		List<MealDTO> mealDTOs = new ArrayList<MealDTO>();
		for(Meal m: meals) {
			mealDTOs.add(new MealDTO(m));
		}
		return new ResponseEntity<List<MealDTO>>(mealDTOs, HttpStatus.OK);
	}
}
