package com.hbc.demo;

import com.hbc.demo.model.Customer;
import com.hbc.demo.model.PaintShop;
import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.CustomerMapper;
import com.hbc.demo.utils.PaintShopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaintShopApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PaintShopApplication.class);
    private CustomerMapper customerMapper = new CustomerMapper();

    public static void main(String args[]) throws PaintShopException, IOException {
        SpringApplication.run(PaintShopApplication.class, args);

        LOG.info("Started - PaintShopApplication");
        PaintShopApplication application = new PaintShopApplication();
        if (args == null || args.length < 1) {
            LOG.error("File path is missing");
            return;
        }

        try {
            List<ColorType> mixedColorTypes = application.mixColors(args[0]);
            LOG.info("Colors : {}", mixedColorTypes);
        } catch (PaintShopException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error("Can not locate file {}. Please check file path and file name", args[0]);
        }
        LOG.info("Completed - PaintShopApplication");
    }

    /**
     * This function will read input file and will mix colors.
     *
     * @param filePath - path of input file.
     */
    public List<ColorType> mixColors(String filePath) throws PaintShopException, IOException {
        PaintShop paintShop = createPaintShopFromFile(filePath);

        if (!paintShop.isValid()) {
            throw new PaintShopException("File contains invalid lines.");
        }

        // create output list with dummy colors
        List<ColorType> outputColorTypes = new ArrayList<>(paintShop.getTotalColorsToMix());
        for (int i = 0; i < paintShop.getTotalColorsToMix(); i++) {
            outputColorTypes.add(ColorType.NONE);
        }

        // mix colors
        mixColors(paintShop, outputColorTypes);

        // update none to gloss
        for (int i = 0; i < outputColorTypes.size(); i++) {
            if (outputColorTypes.get(i) == ColorType.NONE) {
                outputColorTypes.set(i, ColorType.GLOSS);
            }
        }

        return outputColorTypes;
    }

    private PaintShop createPaintShopFromFile(String filePath) throws IOException, PaintShopException {
        PaintShop paintShop = null;
        int lineNumber = 1;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                if (lineNumber == 1) {
                    paintShop = new PaintShop(Integer.valueOf(line));
                } else {
                    Customer customer = customerMapper.mapFromFile(lineNumber, line, paintShop.getTotalColorsToMix());
                    paintShop.addCustomer(customer);
                }

                lineNumber++;
            }
        } catch (IOException e) {
            LOG.error("Error opening input file at : {}.", filePath);
            throw new PaintShopException("Invalid file path : " + filePath, e);
        }

        return paintShop;
    }

    private void mixColors(PaintShop paintShop, List<ColorType> outputColorTypes) throws PaintShopException {
        // first fix matte and gloss only positions
        for (Customer customer : paintShop.getCustomers()) {
            if (!customer.isMixCustomer()) {
                customer.addColorToList(outputColorTypes);
                continue;
            }
        }

        // set mix customer positions
        for (Customer customer : paintShop.getCustomers()) {
            if (customer.isMixCustomer()) {
                customer.addColorToList(outputColorTypes);
            }
        }
    }

}
