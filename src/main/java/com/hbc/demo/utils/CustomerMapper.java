package com.hbc.demo.utils;

import com.hbc.demo.model.Customer;
import com.hbc.demo.model.GlossCustomer;
import com.hbc.demo.model.MatteCustomer;
import com.hbc.demo.model.MixCustomer;

import java.util.HashSet;
import java.util.Set;

/**
 * Util class to map customer from file to java object.
 */
public class CustomerMapper {

    public Customer mapFromFile(int id, String customerData, int outputSize) throws PaintShopException {
        // check for invalid number of fields
        String[] tokens = customerData.split(" ");
        if (tokens.length == 0 || tokens.length % 2 != 0) {
            throw new PaintShopException("Invalid no of fields at line no : " + id);
        }

        Set<Integer> matteIndexes = new HashSet<>();
        Set<Integer> glossIndexes = new HashSet<>();
        for (int i = 0; i < tokens.length; i++) {
            if (i % 2 != 0) {
                ColorType colorType = ColorType.fromString(tokens[i]);
                int index = Integer.valueOf(tokens[i - 1]);
                if(outputSize < index) {
                    throw new PaintShopException("Invalid input file, position for color is higher than number of colors to be mixed.");
                }
                if (ColorType.MATTE == colorType) {
                    matteIndexes.add(index);
                } else if (ColorType.GLOSS == colorType) {
                    glossIndexes.add(index);
                } else {
                    throw new PaintShopException("Invalid color type at line no : " + id);
                }
            }
        }

        return createCustomer(id, matteIndexes, glossIndexes);
    }

    private Customer createCustomer(int id, Set<Integer> matteIndexes, Set<Integer> glossIndexes) {
        Customer customer = null;
        if(matteIndexes.size() > 0 && glossIndexes.size() == 0) {
            customer = new MatteCustomer(id);
        } else if (matteIndexes.size() == 0 && glossIndexes.size() > 0) {
            customer = new GlossCustomer(id);
        } else {
            customer = new MixCustomer(id);
        }

        // add color info to customer
        customer.getColorData().put(ColorType.MATTE, matteIndexes);
        customer.getColorData().put(ColorType.GLOSS, glossIndexes);
        return customer;
    }
}
