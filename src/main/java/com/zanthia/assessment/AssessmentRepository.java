package com.zanthia.assessment;

import com.mongodb.WriteResult;
import com.zanthia.assessment.model.Assessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Zanthia on 18/02/2015.
 */
@Repository
public class AssessmentRepository {

    private static final String COLLECTION = "assessments";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Assessment> getAssessments() {
        return mongoTemplate.findAll(Assessment.class, COLLECTION);
    }

    public Assessment getAssessment(String id){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Assessment.class, COLLECTION);
    }

    public void saveAssessment(Assessment assessment){
        mongoTemplate.save(assessment, COLLECTION);
    }

    public WriteResult deleteAssessment(String id){
        return mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Assessment.class, COLLECTION);
    }
}
