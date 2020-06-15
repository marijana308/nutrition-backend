package diplomski.nutrition.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.FoodDTO;
import diplomski.nutrition.entity.Food;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.FoodService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/foods")
public class FoodController {

	@Autowired
	FoodService foodService;
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> getAllFoods(){
		List<Food> foods = foodService.findAll();
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> getFoodsByUsername(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Food> foods = foodService.findFoodsByUsername(username);
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<List<FoodDTO>> getFoodsCreatedByAdmins(){
		List<Food> foods = foodService.findFoodsCreatedByAdmins();
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food f: foods) {
			foodDTOs.add(new FoodDTO(f));
			
		}
		return new ResponseEntity<>(foodDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO foodDTO){
		Food food = new Food();
		User u = userRepository.findByUsername(foodDTO.getUsername());
		food.setUser(u);
		food.setName(foodDTO.getName());
		food.setTotalCalories(foodDTO.getCalories());
		food.setServingSize("100g");
		food.setTotalFat(foodDTO.getTotalFat());
		food.setSaturatedFat(foodDTO.getSaturatedFat());
		food.setCholesterol(foodDTO.getCholesterol());
		food.setSodium(foodDTO.getSodium());
		food.setTotalCarbs(foodDTO.getTotalCarbs());
		food.setFiber(foodDTO.getFiber());
		food.setSugars(foodDTO.getSugars());
		food.setProtein(foodDTO.getProtein());
		food.setVitaminA(foodDTO.getVitaminA());
		food.setVitaminC(food.getVitaminC());
		food.setVitaminD(food.getVitaminD());
		food.setCalcium(foodDTO.getCalcium());
		food.setIron(food.getIron());
		food.setPhosphorus(foodDTO.getPhosphorus());
		food.setPotasium(foodDTO.getPotasium());
		
		foodService.save(food);
		
		return new ResponseEntity<>(new FoodDTO(food), HttpStatus.CREATED);
	}
	

	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<FoodDTO> updateFood(@RequestBody FoodDTO foodDTO){
		Food food = foodService.findById(foodDTO.getId());
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		food.setName(foodDTO.getName());
		food.setTotalCalories(foodDTO.getCalories());
		food.setTotalFat(foodDTO.getTotalFat());
		food.setSaturatedFat(foodDTO.getSaturatedFat());
		food.setCholesterol(foodDTO.getCholesterol());
		food.setSodium(foodDTO.getSodium());
		food.setTotalCarbs(foodDTO.getTotalCarbs());
		food.setFiber(foodDTO.getFiber());
		food.setSugars(foodDTO.getSugars());
		food.setProtein(foodDTO.getProtein());
		food.setVitaminA(foodDTO.getVitaminA());
		food.setVitaminC(food.getVitaminC());
		food.setVitaminD(food.getVitaminD());
		food.setCalcium(foodDTO.getCalcium());
		food.setIron(food.getIron());
		food.setPhosphorus(foodDTO.getPhosphorus());
		food.setPotasium(foodDTO.getPotasium());
		
		foodService.save(food);
		
		return new ResponseEntity<>(new FoodDTO(food), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM', 'ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFoodById(@PathVariable Long id){
		Food food = foodService.findById(id);
		if(food == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		foodService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
