package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private CustomerRepository customerRepository = new CustomerRepository();

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/services/customers/all.json");
    }

    @RequestMapping("/services/customers/all.json")
    public @ResponseBody List<Customer> zobrazZakazniky() throws SQLException {
        List<Customer> zakaznici = customerRepository.findAll();
        return zakaznici;
    }

}
