package org.hbrs.se1.ws24.tests.uebung4;

@SuppressWarnings("removal")
public class NoSysExit extends SecurityManager{


  @Override
  public void checkExit(int status) {
    super.checkExit(status);
    throw new ExitOneException();
  }

  private class ExitOneException extends RuntimeException {

    ExitOneException() {
      super();
    }
  }
}
