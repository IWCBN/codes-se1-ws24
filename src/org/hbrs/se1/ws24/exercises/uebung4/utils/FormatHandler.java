package org.hbrs.se1.ws24.exercises.uebung4.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatHandler {

  public static List<Integer> extractLengthFromFormat(String format) {
    List <Integer> returnList = new ArrayList<>();
    Matcher matcher = Pattern.compile("%-(\\d+)s").matcher(format);
    while (matcher.find()) {
      returnList.add(Integer.parseInt(matcher.group(1)));
    }
    return returnList;
  }

}
