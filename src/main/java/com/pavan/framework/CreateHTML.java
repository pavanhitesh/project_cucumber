package com.pavan.framework;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;


public class CreateHTML {

	BufferedWriter bw;
	FileOutputStream file;


	public void create() throws Exception {
		File f = new File("C:\\Hitesh\\Selenium\\WorkSpace\\seleniumReporting\\files\\test.html");
		file= new FileOutputStream(f);
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("<html>");
		bw.write("<head>");
		String sss = "<style>"
				+ "table "
				+ "{"
				+ "font-family: arial, sans-serif; "
				+ "border-collapse: collapse;width: 100%;"
				+ "}"
				+ "td, th {"
				+ "border: 5px solid #dddddd;"
				+ "text-align: center;padding: 8px;"
				+ "}"
				+ "tr:nth-child(even) {"
				+ "background-color: #eee;"
				+ "}"
				+ "tr:nth-child(odd) {"
				+ "background-color: #fff;"
				+ "}"
				+ "</style>";

		bw.write(sss);
		bw.write("</head>");
		bw.write("<body>");
		bw.write("<h1 style="+"text-align:center;"+">"
				+ "Test Result</h1>");


		String table;
		table = "<table>"
				+ "<tr>"
				+ "<th>TestCase</th>"
				+ "<th>TestStep</th>"
				+ "<th>Result</th>"
				+ "</tr>";
			
		bw.newLine();
		bw.write(table);
		insertRow("TestCase1", "User Login", "Pass");
		insertRow("TestCase1", "User Login", "Fail");
		insertRow("TestCase1", "User Login", "Skip");
		bw.write("</table>");
		bw.write("</body>");
		bw.write("</html>");


		bw.close();
		file.close();
	}

	public static void main(String[] args) throws Exception {

		CreateHTML cc = new CreateHTML();
		cc.create();

	}

	public void insertRow(String TestCase,String TestStep,String Result) throws Exception {
		
		bw.newLine();
		
		String temprow1 = "<tr>"
				+ "<td>"+TestCase+"</td>"
				+ "<td>"+TestStep+"</td>";
		String temprowfail = "<td style="+"background-color:red;"+">"+Result+"</td>"
				+ "</tr>";
		String temprowpass = "<td style="+"background-color:green;"+">"+Result+"</td>"
				+ "</tr>";
		String temprowskip = "<td style="+"background-color:yellow;"+">"+Result+"</td>"
				+ "</tr>";
		
		String row = null;
		
		if (Result.equalsIgnoreCase("Pass")) {
			
			row = temprow1+temprowpass;
			
		} else if (Result.equalsIgnoreCase("Fail")) {
			
			row = temprow1+temprowfail;
			
		} else if (Result.equalsIgnoreCase("Skip")) {
			
			row = temprow1+temprowskip;
		}
		
		
		bw.write(row);
		
	}


}
