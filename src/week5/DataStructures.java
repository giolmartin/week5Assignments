package week5;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import acm.program.*;

public class DataStructures extends ConsoleProgram{

	HashMap<String, ArrayList<String>> flightList = new HashMap<String, ArrayList<String>>();
	ArrayList<String> city= new ArrayList<String>();

	private ArrayList<String> destinationFromNewYork = new ArrayList<String>();
	private ArrayList<String> destinationFromSanFrancisco = new ArrayList<String>();
	private ArrayList<String> destinationFromDenver = new ArrayList<String>();
	private ArrayList<String> destinationFromSanJose = new ArrayList<String>();
	private ArrayList<String> destinationFromHonolulu = new ArrayList<String>();
	private ArrayList<String> destinationFromAnchorage = new ArrayList<String>();

	

	public void run() {
		File file  = new File("flights.txt");
		readFile(file);

		println("Welcome to Travel Planner: ");
		println("List of all the cities available: ");
		String c = flightList.keySet().toString();
		String[] cities = c.split(", ");
		for(String s: cities) {
		s = s.replace("]", "");
		s = s.replace("[", "");
		println(s);
		}
		println();
		println("Lets plan your next trip: ");
		
		display();
		
		
	}
	

	private void readFile(File file) {

		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = br.readLine()) !=null) {
				String[] list = line.split(" -> ");
				for(int i = 0; i < list.length; i++ ) { //creating single cities, departure \n destination(n)
					city.add(list[i]);
				}
			}	
		} catch(IOException e){	
			e.printStackTrace();
		}
		for (int i = 0; i< city.size(); i++) {
			if (city.get(i).equals(""))  {
				city.remove(i);
			} 
		}

		processFile(city);	
	}

	private void processFile(ArrayList<String> dataList) {

		for (int i = 0; i < dataList.size(); i+= 2) {
			if(dataList.get(i).equals("San Jose")) {
				destinationFromSanJose.add(dataList.get(i+1)); // Adding the next city, which is saved as destination.
			} else if(dataList.get(i).equals("New York")){
				destinationFromNewYork.add(dataList.get(i + 1));
			}	else if(dataList.get(i).equals("Anchorage")) {
				destinationFromAnchorage.add(dataList.get(i + 1));
			} else if(dataList.get(i).equals("Honolulu")) {
				destinationFromHonolulu.add(dataList.get(i + 1 ));
			} else if(dataList.get(i).equals("Denver")) {
				destinationFromDenver.add(dataList.get(i + 1 ));
			} else if(dataList.get(i).equals("San Francisco")) {
				destinationFromSanFrancisco.add(dataList.get(i + 1));
			}
		}
		storeFile(destinationFromAnchorage, destinationFromDenver, destinationFromHonolulu, destinationFromNewYork,
				destinationFromSanFrancisco, destinationFromSanJose);
	}

	private void storeFile(ArrayList<String> anch ,ArrayList<String> dnv, ArrayList<String> ho, ArrayList<String> nY, 
			ArrayList<String> sF, ArrayList<String> sJ) {

		flightList.put("New York", nY);
		flightList.put("Anchorage", anch);
		flightList.put("Denver", dnv);
		flightList.put("Honolulu", ho);
		flightList.put("San Francisco", sF);
		flightList.put("San Jose", sJ);
	}


	
	private void display() {
	
		String departure = readLine("Enter the departure city: ");;
		String destination = "";
		ArrayList<String> tripSequence = new ArrayList<String>();
		tripSequence.add(departure);
		
		while(tripSequence.get(0).contentEquals(destination) == false) {
			 
			while(departure != "") {
				if(flightList.containsKey(departure)) {
					println("From " + departure + " you can flight to: ");
					for(String p: flightList.get(departure)) {
						println(" "+ p );
					}break;
				} else {
					println("City not available by direct fly, enter another.");
					departure = readLine("Enter the departure city: ");
				}
			}
			destination = readLine("Where do you want to flight to: ");
			ArrayList<String> trans = flightList.get(departure);
			while(destination != "") {
				if(flightList.get(departure).contains(destination)) {
					tripSequence.add(destination);
					int index = trans.indexOf(destination);
					println("Are you sure: " + trans.get(index)); break;
				} else {
					println("City not available by direct fly, enter another.");
					destination = readLine("Where do you want to flight to: ");
				}
			} departure = destination;
		}
		tripToString(tripSequence);
	}
	
	public void tripToString(ArrayList<String> s) {
		
		for (int i = 0; i < (s.size() - 1); i ++) {
			
			print(s.get(i) + " -> ");
		}
		print(s.get(s.size() - 1));
	}
}
