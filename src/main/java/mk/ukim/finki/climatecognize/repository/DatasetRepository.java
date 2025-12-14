package mk.ukim.finki.climatecognize.repository;

import mk.ukim.finki.climatecognize.models.Dataset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DatasetRepository  extends MongoRepository<Dataset, String> {
    List<Dataset> findByAuthor(String author);
}
