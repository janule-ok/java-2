package net.sevecek.videoboss.repository;

import java.util.List;
import net.sevecek.videoboss.entity.*;

public interface CustomerRepository {

    List<Customer> findAllCustomers(int firstItem, int count);

    Customer findCustomer(Long id);

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Customer customer);
}
