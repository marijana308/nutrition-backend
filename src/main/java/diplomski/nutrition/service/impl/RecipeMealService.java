package diplomski.nutrition.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import diplomski.nutrition.entity.RecipeMeal;
import diplomski.nutrition.repository.RecipeMealRepository;
import diplomski.nutrition.service.RecipeMealServiceInterface;

@Service
public class RecipeMealService implements RecipeMealServiceInterface{

	@Autowired
	RecipeMealRepository repository;

	@Override
	public Set<RecipeMeal> findRecipesByMealId(Long id) {
		Set<RecipeMeal> recipes = new HashSet<RecipeMeal>();
		for(RecipeMeal rm : repository.findAll()) {
			if(rm.getMeal().getId() == id) {
				recipes.add(rm);
			}
		}
		return recipes;
	}

	@Override
	public RecipeMeal save(RecipeMeal recipeMeal) {
		return repository.save(recipeMeal);
	}
}
