package org.hbrs.se1.ws24.exercises.uebung4.view;

import org.hbrs.se1.ws24.exercises.uebung4.HasColum;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryInterface;

import java.util.LinkedList;
import java.util.stream.Collectors;

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
        System.out.print(((HasColum) item).generateColumn(alignment));
      }
      return;
    }
    for (T item : items) {
      System.out.println(item);
    }
  }

  public static <T>void dump(LinkedList<? super UserStoryInterface> items,String projectFilter) {
    if(items.isEmpty()){
      System.out.println("Container is empty");
      return;
    }
    if (projectFilter == null) {
      dump(items);
      return;
    }
    if (items.get(0) instanceof UserStoryInterface) {
      dump(items.stream()
          .filter(item -> ((UserStoryInterface) item).getProject().equals(projectFilter))
          .collect(Collectors.toCollection(LinkedList::new)));
      return;
    }
    throw new IllegalArgumentException("Not supported");
  }

}
