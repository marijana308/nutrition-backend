package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long>{

}
