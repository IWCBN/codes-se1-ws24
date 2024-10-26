package org.hbrs.se1.ws24.exercises.uebung4;

public interface UserStoryInterface {


    public int getID();
    public String getTitle();
    public void setTitle(String title);
    public String getAcceptanceCriterion();
    public void setAcceptanceCriterion(String acceptanceCriterion);
    public int getPrioritization();
    public String getProject();
    public void setProject(String project);
}
