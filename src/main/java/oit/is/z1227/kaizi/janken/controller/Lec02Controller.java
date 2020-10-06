package oit.is.z1227.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lec02Controller {
  /**
   * lec02 というGETリクエストがあったら，lec02 ()を呼び出して，lec02 .htmlを返すメソッド
   *
   * @return
   */
  @GetMapping("/lec02")
  public String lec02() {
    return "lec02.html";
  }

  /**
   * パスパラメータ2つをGETで受け付ける 1つ目の変数をparam1という名前で，2つ目の変数をparam2という名前で受け取る
   * GETで受け取った2つの変数とsample22の引数の名前が同じなため， 引数の前に @PathVariable と付けるだけで，パスパラメータの値を
   * javaで処理できるようになる ModelMapはthymeleafに渡すためのMapと呼ばれるデータ構造を持つ変数
   * Mapはkeyとvalueの組み合わせで値を保持する
   *
   * @param name
   * @param model
   * @return
   */
  @PostMapping("/toroku")
  public String toroku(@RequestParam String name, ModelMap model) {
    String tasu = name;// param1が文字列なので，parseIntでint型の数値に変換する
    String tasuResult = tasu;
    // ModelMap型変数のmodelにtasuResult1という名前の変数で，tasuResultの値を登録する．
    // ここで値を登録するとthymeleafが受け取り，htmlで処理することができるようになる
    model.addAttribute("tasuResult1", tasuResult);
    return "lec02.html";

  }

  @GetMapping("/gu")
  public String gu(@RequestParam String gu, ModelMap model) {
    String kekka = "勝ち";
    String hand = "グー";
    model.addAttribute("tasuResult3", hand);
    model.addAttribute("tasuResult2", kekka);
    return "lec02.html";
  }

  @GetMapping("/chi")
  public String chi(@RequestParam String gu, ModelMap model) {
    String kekka = "あいこ";
    String hand = "チョキ";
    model.addAttribute("tasuResult3", hand);
    model.addAttribute("tasuResult2", kekka);
    return "lec02.html";
  }

  @GetMapping("/pa")
  public String pa(@RequestParam String gu, ModelMap model) {
    String kekka = "負け";
    String hand = "パー";
    model.addAttribute("tasuResult3", hand);
    model.addAttribute("tasuResult2", kekka);
    return "lec02.html";
  }

}
