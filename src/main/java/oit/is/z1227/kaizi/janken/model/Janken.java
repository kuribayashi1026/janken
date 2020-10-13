package oit.is.z1227.kaizi.janken.model;

public class Janken {
  String userhand;

  public static String Uhand(String userhand) {
    return userhand;
  }

  public static String Result(String userhand) {
    String result;
    if (userhand.equals("chi")) {
      result = "draw";
    } else if (userhand.equals("gu")) {
      result = "win";
    } else {
      result = "lose";
    }
    return result;
  }
}
