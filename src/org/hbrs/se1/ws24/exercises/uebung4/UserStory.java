package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements UserStoryInterface, Serializable {
    private final int id;
    private String title;
    private String acceptanceCriterion;
    private int prioritization;
    private String project;
    private int businessValue;
    private int effort;
    private int risk;
    private int penalty;
    private static int nextId = 1;

    public UserStory(String title, String acceptanceCriterion, String project, int businessValue, int effort, int risk, int penalty) {
        id = nextId++;
        this.title = title;
        this.acceptanceCriterion = acceptanceCriterion;
        this.project = project;
        this.businessValue = businessValue;
        this.effort = effort;
        this.risk = risk;
        this.penalty = penalty;
        calculatePrioritization();
    }

    private void calculatePrioritization() {
        prioritization = (businessValue+penalty)/(effort+risk);
    }


    @Override
    public int getID() {
        return id;
    }


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getAcceptanceCriterion() {
        return acceptanceCriterion;
    }

    @Override
    public void setAcceptanceCriterion(String acceptanceCriterion) {
        this.acceptanceCriterion = acceptanceCriterion;
    }

    @Override
    public int getPrioritization() {
        return prioritization;
    }

    @Override
    public String getProject() {
        return this.project;
    }

    @Override
    public void setProject(String project) {
        this.project = project;
    }

    public boolean equals(Object userStory) {
        if(userStory instanceof UserStory) {
            return id == ((UserStory)userStory).getID();
        }
        return false;
    }

    public String toString() {
        return "ID: "+ id +"Titel: " + title;
    }
}
