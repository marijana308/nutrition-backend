package diplomski.nutrition.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.RecipeFood;
import diplomski.nutrition.repository.RecipeFoodRepository;
import diplomski.nutrition.service.RecipeFoodServiceInterface;

@Service
public class RecipeFoodService implements RecipeFoodServiceInterface{
	
	@Autowired
	RecipeFoodRepository recipeFoodRepository;

	@Override
	public RecipeFood save(RecipeFood recipeFood) {
		return recipeFoodRepository.save(recipeFood);
	}

	@Override
	public RecipeFood findById(Long id) {
		return recipeFoodRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		recipeFoodRepository.deleteById(id);
	}

	@Override
	public Set<RecipeFood> findFoodsByRecipeId(Long recipeid) {
		Set<RecipeFood> foods = new HashSet<RecipeFood>();
		for(RecipeFood rf: recipeFoodRepository.findAll()) {
			if(rf.getRecipe().getId() == recipeid) {
				foods.add(rf);
			}
		}
		return foods;
	}

}
