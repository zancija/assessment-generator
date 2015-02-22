package com.zanthia.assessment;

import com.zanthia.assessment.model.Answer;
import com.zanthia.assessment.model.Assessment;
import com.zanthia.assessment.model.Question;
import com.zanthia.assessment.model.QuestionType;
import junit.framework.TestCase;

import java.util.Arrays;

public class AssessmentGeneratorServiceTest extends TestCase {
    private AssessmentGeneratorService assessmentGeneratorService = new AssessmentGeneratorService();

    public void testIsValidAssessment() {
        assertIsValidAssessment(new Assessment("id", null, null), false);
        assertIsValidAssessment(new Assessment("id", "name", null), false);

        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", -5, QuestionType.SHORT_ANSWER,
                        Arrays.asList(new Answer("text", true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question(null, 1, QuestionType.SHORT_ANSWER,
                        Arrays.asList(new Answer("text", true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.SHORT_ANSWER,
                        null))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.SHORT_ANSWER,
                        Arrays.asList(new Answer(null, true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.SHORT_ANSWER,
                        Arrays.asList(new Answer("text", true))))), true);

        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_SELECT,
                        Arrays.asList(new Answer("text", true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_SELECT,
                        Arrays.asList(new Answer("text", false), new Answer("text", false))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_SELECT,
                        Arrays.asList(new Answer("text", true), new Answer("text", true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_SELECT,
                        Arrays.asList(new Answer("text", true), new Answer("text", false))))), true);



        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList(new Answer("text", true))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList(new Answer("text", false), new Answer("text", false))))), false);
        assertIsValidAssessment(new Assessment("id", "name",
                Arrays.asList(new Question("text", 1, QuestionType.MULTIPLE_CHOICE,
                        Arrays.asList(new Answer("text", true), new Answer("text", false))))), true);

    }

    private void assertIsValidAssessment(Assessment assessment, boolean expected){
        assertEquals("Failed to test isValidAssessment()", expected, assessmentGeneratorService.isValidAssessment(assessment));
    }
}