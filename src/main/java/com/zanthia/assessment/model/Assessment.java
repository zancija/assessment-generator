package com.zanthia.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Zanthia on 17/02/2015.
 */
@Document
public class Assessment {
    @Id
    private String id;

    private String name;

    private Collection<Question> questions = new ArrayList<Question>();

    public Assessment(){
    }

    public Assessment(String id, String name, Collection<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
