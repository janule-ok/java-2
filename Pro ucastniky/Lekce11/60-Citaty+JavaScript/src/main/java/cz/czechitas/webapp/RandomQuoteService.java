package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;

@Component
public class RandomQuoteService {

    private Random randomGenerator;
    private QuoteRepository quoteRepository;

    public RandomQuoteService(Random randomGenerator, QuoteRepository quoteRepository) {
        this.randomGenerator = randomGenerator;
        this.quoteRepository = quoteRepository;
    }

    public String randomQuote() {
        int index = (int)
                (randomGenerator.nextInt(
                		quoteRepository.getQuoteCount()));
        return quoteRepository.getQuote(index);
    }

}
