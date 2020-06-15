package diplomski.nutrition.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.Food;
import diplomski.nutrition.enumeration.Role;
import diplomski.nutrition.repository.FoodRepository;
import diplomski.nutrition.service.FoodServiceInterface;

@Service
public class FoodService implements FoodServiceInterface{
	
	@Autowired
	FoodRepository foodRepository;


	@Override
	public Food save(Food food) {
		return foodRepository.save(food);
	}
	
	@Override
	public Food findByName(String name) {
		for(Food food : foodRepository.findAll()) {
			if(food.getName().equals(name)) {
				return food;
			}
		}
		return null;
	}

	@Override
	public Food findById(Long id) {
		return foodRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		foodRepository.deleteById(id);
	}

	@Override
	public List<Food> findFoodsByUsername(String username) {
		List<Food> foods = new ArrayList<Food>();
		for(Food food : foodRepository.findAll()) {
			if(food.getUser().getUsername().equals(username)) {
				foods.add(food);
			}
		}
		return foods;
	}

	@Override
	public List<Food> findAll() {
		return foodRepository.findAll();
	}

	@Override
	public List<Food> findFoodsCreatedByAdmins() {
		List<Food> foods = new ArrayList<Food>();
		for(Food food : foodRepository.findAll()) {
			if(food.getUser().getRole().equals(Role.ADMIN)) {
				foods.add(food);
			}
		}
		return foods;
	}

}
