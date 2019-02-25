package com.hbc.demo;

import com.hbc.demo.utils.ColorType;
import com.hbc.demo.utils.PaintShopException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaintShopApplicationTests {

	@Test
	public void testTwoCustomerMatteOnly() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/MatteOnly.txt");

		assertEquals(4, mixedColorTypes.size());
		assertEquals(ColorType.MATTE, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(3));
	}

	@Test
	public void testTwoCustomerGlossOnly() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/GlossOnly.txt");

		assertEquals(4, mixedColorTypes.size());
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(0));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(3));
	}

	@Test
	public void testMixCustomersFirstGloss() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/MixCustomersFirstGloss.txt");

		assertEquals(4, mixedColorTypes.size());
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(3));
	}

	@Test
	public void testMixCustomers() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/MixCustomersFirstMatte.txt");

		assertEquals(4, mixedColorTypes.size());
		assertEquals(ColorType.MATTE, mixedColorTypes.get(0));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(3));
	}

	/**
	 * Scenario 1 from problem document.
	 */
	@Test
	public void testScenario1() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/Scenario1.txt");
		assertEquals(5, mixedColorTypes.size());
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(0));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(3));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(4));
	}

	/**
	 * Scenario 2 from problem document.
	 */
	@Test
	public void testScenario2() throws IOException, PaintShopException {
		try {
			PaintShopApplication paintShopApplication = new PaintShopApplication();
			paintShopApplication.mixColors("src/test/resources/Scenario2.txt");
			fail("Method should throw exception for invalid input");
		} catch (PaintShopException e) {
			assertEquals("No solution exists", e.getMessage());
		}
	}

	/**
	 * Scenario 3 from problem document.
	 */
	@Test
	public void testScenario3() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/Scenario3.txt");
		assertEquals(5, mixedColorTypes.size());
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(3));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(4));
	}

	/**
	 * Scenario 4 from problem document.
	 */
	@Test
	public void testScenario4() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/Scenario4.txt");

		assertEquals(2, mixedColorTypes.size());
		assertEquals(ColorType.MATTE, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
	}

	/**
	 * Output index is smaller than index in customers
	 */
	@Test
	public void testScenario5() throws IOException, PaintShopException {
		try {
			PaintShopApplication paintShopApplication = new PaintShopApplication();
			paintShopApplication.mixColors("src/test/resources/Scenario5.txt");
			fail("Method should throw exception for invalid input");
		} catch (PaintShopException e) {
			assertEquals("Invalid input file, position for color is higher than number of colors to be mixed.", e.getMessage());
		}
	}

	@Test
	public void testScenario6() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/Scenario6.txt");
		assertEquals(2, mixedColorTypes.size());
		assertEquals(ColorType.MATTE, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
	}

	@Test
	public void testBigFile() throws PaintShopException, IOException {
		PaintShopApplication paintShopApplication = new PaintShopApplication();
		List<ColorType> mixedColorTypes = paintShopApplication.mixColors("src/test/resources/BigFile.txt");
		assertEquals(5, mixedColorTypes.size());
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(0));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(1));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(2));
		assertEquals(ColorType.MATTE, mixedColorTypes.get(3));
		assertEquals(ColorType.GLOSS, mixedColorTypes.get(4));
	}

}
