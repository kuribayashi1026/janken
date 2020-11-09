package oit.is.z1227.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.ui.ModelMap;
import oit.is.z1227.kaizi.janken.model.Janken;
import oit.is.z1227.kaizi.janken.model.Match;
import oit.is.z1227.kaizi.janken.model.MatchMapper;
import oit.is.z1227.kaizi.janken.model.MatchInfo;
import oit.is.z1227.kaizi.janken.model.MatchInfoMapper;
import oit.is.z1227.kaizi.janken.model.User;
import oit.is.z1227.kaizi.janken.model.UserMapper;
import oit.is.z1227.kaizi.janken.model.Entry;
import oit.is.z1227.kaizi.janken.service.AsyncKekka;

@Controller
public class Lec02Controller {

  @Autowired
  private Entry entry;

  @Autowired
  UserMapper userMapper;

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper matchInfoMapper;
  @Autowired
  AsyncKekka asyncKekka;
  private int user_id;

  @GetMapping("lec02")
  public String getlec02(Principal prin, ModelMap model) {
    String loginUser = prin.getName();
    this.entry.addUser(loginUser);
    model.addAttribute("entry", this.entry);
    model.addAttribute("user", loginUser);
    ArrayList<Match> match2 = matchMapper.selectAllMatches();
    model.addAttribute("match2", match2);
    ArrayList<User> user2 = userMapper.selectAllUsers();
    model.addAttribute("user2", user2);
    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(String name, ModelMap model) {
    model.addAttribute("username", name);
    return "lec02.html";
  }

  @GetMapping("/match")
  public String Hands(Principal prin, ModelMap model, @RequestParam Integer id) {
    var loginUser = prin.getName();
    MatchInfo matchInfo = new MatchInfo();
    User user = userMapper.selectNameById(id);
    matchInfo.setIs_active(true);
    matchInfo.setUser_1(user_id);
    matchInfo.setUser_2(id);
    matchInfoMapper.insertMatchInfo(matchInfo);
    model.addAttribute("user", loginUser);
    model.addAttribute("user2", user);
    return "match.html";
  }

  @GetMapping("/result")
  public String result(Principal prin, ModelMap model, @RequestParam Integer id, @RequestParam Integer hand) {
    String loginUser = prin.getName();
    Janken janken = new Janken(hand);
    Match match = new Match();
    match.setUser_1_hand("チョキ");
    match.setUser_2_hand(janken.userhand);
    match.setUser_1(user_id);
    match.setUser_2(id);
    matchMapper.insertMatch(match);
    model.addAttribute("result", janken.result);
    model.addAttribute("user", loginUser);
    model.addAttribute("userhand", janken.userhand);
    return "wait.html";
  }

  @GetMapping("check")
  public SseEmitter check() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.asyncKekka.GetActive(sseEmitter);
    return sseEmitter;
  }

}
