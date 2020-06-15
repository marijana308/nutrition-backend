package diplomski.nutrition.controller;

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

import diplomski.nutrition.dto.UserDTO;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.RegularUserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegularUserController {
	
	@Autowired
	RegularUserService regularUserService;
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/api/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO){
		System.out.println("api register, userDTO username: " + userDTO.getUsername());
		System.out.println("api register, userDTO gender:" + userDTO.getGender());
		System.out.println("api register, userDTO activity: " + userDTO.getActivityLevel());
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
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/api/userDetails/{username}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
		User u = userRepository.findByUsername(username);
		Long userID = u.getId();
		RegularUser user = regularUserService.findById(userID);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/api/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		User u = userRepository.findByUsername(userDTO.getUsername());
		RegularUser user = regularUserService.findById(u.getId());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setFirstname(user.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setHeight(userDTO.getHeight());
		user.setWeight(userDTO.getWeight());
		user.setGender(userDTO.getGender());
		user.setBirthday(userDTO.getBirthday());
		user.setActivityLevel(userDTO.getActivityLevel());
		user.setGoalDate(userDTO.getGoalDate());
		user.setGoalWeight(userDTO.getGoalWeight());
		
		regularUserService.update(user);
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
}