package lk.sachinsilva.thogakade.repo;

import lk.sachinsilva.thogakade.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, String> {
}
