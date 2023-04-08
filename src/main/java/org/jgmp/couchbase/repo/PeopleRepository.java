package org.jgmp.couchbase.repo;

import java.util.List;

import org.jgmp.couchbase.model.People;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends CouchbaseRepository<People, String>{


    List<People> findByFullName(String fullName);

    List<People> findByEmail(String email);

    List<People> findBySportSportName(String sportName);

}
