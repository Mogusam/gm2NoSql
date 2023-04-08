package org.jgmp.couchbase.service;

import java.util.List;
import java.util.Optional;

import org.jgmp.couchbase.model.People;
import org.jgmp.couchbase.model.Sport;
import org.jgmp.couchbase.repo.PeopleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;

@Service
public class PeopleCouchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleCouchService.class);


    @Autowired
    private PeopleRepository repository;


    public People savePeople(People people) {
        return repository.save(people);

    }

    public People findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<People> findByFullName(String fullName) {
        return repository.findByFullName(fullName);
    }

    public List<People> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public People addSport(String id, Sport sport) {
        Optional<People> oPeople = repository.findById(id);
        if (oPeople.isPresent()) {
            People people = oPeople.get();
            sport.setId(id);
            people.setSport(sport);

            return repository.save(people);
        }
        return null;

    }

    public List<People> findBySportName(String sportName) {
        return repository.findBySportSportName(sportName);
    }

}
