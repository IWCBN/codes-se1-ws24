package org.hbrs.se1.ws24.exercises.uebung4.view;

import org.hbrs.se1.ws24.exercises.uebung4.HasColum;
import org.hbrs.se1.ws24.exercises.uebung4.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.UserStoryInterface;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class ContainerView {

  /**
   * Gibt den Inhalt einer verketteten Liste auf der Konsole aus.
   * Wenn die Liste Objekte enthält, die gegen die Interface HasColum und UserStory implementiert sind, wird
   * die Ausgabe in eine Tabelle mit spezifischen Spaltenbreiten mit folgenden Elementen formatiert:
   * <table>
   *   <tr>
   *     <th>ID</th>
   *     <th>Titel</th>
   *     <th>Kriterium</th>
   *     <th>Projekt</th>
   *     <th>BusinessValue</th>
   *     <th>Effort</th>
   *     <th>Risk</th>
   *     <th>Penalty</th>
   *     <th>Prioritization</th>
   *   </tr>
   *    <tr>
   *     <td>1</td>
   *     <td>Beispiel Titel</td>
   *     <td>Beispiel Kriterium</td>
   *     <td>Beispiel Projekt</td>
   *     <td>1.0</td>
   *     <td>1.0</td>
   *     <td>1.0</td>
   *     <td>1.0</td>
   *     <td>1.0</td>
   *   </tr>
   *   </table>
   * Andernfalls wird jedes Element auf einer neuen Zeile ausgegeben.
   *
   * @param <T> der Typ der Elemente in der Liste
   * @param items Die verkettete Liste, deren Inhalt ausgegeben werden soll.
   */
  public static <T>void dump(LinkedList<T> items) {
    if(items.isEmpty()){
      System.out.println("Container is empty");
      return;
    }
    if (items.get(0) instanceof HasColum && items.get(0) instanceof UserStory) {
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

  /**
   * Gibt die Elemente der übergebenen LinkedList basierend auf dem angegebenen Projektfilter aus.
   *
   * <p>Wenn die Liste leer ist, wird <code>Container is empty</code> ausgegeben und die Methode beendet.
   * Falls `projectFilter` null ist, werden alle Elemente ohne Filter ausgegeben. Ist ein Filter angegeben,
   * so werden die Elemente der Liste nach dem im `projectFilter` spezifizierten Projekt gefiltert
   * und die gefilterte Liste wird an die {@link #dump(LinkedList)} übergeben.
   *
   * @param <T>           der Typ der Elemente in der Liste.
   * @param items         die LinkedList mit den zu verarbeitenden Elementen; muss gegen
   *                      {@link UserStoryInterface} oder einer seiner Unterklassen Implementer sein.
   * @param projectFilter der Projektname, nach dem die Elemente gefiltert werden sollen; bei null wird kein Filter angewendet
   * @throws IllegalArgumentException falls die Elemente in der Liste keine Implementierungen des {@link UserStoryInterface} sind
   */
  public static <T>void dump(LinkedList<? super UserStoryInterface> items,String projectFilter) throws IllegalArgumentException {
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
