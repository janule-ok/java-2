package net.sevecek.videoboss.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import net.sevecek.videoboss.*;
import net.sevecek.videoboss.entity.Customer;
import net.sevecek.videoboss.repository.*;

@Controller
public class CustomersController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/customers/all.html")
    public String showAllCustomers(Map<String, Object> model) {
        List<Customer> allCustomers = customerRepository.findAllCustomers(0, -1);
        model.put("customers", allCustomers);
        return "/WEB-INF/view/customers/all.jsp";
    }

    //-------------------------------------------------------------------------

    @RequestMapping(value = "/customers/{id}.html",
            method = RequestMethod.GET)
    public String showEditCustomer(
            Map<String, Object> model,
            @PathVariable("id") Long customerId) {
        Customer customer = customerRepository.findCustomer(customerId);
        model.put("customer", customer);
        return "/WEB-INF/view/customers/edit.jsp";
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
        return "/WEB-INF/view/customers/edit.jsp";
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
            method = { RequestMethod.POST },
            params = "_method=delete")
    public String processDeleteCustomer(
            @PathVariable("id") Long customerId,
            @PathVariable("version") int version) {
        customerRepository.deleteCustomer(new Customer(customerId, version));
        return "redirect:/customers/all.html";
    }

}
