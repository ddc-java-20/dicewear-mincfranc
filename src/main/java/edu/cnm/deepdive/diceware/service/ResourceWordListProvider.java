package edu.cnm.deepdive.diceware.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceWordListProvider implements WordListProvider {

  private static final Pattern WORD_EXTRACTOR = Pattern.compile("^\\s*\\d+\\s+(\\S+)\\s*$");

  private final List<String> words;

  @Autowired
  public ResourceWordListProvider(@Value("${diceware.word-list}") String resourcePath) {
    Resource resource = new ClassPathResource(resourcePath);
    try (Stream<String> lines = Files.lines(Paths.get(resource.getURI()))) {
      //any object we initialize in try parentheses will close automatically
      //it searches line by line and will return a URI
      words = lines
          .map((line) -> WORD_EXTRACTOR.matcher(line))
          .map((matcher) -> matcher.replaceAll("$1"))
          .filter((word) -> !word.isEmpty())  //if it's an empty string, the filter will evaluate to false and take it out
          .toList();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<String> words() {
    return words;
  }
}
