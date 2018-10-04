package cz.czechitas.webapp;

import java.time.*;
import java.time.format.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuoteController {

    private RandomQuoteService randomQuoteService;

    public QuoteController(RandomQuoteService randomQuoteService) {
        this.randomQuoteService = randomQuoteService;
    }

    @RequestMapping(value = "/")
    public String zobrazIndex() {
        return "index";
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public @ResponseBody String zobrazVyrok() {
        return "Do not take life too seriously. You will not get out of it alive anyways.";
    }

}
