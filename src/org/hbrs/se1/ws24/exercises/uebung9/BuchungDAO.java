package org.hbrs.se1.ws24.exercises.uebung9;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.util.HashMap;

public class BuchungDAO {

  private HashMap<Buchung,Status> buchungen;
  private Verification verification;

  public void addBuchung(Buchung b){
    buchungen.put(b,verification.verfiyBooking(b));
  }

  public Status getStatus(Buchung b) {
    return buchungen.get(b);
  }

  public void reVerify(Buchung b) {
    buchungen.put(b,verification.verfiyBooking(b));
  }

  public void remove(Buchung b) {
    buchungen.remove(b);
  }

  public Buchung getBuchung(int index) {
    return buchungen.keySet().toArray(new Buchung[0])[index];
  }

  public void reVerify(int index) {
    reVerify(getBuchung(index));
  }

  public void remove(int index) {
    remove(getBuchung(index));
  }


}

class Verification {
  public Status verfiyBooking (Buchung b ) {
    return null;
  }
}

class Buchung {

}

class Status {

}