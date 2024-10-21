package org.hbrs.se1.ws24.exercises.uebung3.view;

import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.util.List;

public class MemberView {

  /**
   * Gibt auf der Konsole alle Member aus einer Liste aus.
   */
  public static void dump(List<Member> members) {
    for (Member member : members) {
      System.out.println(member);
    }
  }
}
