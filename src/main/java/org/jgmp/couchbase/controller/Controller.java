package org.jgmp.couchbase.controller;

import java.util.List;

import org.jgmp.couchbase.model.People;
import org.jgmp.couchbase.model.Sport;
import org.jgmp.couchbase.service.PeopleCouchService;
import org.jgmp.couchbase.service.PeopleFullTextSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private PeopleCouchService service;
    @Autowired
    private PeopleFullTextSearchService fullTextSearchtService;

    @PostMapping("/user")
    public String savePeople(@RequestBody People people) {
       return service.savePeople(people).getId();
    }

   @PutMapping("/user/{id}/sport")
   public People addSport(@PathVariable String id, @RequestBody Sport sport) {
       return service.addSport(id, sport);
    }

    @GetMapping("/user/{id}")
    public People findById(@PathVariable  String id) {
       return service.findById(id);
    }
   @GetMapping("/user/email/{email}")
    public List<People> findByEmail(@PathVariable String email) {
       return service.findByEmail(email);
    }

    @GetMapping("/user/sport/{sportName}")
    public List<People> getPeopleBySport(@PathVariable String sportName){
        return service.findBySportName(sportName);
    }

   @GetMapping("/user/name")
    public List<People> findByName(@RequestParam(value = "fullName") String fullName) {
       return service.findByFullName(fullName);
    }

    @GetMapping("/search/user")
    public List<People> findFT(@RequestParam(value = "q") String text) {
       return fullTextSearchtService.findFullText(text);
    }


}
