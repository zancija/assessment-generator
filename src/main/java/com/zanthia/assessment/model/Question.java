package com.zanthia.assessment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Zanthia on 17/02/2015.
 */

@Document
public class Question {

    private String text;

    private int number;

    private QuestionType questionType;

    private Collection<Answer> answers = new ArrayList<Answer>();

    public Question(){
    }

    public Question(String text, int number, QuestionType questionType, Collection<Answer> answers) {
        this.text = text;
        this.number = number;
        this.questionType = questionType;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", number=" + number +
                ", questionType=" + questionType +
                ", answers=" + answers +
                '}';
    }
}
