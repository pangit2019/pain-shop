package com.hbc.demo.model;

import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.PaintShopException;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Customer class for holding customer specific data. Sub class will hold customer color preferences.
 */
public abstract class Customer {

    private static final Logger LOG = LoggerFactory.getLogger(Customer.class);

    private int id;
    private Map<ColorType, Set<Integer>> colorData = new HashMap<>();

    public Map<ColorType, Set<Integer>> getColorData() {
        return colorData;
    }

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;

        return new EqualsBuilder().append(id, customer.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).hashCode();
    }

    public boolean isMixCustomer() {
        return false;
    }

    /**
     * Validate customer object.
     */
    public boolean isValid() {
        Set<Integer> matteColorIndexes = colorData.get(ColorType.MATTE);
        if (matteColorIndexes != null && matteColorIndexes.size() > 1) {
            LOG.error("Customer on line no : " + id + " have more than 1 matter color.");
            return false;
        }

        return true;
    }

    /**
     * Get customer preferred color. In case more than more then returns None.
     */
    public abstract ColorType getCustomerColor() throws PaintShopException;

    /**
     * If color is not present set color. If already exists then does nothing.
     * If different color is exists at same index then throw error for no solution.
     */
    public void addColorToList(List<ColorType> outputColorTypes) throws PaintShopException {
        ColorType customerColorType = getCustomerColor();
        int index = getColorData().get(customerColorType).iterator().next() - 1;

        if (outputColorTypes.get(index) == ColorType.NONE) {
            outputColorTypes.set(index, customerColorType);
        }

        if (outputColorTypes.get(index) != customerColorType) {
            throw new PaintShopException("No solution exists");
        }
    }


}
