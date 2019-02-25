package com.hbc.demo.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class's object will hold all information for paint shop and its customers.
 */
public class PaintShop {

    private int totalColorsToMix;

    private Set<Customer> customers = new HashSet<>();

    public PaintShop(int totalColorsToMix) {
        this.totalColorsToMix = totalColorsToMix;
    }

    public int getTotalColorsToMix() {
        return totalColorsToMix;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean isValid() {
        Optional<Customer> invalidCustomer = customers.stream().filter(customer -> !customer.isValid()).findFirst();
        if (invalidCustomer.isPresent()) {
            return false;
        }

        return true;
    }

}
