package org.jgmp.couchbase.model;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class People {
    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;
    private String fullName;
    private String email;
    private String gender;
    private LocalDate birthDate;

    private Sport sport;



    public People(String fullName, String email, String gender, LocalDate birthDate) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
