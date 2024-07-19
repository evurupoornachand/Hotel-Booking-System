package com.console.Controller;

import com.console.DAO.CustomerDAO;
import com.console.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void addCustomer(Customer customer) throws SQLException {
        customerDAO.addCustomer(customer);
    }

    public void getCustomer(int customerId) throws SQLException {
        Customer customer = customerDAO.getCustomer(customerId);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    public void removeCustomer(int customerId) throws SQLException {
        customerDAO.removeCustomer(customerId);
    }

    public void listCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
