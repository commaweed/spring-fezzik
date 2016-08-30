package fezzik.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fezzik.domain.User;

/**
 * The DAO responsible for data access for anything related to the User collection in the mongo database.
 */
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {


}