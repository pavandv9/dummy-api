package in.test.dummy.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.test.dummy.api.requestModel.Users;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends MongoRepository<Users, Long>{

}
