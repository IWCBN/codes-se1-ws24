package org.hbrs.se1.ws24.exercises.uebung8;

public class Hotelsuche {
  public static void main(String[] args) {
    SuchAuftrag suchAuftrag = new Suche("Test", -0.14159, 6.4, "Test");
    System.out.println("searchName: " + suchAuftrag.getSearchName());
    System.out.println("searchMinPrice: " + suchAuftrag.getSearchMinPrice());
    System.out.println("searchMaxPrice: " + suchAuftrag.getSearchMaxPrice());
    System.out.println("searchPlace: " + suchAuftrag.getSearchPlace());
    suchAuftrag.getSuchErgebnis().getHits().forEach( hitElement ->
        System.out.println("name: " + hitElement.name + ", place: " + hitElement.place + ", price: " + hitElement.price
            + ", telefon: " + hitElement.telefon + "")
    );
  }
}
