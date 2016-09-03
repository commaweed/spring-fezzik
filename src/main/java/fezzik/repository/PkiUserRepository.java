package fezzik.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fezzik.domain.PkiUser;

/**
 * The DAO responsible for data access for anything related to the User collection in the mongo database
 * for users that use the 2-way ssl login.
 */
@RepositoryRestResource
public interface PkiUserRepository extends MongoRepository<PkiUser, String> {


}