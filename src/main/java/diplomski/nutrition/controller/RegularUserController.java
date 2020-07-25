package diplomski.nutrition.controller;

import java.util.Calendar;
import java.util.Date;
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

import diplomski.nutrition.dto.CaloriesDTO;
import diplomski.nutrition.dto.DayDTO;
import diplomski.nutrition.dto.UserDTO;
import diplomski.nutrition.dto.UserDetailsDTO;
import diplomski.nutrition.entity.Day;
import diplomski.nutrition.entity.Nutrient;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.DayService;
import diplomski.nutrition.service.impl.NutrientService;
import diplomski.nutrition.service.impl.RegularUserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegularUserController {
	
	@Autowired
	RegularUserService regularUserService;
	
	@Autowired
	NutrientService nutrientService;
	
	@Autowired
	DayService dayService;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/api/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
		RegularUser regular = new RegularUser();
		regular.setUsername(userDTO.getUsername());
		regular.setPassword(userDTO.getPassword());
		regular.setFirstname(userDTO.getFirstname());
		regular.setLastname(userDTO.getLastname());
		regular.setRole(Role.REGULAR);
		regular.setHeight(userDTO.getHeight());
		regular.setWeight(userDTO.getWeight());
		regular.setGender(userDTO.getGender());
		regular.setBirthday(userDTO.getBirthday());
		regular.setActivityLevel(userDTO.getActivityLevel());
		regular.setGoalWeight(userDTO.getGoalWeight());
		regular.setGoalDate(userDTO.getGoalDate());
		
		regularUserService.save(regular);
		
		return new ResponseEntity<>(new UserDTO(regular), HttpStatus.CREATED);
	}
	
//	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
//	@RequestMapping(value = "/api/userDetails/{username}", method = RequestMethod.GET)
//	public ResponseEntity<UserDetailsDTO> getUserByUsername(@PathVariable String username) {
//		User u = userRepository.findByUsername(username);
//		Long userID = u.getId();
//		RegularUser user = regularUserService.findById(userID);
//		if(user == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.OK);
//	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/api/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDetailsDTO> updateUser(@RequestBody UserDTO userDTO){
		User u = userRepository.findByUsername(userDTO.getUsername());
		RegularUser user = regularUserService.findById(u.getId());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setHeight(userDTO.getHeight());
		user.setWeight(userDTO.getWeight());
		user.setGender(userDTO.getGender());
		user.setBirthday(userDTO.getBirthday());
		user.setActivityLevel(userDTO.getActivityLevel());
		user.setGoalDate(userDTO.getGoalDate());
		user.setGoalWeight(userDTO.getGoalWeight());
		
		regularUserService.updateAndCalculate(user);
		
		for(Nutrient n : user.getDailyNutrients()) {
			nutrientService.updateDailyValue(n.getId(), user.getDailyCalories());
			
//			nutrientService.update(n);
		}
		System.out.println("update user, user calories: " + user.getDailyCalories());
		return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.OK);
	}
	
	private Date pastDate(Integer number) {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, number);
	    return cal.getTime();
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/api/updateStreakAndPoints/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> updateStreakAndPoints(@PathVariable String username){
		User u = userRepository.findByUsername(username);
		RegularUser user = regularUserService.findById(u.getId());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Day today = dayService.findDayByDateAndUsername(new Date(), username);
		Day yesterday = dayService.findDayByDateAndUsername(pastDate(-1), username);
		
		if (yesterday == null) {
			user.setStreak(0);
		}
		if(yesterday != null && today != null) {
			user.setStreak(user.getStreak() + 1);
		}
		
		if(user.getStreak() % 7 == 0) {
			user.setPoints(user.getPoints() + 10);
		}
		
		regularUserService.update(user);
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('REGULAR')")
	@RequestMapping(value = "/api/updateRole/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> updateCalories(@PathVariable String username){
		User u = userRepository.findByUsername(username);
		if(u == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		RegularUser user = regularUserService.findById(u.getId());
		user.setRole(Role.PREMIUM);
		
		regularUserService.update(user);
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('PREMIUM')")
	@RequestMapping(value = "/api/updateCalories/{username}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> updateCalories(@PathVariable String username, @RequestBody String caloriesDTO){
		System.out.println("update calories, calorieDTO: "+ caloriesDTO);
		User u = userRepository.findByUsername(username);
		if(u == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		RegularUser user = regularUserService.findById(u.getId());
		user.setDailyCalories(Integer.valueOf(caloriesDTO));
		
		regularUserService.update(user);
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
}
