package net.sevecek.videoboss.controller;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import net.sevecek.videoboss.entity.*;
import net.sevecek.videoboss.repository.*;

@Controller
public class CustomersController {

    private CustomerRepository customerRepository;

    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/customers/all.html")
    public String showAllCustomers(Map<String, Object> model) {
        List<Customer> allCustomers = customerRepository.findAllCustomers(0, -1);
        model.put("customers", allCustomers);
        return "customers/all";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/{id}.html",
            method = RequestMethod.GET)
    public String showEditCustomer(
            Map<String, Object> model,
            @PathVariable("id") Long customerId) {
        Customer customer = customerRepository.findCustomer(customerId);
        model.put("customer", customer);
        return "customers/edit";
    }

    @RequestMapping(value = "/customers/{id}.html",
            method = RequestMethod.POST)
    public String processEditCustomer(
            @PathVariable("id") Long customerId,
            Customer customer) {
        customer.setId(customerId);
        customerRepository.updateCustomer(customer);
        return "redirect:/customers/all.html";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/new.html",
            method = RequestMethod.GET)
    public String showAddCustomer(
            Map<String, Object> model) {
        Customer customer = new Customer(null, 0);
        model.put("customer", customer);
        return "customers/edit";
    }

    @RequestMapping(value = "/customers/new.html",
            method = RequestMethod.POST)
    public String processAddCustomer(
            Customer customer) {
        customerRepository.addCustomer(customer);
        return "redirect:/customers/all.html";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/{id}/{version}",
            method = { RequestMethod.POST, RequestMethod.DELETE })
    public String processDeleteCustomer(
            @PathVariable("id") Long customerId,
            @PathVariable("version") int version) {
        customerRepository.deleteCustomer(new Customer(customerId, version));
        return "redirect:/customers/all.html";
    }
}
