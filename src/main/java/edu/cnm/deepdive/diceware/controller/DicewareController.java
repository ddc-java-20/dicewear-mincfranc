package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/passphrases")
@Validated
public class DicewareController {

  private final PassphraseService passphraseService;

  @Autowired
  public DicewareController(PassphraseService passphraseService) {
    this.passphraseService = passphraseService;
  }

  @GetMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String helloWorld() {
    return "Hello, world!";
  }

  @GetMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> generate(
      @RequestParam(required = false, defaultValue = "5")
      @Min(1)
      @Max(20)
      int length
  ) {
    return passphraseService.generate(length);
  }

  @GetMapping(path = "/generate", produces = MediaType.TEXT_HTML_VALUE)
  public String generate(
      @RequestParam(required = false, defaultValue = "5")
      @Min(1)
      @Max(20)
      int length,
      Model model
  ) {
    model.addAttribute("words", passphraseService.generate(length));
    return "generate";
  }

}
