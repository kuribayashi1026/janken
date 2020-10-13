package oit.is.z1227.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import oit.is.z1227.kaizi.janken.model.Janken;

@Controller

public class Lec02Controller {
  /**
   *
   * @param name
   * @param model
   * @return
   */
  @GetMapping("/lec02")
  public String lec02() {
    return "lec02.html";
  }

  @PostMapping("/lec02")
  public String lec02(RedirectAttributes redirectAttributes, @RequestParam String name, ModelMap model) {
    redirectAttributes.addFlashAttribute("name", name);
    return "redirect:/lec02";
  }

  /**
   * @param gu
   * @param model
   * @return
   */
  @GetMapping("/lec02/{gu}")
  public String Hands(@PathVariable String gu, ModelMap model) {
    model.addAttribute("tasuResult3", Janken.Uhand(gu));
    model.addAttribute("tasuResult2", Janken.Result(gu));
    return "lec02.html";
  }
}
