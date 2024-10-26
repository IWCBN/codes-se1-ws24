package org.hbrs.se1.ws24.exercises.uebung4;

public class UserStory implements UserStoryInterface {
    private int id;
    private String title;


    public UserStory() {

    }


    @Override
    public int getID() {
        return 0;
    }


    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public String getAcceptanceCriterion() {
        return "";
    }

    @Override
    public void setAcceptanceCriterion(String acceptanceCriterion) {

    }

    @Override
    public int getPrioritization() {
        return 0;
    }

    @Override
    public String getProject() {
        return "";
    }

    @Override
    public void setProject(String project) {

    }

    public boolean equals(UserStory userStory) {
        return false;
    }

    public String toString() {
        return "";
    }
}
