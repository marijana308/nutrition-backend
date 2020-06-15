package diplomski.nutrition.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.repository.AdminRepository;
import diplomski.nutrition.service.AdminServiceInterface;

@Service
public class AdminService implements AdminServiceInterface{
	
	@Autowired
	AdminRepository adminRepository;

}
