package org.hbrs.se1.ws24.exercises.uebung4.view;

import org.hbrs.se1.ws24.exercises.uebung4.HasColum;

import java.util.LinkedList;

public class ContainerView {

  public static <T>void dump(LinkedList<T> items) {
    if(items.isEmpty()){
      System.out.println("Container is empty");
      return;
    }
    if (items.get(0) instanceof HasColum) {
      String alignment = "| %-4s |" + //ID
          " %-20s |" + //Title
          " %-20s |" + //AcceptanceCriterion
          " %-20s |" + //Project
          " %-13s |" + //BusinessValue
          " %-6s |" + //Effort
          " %-4s |" + //Risk
          " %-7s |" + //Penalty
          " %-14s |"; //Prioritization
      System.out.println(((HasColum) items.get(0)).getHeadColumn(alignment));
      for (T item : items) {
        System.out.println(((HasColum) item).generateColumn(alignment));
      }
      return;
    }
    for (T item : items) {
      System.out.println(item);
    }
  }

}
