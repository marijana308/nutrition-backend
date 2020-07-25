package diplomski.nutrition.service.impl;

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

}
