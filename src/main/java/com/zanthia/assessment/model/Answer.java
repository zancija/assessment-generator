package com.zanthia.assessment.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Zanthia on 18/02/2015.
 */
@Document
public class Answer {

    private String text;

    private boolean correct;

    public Answer(){
    }

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }
}
