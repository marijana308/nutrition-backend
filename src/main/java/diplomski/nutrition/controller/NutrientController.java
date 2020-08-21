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

import diplomski.nutrition.dto.NutrientDTO;
import diplomski.nutrition.entity.Nutrient;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.service.impl.NutrientService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/nutrients")
public class NutrientController {
	
	@Autowired
	NutrientService nutrientService;

	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasAnyAuthority('REGULAR', 'PREMIUM')")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<NutrientDTO>> getNutrientsByUsername(@PathVariable String username) {
		User u = userRepository.findByUsername(username);
		Long userID = u.getId();
		List<Nutrient> nutrients = nutrientService.findNutrientsByUserId(userID);
		List<NutrientDTO> nutrientDTOs = new ArrayList<NutrientDTO>();
		for(Nutrient n : nutrients) {
			nutrientDTOs.add(new NutrientDTO(n));
		}
		return new ResponseEntity<>(nutrientDTOs, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('PREMIUM')")
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<NutrientDTO> updateNutrient(@RequestBody NutrientDTO dto){
		Nutrient nutrient = nutrientService.findById(dto.getId());
		if(nutrient == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		nutrient.setDailyValue(dto.getDailyValue());
		
		nutrientService.update(nutrient);
		
		return new ResponseEntity<>(new NutrientDTO(nutrient), HttpStatus.OK);
	}
}
