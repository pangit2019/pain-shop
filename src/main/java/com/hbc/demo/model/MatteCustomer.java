package com.hbc.demo.model;

import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.PaintShopException;

/**
 * Customer having preference for Matte type color only.
 */
public class MatteCustomer extends Customer {

    public MatteCustomer(int id) {
        super(id);
    }

    @Override
    public ColorType getCustomerColor() throws PaintShopException {
        return ColorType.MATTE;
    }

}