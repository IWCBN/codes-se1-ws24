package org.hbrs.se1.ws24.exercises.uebung8;

import java.util.Random;

public class ReiseAnbieter {
  public QueryResult executeQuery(QueryObject d) {
    String[] result = {
        "Maria, New York, 106.02\nLuca, Helsinki, 197.74",
        "Isabella, Oslo, 295.13\nMaria, Moscow, 285.76",
        "Gabriel, Buenos Aires, 154.27\nLi, Stockholm, 246.23",
        "John, Moscow, 220.98\nMia, Stockholm, 272.76",
        "Ahmed, New York, 127.28\nAhmed, Vienna, 157.95\nMaria, Toronto, 253.2",
        "Charlotte, São Paulo, 146.39\nCharlotte, Vienna, 213.9",
        "Emma, São Paulo, 266.85\nPierre, Stockholm, 292.87",
        "Maria, Helsinki, 141.42\nMaria, London, 256.81\nAhmed, Beijing, 239.94\nJohn, Stockholm, 191.42\nMaria, Madrid, 210.61\nLi, Sydney, 178.4",
        "Ahmed, Cape Town, 283.82\nEmma, Cairo, 213.3\nGabriel, Oslo, 148.9\nCharlotte, Cape Town, 154.78",
        "Ethan, Stockholm, 195.77\nIsabella, Paris, 294.99"
    };
    if(d.ID < 0 || d.ID > 9) {
      Random random = new Random();
      return new QueryResult(result[random.nextInt(result.length)]);
    } else {
      return new QueryResult(result[d.ID]);
    }
  }
}

class QueryObject {
  public int ID;
}

class QueryResult {
  public QueryResult(String result) {
    this.result = result;
  }
  public final String result;
}
