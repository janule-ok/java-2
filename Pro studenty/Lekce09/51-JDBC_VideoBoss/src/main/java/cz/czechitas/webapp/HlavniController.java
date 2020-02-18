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
    public ModelAndView zobrazIndex() throws SQLException {
        return new ModelAndView("index");
    }

    @RequestMapping("/customers/")
    public ModelAndView zobrazZakazniky() throws SQLException {
        List<Customer> zakaznici = customerRepository.findAll();

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("customers/index");
        drzakNaDataAJmenoSablony.addObject("seznamZakazniku", zakaznici);
        return drzakNaDataAJmenoSablony;
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/{id}.html",
            method = RequestMethod.GET)
    public String zobrazEditaciZakaznika(
            Map<String, Object> model,
            @PathVariable("id") Long customerId) {
        Customer customer = customerRepository.findOne(customerId);
        model.put("customer", customer);
        return "customers/edit";
    }

    @RequestMapping(value = "/customers/{id}.html",
            method = RequestMethod.POST)
    public String zpracujEditaciZakaznika(
            @PathVariable("id") Long customerId,
            Customer customer) {
        customer.setId(customerId);
        customerRepository.save(customer);
        return "redirect:/customers/";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/new.html",
            method = RequestMethod.GET)
    public String zobrazPridavaniZakaznika(
            Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        return "customers/edit";
    }

    @RequestMapping(value = "/customers/new.html",
            method = RequestMethod.POST)
    public String zpracujPridavaniZakaznika(
            Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers/";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/{id}/{version}",
            method = { RequestMethod.DELETE })
    public String zpracujOdstraneniZakaznika(
            @PathVariable("id") Long customerId,
            @PathVariable("version") int version) {
        customerRepository.delete(customerId, version);
        return "redirect:/customers/";
    }
}
