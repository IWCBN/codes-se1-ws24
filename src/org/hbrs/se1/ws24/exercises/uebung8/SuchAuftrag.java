package org.hbrs.se1.ws24.exercises.uebung8;

public interface SuchAuftrag {

  public SuchErgebnis getSuchErgebnis();
  public String getSearchName();
  public Double getSearchMinPrice();
  public Double getSearchMaxPrice();
  public String getSearchPlace();
}
