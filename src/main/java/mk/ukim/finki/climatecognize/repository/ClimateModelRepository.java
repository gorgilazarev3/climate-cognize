package mk.ukim.finki.climatecognize.repository;

import mk.ukim.finki.climatecognize.models.ClimateMLModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimateModelRepository extends JpaRepository<ClimateMLModel, String> {
}
