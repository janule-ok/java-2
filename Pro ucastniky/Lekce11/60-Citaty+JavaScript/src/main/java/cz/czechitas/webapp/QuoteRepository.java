package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;

@Component
public class QuoteRepository {

    private List<String> quotes = Arrays.asList(
            "If life gives you lemons, you should make lemonade... If you find somebody whom life has given vodka, have a party.",
            "I feel sorry for people who don't drink. When they wake up in the morning, that's as good as they're going to feel all day.",
            "I always wanted to be somebody, but now I realize I should have been more specific.",
            "A lot of people are afraid of heights. Not me, I'm afraid of widths.",
            "All right everyone, line up alphabetically according to your height.",
            "My fake plants died because I did not pretend to water them."
    );


    public int getQuoteCount() {
        return quotes.size();
    }


    public String getQuote(int index) {
        return quotes.get(index);
    }

}
