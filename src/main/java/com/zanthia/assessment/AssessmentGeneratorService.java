package com.zanthia.assessment;

import com.zanthia.assessment.model.Answer;
import com.zanthia.assessment.model.Assessment;
import com.zanthia.assessment.model.Question;
import com.zanthia.assessment.model.QuestionType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

/**
 * Created by Zanthia on 16/02/2015.
 */

@Path("/assessment")
public class AssessmentGeneratorService {

    private AssessmentRepository assessmentRepository;

    public AssessmentGeneratorService() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        assessmentRepository = context.getBean(AssessmentRepository.class);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssessment(@PathParam("id") String id) {
        return Response.ok().entity(assessmentRepository.getAssessment(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssessments() {
        List<Assessment> assessments = assessmentRepository.getAssessments();
        return Response.ok().entity(assessments).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteAssessment(@PathParam("id") String id) {
        if (assessmentRepository.deleteAssessment(id) != null) {
            return Response.ok().build();
        } else {
            return Response.status(HttpStatus.NOT_FOUND.value()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAssessment(Assessment assessment) {
        if (isValidAssessment(assessment)) {
            assessmentRepository.saveAssessment(assessment);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Assessment validation failed").build();
        }

    }

    protected boolean isValidAssessment(Assessment assessment) {
        return assessment.getName() != null && isValidQuestions(assessment.getQuestions());
    }

    private boolean isValidQuestions(Collection<Question> questions) {
        if (questions == null || questions.isEmpty()){
            return false;
        }
        for (Question question : questions) {
            if (question.getText() == null
                    || question.getNumber() < 1
                    || question.getQuestionType() == null
                    || !isValidAnswers(question.getQuestionType(), question.getAnswers())) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidAnswers(QuestionType questionType, Collection<Answer> answers) {
        if (answers == null || hasEmptyAnswers(answers)) {
            return false;
        }
        if (QuestionType.SHORT_ANSWER.equals(questionType)) {
            return answers.size() == 1;
        } else if (QuestionType.MULTIPLE_CHOICE.equals(questionType)) {
            return answers.size() > 1 && getNumberOfCorrectAnswers(answers) > 0;
        } else if (QuestionType.MULTIPLE_SELECT.equals(questionType)) {
            return answers.size() > 1 && getNumberOfCorrectAnswers(answers) == 1;
        } else {
            return false;
        }
    }


    private boolean hasEmptyAnswers(Collection<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.getText() == null) {
                return true;
            }
        }
        return false;
    }

    private int getNumberOfCorrectAnswers(Collection<Answer> answers) {
        int numberOfCorrectAnswers = 0;
        for (Answer answer : answers) {
            if (answer.isCorrect()) {
                numberOfCorrectAnswers++;
            }
        }
        return numberOfCorrectAnswers;
    }
}
