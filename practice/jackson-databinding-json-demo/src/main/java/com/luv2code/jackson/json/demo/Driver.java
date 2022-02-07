package com.luv2code.jackson.json.demo;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// create object mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// red JSON file and map/convert to Java POJO
			Student s1 = mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			
			// print firstName and lastName
			System.out.println("First Name: " + s1.getFirstName());
			System.out.println("Last Name: " + s1.getLastName());
			Address tmpAddress = s1.getAddress();
			System.out.println("Street: " + tmpAddress.getStreet());
			System.out.println("City: " + tmpAddress.getCity());
			System.out.print("Languages: ");
			for (String tmpLang : s1.getLanguages()) {
				System.out.print(tmpLang + ", ");
			}
			System.out.println("\n");
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
}
