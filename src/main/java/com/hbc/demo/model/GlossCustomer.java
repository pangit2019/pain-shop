package com.hbc.demo.model;

import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.PaintShopException;

/**
 * Customer having preference for Glass type color only.
 */
public class GlossCustomer extends Customer {

    public GlossCustomer(int id) {
        super(id);
    }

    @Override
    public ColorType getCustomerColor() throws PaintShopException {
        return ColorType.GLOSS;
    }
}
