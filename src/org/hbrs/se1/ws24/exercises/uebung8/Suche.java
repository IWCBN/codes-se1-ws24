package org.hbrs.se1.ws24.exercises.uebung8;

public class Suche implements SuchAuftrag {

  private SuchErgebnis suchErgebnis = new SuchErgebnis();
  private String searchName;
  private Double searchMinPrice;
  private Double searchMaxPrice;
  private String searchPlace;

  public Suche(String searchName, Double searchMinPrice, Double searchMaxPrice, String searchPlace) {
    this.searchName = searchName;
    this.searchMinPrice = searchMinPrice;
    this.searchMaxPrice = searchMaxPrice;
    this.searchPlace = searchPlace;
    searchTransfer();
  }

  private void searchTransfer() {
    QueryObject queryObject = new QueryObject();
    queryObject.ID = -1;
    String result = new ReiseAnbieter().executeQuery(queryObject).result;

    for (String line : result.split("\n")) {
      String[] values = line.split(",");
      suchErgebnis.addElement(new HitElement(values[0], values[1], Double.parseDouble(values[2]),null));
    }
  }

  @Override
  public SuchErgebnis getSuchErgebnis() {
    return suchErgebnis;
  }

  @Override
  public String getSearchName() {
    return searchName;
  }

  @Override
  public Double getSearchMinPrice() {
    return searchMinPrice;
  }

  @Override
  public Double getSearchMaxPrice() {
    return searchMaxPrice;
  }

  @Override
  public String getSearchPlace() {
    return searchPlace;
  }
}
