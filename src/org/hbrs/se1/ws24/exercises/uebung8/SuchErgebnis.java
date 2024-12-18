package org.hbrs.se1.ws24.exercises.uebung8;

import java.util.HashSet;
import java.util.Set;

public class SuchErgebnis {

  private Set<HitElement> hits = new HashSet<>();

  void addElement(HitElement element) {
    hits.add(element);
  }

  public Set<HitElement> getHits() {
    return Set.copyOf(hits);
  }

}
