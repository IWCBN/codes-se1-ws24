package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.utils.FormatHandler;

import java.io.Serializable;
import java.util.List;

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
    private static int nextId = 0;

    public UserStory(String title, String acceptanceCriterion, String project, double businessValue, double effort,
                     double risk, double penalty) {
        id = ++nextId;
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

    public static void setNextId(int id) {
        nextId = id;
    }

    @Override
    public String getTitle() {
        return title;
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

    public String generateColumn(String alignment) throws IllegalArgumentException {
        String returnString = "";
        List<Integer> alignmentList = FormatHandler.extractLengthFromFormat(alignment);
        int position = 0;
        if (alignmentList.size() != 9) {
            throw new IllegalArgumentException("Alignment must contain 9 elements");
        }
        while (true) {
            String[] line = new String[alignmentList.size()];
            line[0] = Integer.toString(id).length() >= position * alignmentList.get(0)
                ? Integer.toString(id).substring(position * alignmentList.get(0),
                Math.min(Integer.toString(id).length(), (position + 1) * alignmentList.get(0)))
                : "";
            line[1] = title.length() >= position * alignmentList.get(1)
                ? title.substring(position * alignmentList.get(1),
                Math.min(title.length(), (position + 1) * alignmentList.get(1)))
                : "";
            line[2] = acceptanceCriterion.length() >= position * alignmentList.get(2)
                ? acceptanceCriterion.substring(position * alignmentList.get(2),
                Math.min(acceptanceCriterion.length(), (position + 1) * alignmentList.get(2)))
                : "";
            line[3] = project.length() >= position * alignmentList.get(3)
                ? project.substring(position * alignmentList.get(3),
                Math.min(project.length(), (position + 1) * alignmentList.get(3)))
                : "";
            line[4] = Double.toString(businessValue).length() >= position * alignmentList.get(4)
                ? Double.toString(businessValue).substring(position * alignmentList.get(4),
                Math.min(Double.toString(businessValue).length(), (position + 1) * alignmentList.get(4)))
                : "";
            line[5] = Double.toString(effort).length() >= position * alignmentList.get(5)
                ? Double.toString(effort).substring(position * alignmentList.get(5),
                Math.min(Double.toString(effort).length(), (position + 1) * alignmentList.get(5)))
                : "";
            line[6] = Double.toString(risk).length() >= position * alignmentList.get(6)
                ? Double.toString(risk).substring(position * alignmentList.get(6),
                Math.min(Double.toString(risk).length(), (position + 1) * alignmentList.get(6)))
                : "";
            line[7] = Double.toString(penalty).length() >= position * alignmentList.get(7)
                ? Double.toString(penalty).substring(position * alignmentList.get(7),
                Math.min(Double.toString(penalty).length(), (position + 1) * alignmentList.get(7)))
                : "";
            line[8] = Double.toString(prioritization).length() >= position * alignmentList.get(8)
                ? Double.toString(prioritization).substring(position * alignmentList.get(8),
                Math.min(Double.toString(prioritization).length(), (position + 1) * alignmentList.get(8)))
                : "";
            returnString = returnString + String.format(alignment, line)+ "\n";
            position++;
            if (position * alignmentList.get(0) > Integer.toString(id).length() &&
                position * alignmentList.get(1) > title.length() &&
                position * alignmentList.get(2) > acceptanceCriterion.length() &&
                position * alignmentList.get(3) > project.length() &&
                position * alignmentList.get(4) > Double.toString(businessValue).length() &&
                position * alignmentList.get(5) > Double.toString(effort).length() &&
                position * alignmentList.get(6) > Double.toString(risk).length() &&
                position * alignmentList.get(7) > Double.toString(penalty).length() &&
                position * alignmentList.get(8) > Double.toString(prioritization).length()) {
                break;
            }
        }
        return returnString;
    }
}
