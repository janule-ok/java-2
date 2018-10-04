package net.sevecek.videoboss.repository;

import java.util.*;
import javax.annotation.*;
import org.springframework.stereotype.*;
import net.sevecek.videoboss.entity.*;

@Component("customerRepository")
public class InMemoryCustomerRepository implements CustomerRepository {

    private long idSequence = 100L;
    private Map<Long, Customer> customers = new TreeMap<>();

    @PostConstruct
    public void initialize() {
        addCustomer(new Customer("Charles", "Dickens", "London"));
        addCustomer(new Customer("Mark", "Twain", "Missouri"));
        addCustomer(new Customer("Victor", "Hugo", "Paris"));
        addCustomer(new Customer("Jára", "Cimrman", "Liptákov"));
        addCustomer(new Customer("Bertold", "Brecht", "Berlin"));
        addCustomer(new Customer("Umberto", "Eco", "Piedmont"));
        addCustomer(new Customer("Franz", "Kafka", "Praha"));
    }

    @Override
    public List<Customer> findAllCustomers(int firstItem, int count) {
        if (count == -1) {
            count = customers.size();
        }
        List<Customer> all = new ArrayList<>(count);
        for (Customer customer : customers.values()) {
            if (firstItem > 0) {
                firstItem--;
            } else {
                if (!customer.isDeleted()) {
                    all.add(customer);
                    count--;
                    if (count == 0) break;
                }
            }
        }
        return all;
    }

    @Override
    public Customer findCustomer(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new NullPointerException("No Customer " + id);
        }
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        long id = generateNextIdFromSequence();
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer newCustomer) {
        Customer originalCustomer = customers.get(newCustomer.getId());
        if (originalCustomer.getVersion() != newCustomer.getVersion()) {
            throw new RuntimeException("Optimistic lock collision: Customer [ID="+newCustomer.getId()+"]. Customer in database was concurrently changed by another user");
        }
        originalCustomer.setFirstName(newCustomer.getFirstName());
        originalCustomer.setLastName(newCustomer.getLastName());
        originalCustomer.setAddress(newCustomer.getAddress());
        originalCustomer.setDeleted(newCustomer.isDeleted());
        originalCustomer.setVersion(originalCustomer.getVersion() + 1);
        return originalCustomer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer originalCustomer = customers.get(customer.getId());
        if (originalCustomer.getVersion() != customer.getVersion()) {
            throw new RuntimeException("Optimistic lock collision: Customer [ID="+customer.getId()+"]. Customer in database was concurrently changed by another user");
        }
        originalCustomer.setVersion(originalCustomer.getVersion() + 1);
        originalCustomer.setDeleted(true);
        return originalCustomer;
    }

    private long generateNextIdFromSequence() {
        return ++idSequence;
    }
}
