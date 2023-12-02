package mk.ukim.finki.climatecognize.repository;


import mk.ukim.finki.climatecognize.models.ClassificationDatasetEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationDatasetEntryRepository extends JpaRepository<ClassificationDatasetEntry, Long> {
}
