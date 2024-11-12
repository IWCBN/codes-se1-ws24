package org.hbrs.se1.ws24.exercises.uebung4.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHandler {

  /**
   * Diese Methode dient dazu um die Länge der Strings aus einem Formatstring, mit dem Regex
   * {@Code %-(\d+)s} zu erfassen, und in eine Liste zu schreiben.
   *
   * @param format der Formatstring, aus dem die Längen der Strings ausgelesen werden sollen
   * @return die Liste der Längen
   */
  public static List<Integer> extractLengthFromFormat(String format) {
    List <Integer> returnList = new ArrayList<>();
    Matcher matcher = Pattern.compile("%-(\\d+)s").matcher(format);
    while (matcher.find()) {
      returnList.add(Integer.parseInt(matcher.group(1)));
    }
    return returnList;
  }

}
