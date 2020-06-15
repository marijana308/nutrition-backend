package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long>{

}
