package in.test.dummy.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.test.dummy.api.requestModel.DummyAPiModel;

/**
 * The Interface DummyApiRepository.
 */
@Repository
public interface DummyApiRepository extends CrudRepository<DummyAPiModel, Long> {}