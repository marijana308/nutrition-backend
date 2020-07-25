package diplomski.nutrition.service;

import java.util.List;

import diplomski.nutrition.entity.Food;

public interface FoodServiceInterface {
	
	Food findByName(String name);
	
	Food save(Food food);
	
	Food findById(Long id);
	
	void deleteById(Long id);
	
	List<Food> findFoodsByUsername(String username);
	
	List<Food> searchFoodsByUsername(String username, String query);
	
	List<Food> findAll();
	
	List<Food> findFoodsCreatedByAdmins();
	
	List<Food> searchFoodsCreatedByAdmins(String query);
	
}
