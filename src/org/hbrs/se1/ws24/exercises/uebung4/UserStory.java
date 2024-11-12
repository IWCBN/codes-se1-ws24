package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements UserStoryInterface, Serializable , HasColum{
    private final int id;
    private String title;
    private String acceptanceCriterion;
    private double prioritization;
    private String project;
    private double businessValue;
    private double effort;
    private double risk;
    private double penalty;
    private static int nextId = 1;

    public UserStory(String title, String acceptanceCriterion, String project, double businessValue, double effort, double risk, double penalty) {
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
    public double getPrioritization() {
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

    public String getHeadColumn(String alignment) {
        return String.format(alignment, "ID", "Titel", "Kriterium", "Projekt", "BusinessValue", "Effort", "Risk", "Penalty", "Prioritization");
    }

    public String generateColumn(String alignment) {
        return String.format(alignment, id, title, acceptanceCriterion, project, businessValue, effort, risk, penalty, prioritization);
    }
}
