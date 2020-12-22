package zad_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
	
	private String[] fileNames;
	private FileData[] files;
	private int filesCount;
	
	public Loader() {
		Configuration cfg = new Configuration();
		this.filesCount = cfg.getFilesCount();
		
		this.fileNames = new String[this.filesCount];
		this.files = new FileData[this.filesCount];
		
		this.fileNames = cfg.getFileNames();
		
		//this.fileNames[0] = "A-n32-k5";
//		this.fileNames[0] = "A-n37-k6";
//		this.fileNames[2] = "A-n39-k5";
//		this.fileNames[3] = "A-n45-k6";
		//this.fileNames[4] = "A-n48-k7";
	}
	
	public FileData loadFileData(String fileName) {
		FileData fileData = new FileData();
		fileName = "data/"+fileName+".vrp";
		FileReader file;
		try {
			file = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(file);
			String line;
		    try {
		    	int lineNumber = 0;
		    	int dimension = 0;
		    	Location depot = new Location();
		    	Location[] customers = null;
		    	int demandSum = 0;
				while ((line = br.readLine()) != null) {
				   lineNumber++;
				   if(lineNumber == 1) {
					   String[] name = line.split(": ");
					   fileData.setName(name[1]);
				   } else if(lineNumber == 4) {
					   dimension = Integer.parseInt(line.replaceAll("[\\D]", ""));
					   fileData.setDimension(dimension);
					   customers = new Location[dimension-1];
				   }else if(lineNumber == 6) {
					   int capacity = Integer.parseInt(line.replaceAll("[\\D]", ""));
					   fileData.setCapacity(capacity);
				   }else if(lineNumber >= 8 && lineNumber < 8+dimension) {
					   String[] parts = line.substring(1).split(" ");
					   if(lineNumber == 8) {
						   depot.setCustomerNumber(Integer.parseInt(parts[0]) - 1);
						   depot.setX(Integer.parseInt(parts[1]));
						   depot.setY(Integer.parseInt(parts[2]));
					   }else {
						   Location tempLocation = new Location();
						   tempLocation.setCustomerNumber(Integer.parseInt(parts[0]) - 1);
						   tempLocation.setX(Integer.parseInt(parts[1]));
						   tempLocation.setY(Integer.parseInt(parts[2]));
						   customers[Integer.parseInt(parts[0]) - 2] = tempLocation;
					   }
				   }else if(lineNumber >= 9 + dimension && lineNumber < 9+2*dimension) {
					   String[] parts = line.split(" ");
					   demandSum += Integer.parseInt(parts[1]);
					   if(lineNumber == 9 + dimension) {
						   depot.setDemand(Integer.parseInt(parts[1]));
						   fileData.setDepot(depot);
					   } else {
						   customers[Integer.parseInt(parts[0]) - 2].setDemand(Integer.parseInt(parts[1]));;
					   }
				   }
				}
				fileData.setCustomers(customers);
				fileData.setDemandSum(demandSum);
				fileData.setMinimumAmountOfVehicles((int) Math.ceil(demandSum / fileData.getCapacity()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileData;
	}
	
	public FileData[] loadAllFilesData() {
		int count = 0;
		for(String file: this.fileNames) {
			this.files[count] = this.loadFileData(file);
			count++;
		}
		return this.files;
	}
}
