package diplomski.nutrition.service;

import diplomski.nutrition.entity.RegularUser;

public interface RegularUserServiceInterface {
	
	RegularUser save(RegularUser regularUser);
	
	RegularUser updateAndCalculate(RegularUser regularUser);
	
	RegularUser findById(Long id);
	
	RegularUser update(RegularUser regularUser);

}
