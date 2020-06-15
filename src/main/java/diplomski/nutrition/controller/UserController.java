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
import diplomski.nutrition.dto.TokenDTO;
import diplomski.nutrition.dto.UserDTO;
import diplomski.nutrition.entity.RegularUser;
import diplomski.nutrition.entity.User;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.security.TokenUtils;

@RestController
//@RequestMapping("/api/users")
//@SuppressWarnings({ "rawtypes", "unchecked" })
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
//        	System.out.println("********USER CONTROLLER, HTTP STATUS OK**************");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
//            System.out.println("details.getAuthorities().toString() = " + details.getAuthorities().toString());
//            System.out.println("details.getAuthorities().toArray()[0] = " + details.getAuthorities().toArray()[0]);
            return new ResponseEntity<TokenDTO>(new TokenDTO(genToken),
            		HttpStatus.OK);
        } catch (Exception ex) {
        	System.out.println("*********USER CONTROLLER, HTTPSTATUS BAD REQUEST************");
        	System.out.println("username= " + loginDTO.getUsername() + " password= " + loginDTO.getPassword());
            return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
        }
	}
}
