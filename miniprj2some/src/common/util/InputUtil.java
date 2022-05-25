package common.util;

import java.util.Scanner;

public class InputUtil {
	public static Scanner sc = new Scanner(System.in);
	
	public static int inputInt() {
		int n = sc.nextInt();
		sc.nextLine();
		return n;
	}

	public static String inputStr() {
		String str = sc.nextLine();
		return str;
	}
	
	public static double inputDouble() {
		String str = sc.nextLine();
		return Double.parseDouble(str);
	}
}
