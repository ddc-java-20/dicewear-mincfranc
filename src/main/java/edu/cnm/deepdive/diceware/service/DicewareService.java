package edu.cnm.deepdive.diceware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import org.springframework.stereotype.Service;

@Service
public class DicewareService implements PassphraseService {

  //placed cursor between wordlistprovider, alt enter, constructor and the bottom was produced
  private final List<String> words;
  private final RandomGenerator rng;

  //an array list is best for this vs a linked list
  public DicewareService(WordListProvider wordListProvider, RandomGenerator rng) {
    words = new ArrayList<>(wordListProvider.words());
    this.rng = rng;
  }

  //here we tell the source of randomness to generate values up to the limit number, then it
  // passes down the words that correspond to the number on the list
  @Override
  public List<String> generate(int count) {
    return rng
        .ints(0, words.size())
        .limit(count)
        .mapToObj(words::get)
        .toList();
  }
}
