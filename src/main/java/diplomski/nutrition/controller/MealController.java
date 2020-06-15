package diplomski.nutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.MealDTO;
import diplomski.nutrition.dto.UserDTO;
import diplomski.nutrition.entity.Meal;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.service.impl.MealService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/meals")
public class MealController {
	
	@Autowired
	MealService mealService;
	
//	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//	public ResponseEntity<MealDTO> register(@RequestBody MealDTO mealDTO){
//		Meal meal = new Meal();
//		meal.setMealType(mealDTO.getMealType());
//		
//		mealService.save(meal);
//		
//		return new ResponseEntity<>(new MealDTO(meal), HttpStatus.CREATED);
//	}

}
