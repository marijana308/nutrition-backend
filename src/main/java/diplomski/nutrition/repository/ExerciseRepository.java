package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
