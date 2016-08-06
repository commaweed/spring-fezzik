package fezzik.component.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import fezzik.component.domain.user.FezzikUser;

public interface FezzikUserRepository extends MongoRepository<FezzikUser, Serializable> {
	
}