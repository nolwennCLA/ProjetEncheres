package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _00_brouillon {

	public static void main(String[] args) throws ParseException {
		
		Date d = new Date();
		System.out.println(d);
		
		String s = "14/01/2021 13:52:24";
		Date d2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(s);
		System.out.println(d2);

	}

}
