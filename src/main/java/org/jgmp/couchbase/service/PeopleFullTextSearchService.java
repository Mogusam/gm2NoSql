package org.jgmp.couchbase.service;

import java.util.ArrayList;
import java.util.List;

import org.jgmp.couchbase.model.People;
import org.jgmp.couchbase.repo.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;

@Service
public class PeopleFullTextSearchService {


    @Autowired
    private Cluster cluster;

    @Autowired
    private PeopleRepository repository;

    @Value("${full.text.index}")
    private String indexName;

    public List<People> findFullText(String text) {

        try {
            final SearchResult result = cluster.searchQuery(indexName, SearchQuery.queryString(text));

            List<String> ids = new ArrayList<>();
            for (SearchRow row : result.rows()) {
                ids.add(row.id());
            }
            return repository.findAllById(ids);
        } catch (CouchbaseException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}