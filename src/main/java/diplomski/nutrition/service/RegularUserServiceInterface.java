package diplomski.nutrition.service;

import diplomski.nutrition.entity.RegularUser;

public interface RegularUserServiceInterface {
	
	RegularUser save(RegularUser regularUser);
	
	RegularUser update(RegularUser regularUser);
	
	RegularUser findById(Long id);

}
