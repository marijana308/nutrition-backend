package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.DayExercise;

@Repository
public interface DayExerciseRepository extends JpaRepository<DayExercise, Long>{

}
