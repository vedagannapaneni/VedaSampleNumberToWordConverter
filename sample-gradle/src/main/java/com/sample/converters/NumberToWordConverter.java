package com.sample.converters;

import java.text.DecimalFormat;
import java.util.Scanner;

public class NumberToWordConverter {
	private static final String[] tensNamesArray = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };
	private static final String[] numNamesArray = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };
	private static Scanner scanner;
	

	public static String convertLessThanOneThousand(int number) {
		String tempNumber;
		
		if (number % 100 < 20) {
			tempNumber = numNamesArray[number % 100];
			number /= 100;
		} else {
			tempNumber = numNamesArray[number % 10];
			number /= 10;

			tempNumber = tensNamesArray[number % 10] + tempNumber;
			number /= 10;
		}
		if (number == 0)
			return tempNumber;
		return numNamesArray[number] + " hundred" + tempNumber;
	}

	public static String convertNumberToWord(long number) throws Exception {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "zero";
		}
		String snumber = Long.toString(number);
		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		if(null!=snumber){
			int billions =getFractions(snumber,0,3);
			int millions=getFractions(snumber, 3, 6);
			int hundredThousands=getFractions(snumber, 6, 9);
			int thousands =getFractions(snumber, 9, 12);

		String tradBillions =getFormat(billions," billion ");
		
		String result = tradBillions;

		String tradMillions=getFormat(millions," million ");
		
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		}else{
			throw new Exception("please enter valid input to proceed further");
		}
			
	}

	private static String getFormat(int fraction, String msg) {
		String tradBillions;
		switch (fraction) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(fraction) + msg;
			break;
		default:
			tradBillions = convertLessThanOneThousand(fraction) + msg;
		}
		return tradBillions;
	}

	private static int getFractions(String snumber, int startIndex, int endIndex) {
		return  Integer.parseInt(snumber.substring(startIndex, endIndex));
		
	}

	/**
	 * testing
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		scanner = new Scanner (System.in);
		System.out.print("Enter your number :");  
		System.out.println("British English Word is " + convertNumberToWord(scanner.nextLong()));

	}

}
