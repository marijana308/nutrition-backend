package diplomski.nutrition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import diplomski.nutrition.dto.LoginDTO;
import diplomski.nutrition.dto.UserDetailsDTO;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.repository.UserRepository;
import diplomski.nutrition.security.TokenUtils;
import diplomski.nutrition.service.impl.RegularUserService;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RegularUserService regularUserService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<UserDetailsDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            //System.out.println("details.getAuthorities().toString() = " + details.getAuthorities().toString());
            //System.out.println("details.getAuthorities().toArray()[0] = " + details.getAuthorities().toArray()[0]);
            //System.out.println("details.getAuthorities().toArray()[0].equals(Role.ADMIN) = " + details.getAuthorities().toArray()[0].toString().equals("ADMIN"));
            //System.out.println("details.getAuthorities().toArray()[0].equals(Role.REGULAR) = " + details.getAuthorities().toArray()[0].toString().equals("REGULAR"));
            if(details.getAuthorities().toArray()[0].toString().equals("ADMIN")) {
            	User u = userRepository.findByUsername(loginDTO.getUsername());
            	//System.out.println("u.getId = " + u.getId());
            	return new ResponseEntity<UserDetailsDTO>(new UserDetailsDTO(u, genToken),
                		HttpStatus.OK);
            }else {
            	User u = userRepository.findByUsername(loginDTO.getUsername());
        		RegularUser regular = regularUserService.findById(u.getId());
//        		System.out.println("regular.getId = " + regular.getId());
//        		System.out.println("UserDetailsDTO(regular, genToken) = " + new UserDetailsDTO(regular, genToken).getFirstname());
            	return new ResponseEntity<UserDetailsDTO>(new UserDetailsDTO(regular, genToken),
                		HttpStatus.OK);
            }
        } catch (Exception ex) {
        	System.out.println("*********USER CONTROLLER, HTTPSTATUS BAD REQUEST************");
        	System.out.println("username= " + loginDTO.getUsername() + " password= " + loginDTO.getPassword());
            return new ResponseEntity<>(new UserDetailsDTO(), HttpStatus.BAD_REQUEST);
        }
	}
}
