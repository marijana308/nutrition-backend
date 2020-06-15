package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.NutritionixExerciseDay;

@Repository
public interface NutritionixDayExerciseRepository extends JpaRepository<NutritionixExerciseDay, Long>{

}
