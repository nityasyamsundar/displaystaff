package edu.acc.java2.day3.displaystaff;

import java.io.*;
import java.util.*;

public class DisplayStaff {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar displaystaff.jar <file-path>");
		}
	
		String line;
		List<String> li = new ArrayList<>();
		int count = 0;
		int skip_record = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				} 
				count++;
				String[] record = line.split(",");
				if (record.length != 4) {
					System.out.println("\n\nLine " +count+ " is invalid: expected 4 fields but has " +record.length+ " fields" );
					skip_record++;
					continue;
				} 
				try {
					int id = Integer.parseInt(record[2]);
				} catch(NumberFormatException nfe) {
					System.out.println("Line " +count+ " is invalid: expected an integer for ID but got " +record[2] );
					skip_record++;
					continue;
				}
				li.add("Employee[name:" +record[0] + " " +record[1]+ " , id: " +record[2] + " , title: " +record[3]+ " ]");
			}
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		} 
		int actual_records = count - skip_record;
		System.out.printf("\n\n");
		System.out.println("Loaded " +actual_records+ " employee records");
		System.out.printf("\n\n");
		System.out.println("Skipped " +skip_record+ " records \n\n");
		for (int i=0; i<actual_records; i++) {
			System.out.println(li.get(i));	
		}		
	}
}