package com.sample.converters.test;

import org.junit.Test;

import com.sample.converters.NumberToWordConverter;

import junit.framework.TestCase;

public class NumberToWordConverterTest extends TestCase {

	@Test
	public void setNumberToWordConverter() throws Exception {
		String expected=null;
		try {
			expected = NumberToWordConverter.convertNumberToWord(new Long(12345));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, "British English Word is twelve thousand three hundred forty five");

	}
	
	
	@Test
	public void setNumberToWordConverter_negative() throws Exception {
		String expected=null;
		try {
			expected = NumberToWordConverter.convertNumberToWord(new Long(-1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, "British English Word is");

	}

}
