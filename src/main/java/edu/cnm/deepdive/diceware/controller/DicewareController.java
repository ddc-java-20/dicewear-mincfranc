package edu.cnm.deepdive.diceware.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/passphrases")
public class DicewareController {

  @GetMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String helloWorld() {
    return "Hello, world!";
  }

}
