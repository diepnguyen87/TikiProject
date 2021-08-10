package vn.tiki.common;

import java.text.NumberFormat;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {
		String selectedRealPrice = "";
		selectedRealPrice = NumberFormat.getNumberInstance(Locale.ITALY).format(13998000);
		System.out.println(String.valueOf(selectedRealPrice));
	}
}
