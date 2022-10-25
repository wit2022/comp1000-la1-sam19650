package edu.wit.cs.comp1000;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import junit.framework.TestCase;

public class LA1aTestCase extends TestCase {

	private void test(String[] values, String sum, String mean, String stdDev) {
		final String input = String.join(" ", values);
		
		final String output = stringOutput(new String[] {
			"Enter five numbers: ",
			"Sum: %s%n",
			"Mean: %s%n",
			"Population Standard Deviation: %s%n" }, new Object[] {sum, mean, stdDev});
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(outContent));
		
		try {
			LA1a.main(new String[] { "foo" });
		} catch (SecurityException e) {}
		assertEquals(output, outContent.toString());
		
		System.setIn(null);
		System.setOut(null);
	}
	
	public void testZero() {
		test(new String[] { "0", "0", "0", "0", "0" }, "0.00", "0.00", "0.00");
	}
	
	public void testIntInput() {
		test(new String[] { "1", "2", "3", "4", "5" }, "15.00", "3.00", "1.41");
		test(new String[] { "0", "1", "2", "3", "4" }, "10.00", "2.00", "1.41");
		
		test(new String[] { "-1", "-2", "-3", "-4", "-5" }, "-15.00", "-3.00", "1.41");
		test(new String[] { "0", "-1", "-2", "-3", "-4" }, "-10.00", "-2.00", "1.41");
		
		test(new String[] { "1", "2", "4", "8", "16" }, "31.00", "6.20", "5.46");
		test(new String[] { "1", "-2", "4", "-8", "16" }, "11.00", "2.20", "7.96");
		test(new String[] { "0", "1", "-2", "4", "-8" }, "-5.00", "-1.00", "4.00");
	}
	
	public void testDecInput() {
		test(new String[] { "1.1", "2.2", "3.3", "4.4", "5.5" }, "16.50", "3.30", "1.56");
		test(new String[] { "1.1", "-2.2", "3.3", "-4.4", "5.5" }, "3.30", "0.66", "3.59");
		test(new String[] { "3.14159", "2.71828", "-7", "0.914", "112.5" }, "112.27", "22.45", "45.17");
	}
	
	static String stringOutput(String[] lines, Object[] values) {
		return String.format(String.join("", lines), values);
	}

}
