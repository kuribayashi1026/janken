package oit.is.z1227.kaizi.janken.model;

public class Janken {
  public String result;
  public String userhand;

  public Janken(int hand) {

    if (hand == 2) {
      userhand = "チョキ";
      result = "draw";
    } else if (hand == 3) {
      result = "lose";
      userhand = "パー";
    } else {
      result = "win";
      userhand = "グー";
    }
  }

}
