package com.hbc.demo.model;

import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.PaintShopException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Customer having preference for both Matte and Glass type colors.
 */
public class MixCustomer extends Customer {

    public MixCustomer(int id) {
        super(id);
    }

    public boolean isMixCustomer() {
        return true;
    }

    /**
     * If color already exist, does nothing
     * If color does not exist, try to add gloss color first and then matter if required.
     * @throws PaintShopException if no possible solution exists.
     */
    @Override
    public void addColorToList(List<ColorType> outputColorTypes) throws PaintShopException {
        boolean isCustomerSatisfied = false;

        // check if already exist
        isCustomerSatisfied = isAlreadyColorPreferenceExists(outputColorTypes, isCustomerSatisfied);

        // create if does not exist
        if (!isCustomerSatisfied) {
            for (ColorType colorType : getOrderedColorsInExpensePreference()) {
                if (isCustomerSatisfied) {
                    break;
                }

                for (Integer index : getColorData().get(colorType)) {
                    if (outputColorTypes.get(index - 1) == ColorType.NONE) {
                        outputColorTypes.set(index - 1, colorType);
                        isCustomerSatisfied = true;
                        break;
                    }
                }
            }
        }

        if (!isCustomerSatisfied) {
            throw new PaintShopException("No solution exists");
        }
    }

    private boolean isAlreadyColorPreferenceExists(List<ColorType> outputColorTypes, boolean isCustomerSatisfied) {
        for (ColorType colorType : getColorData().keySet()) {
            for (Integer index : getColorData().get(colorType)) {
                if (outputColorTypes.get(index - 1) == colorType) {
                    isCustomerSatisfied = true;
                    continue;
                }
            }
        }
        return isCustomerSatisfied;
    }

    /**
     * No specific color preference.
     */
    @Override
    public ColorType getCustomerColor() throws PaintShopException {
        return ColorType.NONE;
    }

    /**
     * Order in which color should be added if adding expensive color is not required.
     */
    private List<ColorType> getOrderedColorsInExpensePreference() {
        return new ArrayList<>(Arrays.asList(ColorType.GLOSS, ColorType.MATTE));
    }

}
