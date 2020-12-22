package zad_2;

import java.util.ArrayList;
import java.util.Random;

public class Individual {

	private int[] sequence;
	private FileData fileData;
	private int capacity;
	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Individual(Individual another) {
		this.fileData = another.fileData;
		this.capacity = another.capacity;
		int[] new_sequence = new int[another.sequence.length];
		for (int i = 0; i < new_sequence.length; i++) {
			new_sequence[i] = another.sequence[i];
		}
		this.sequence = new_sequence;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int[] getSequence() {
		return sequence;
	}

	public void setSequence(int[] sequence) {
		this.sequence = sequence;
		this.value = Process.costFunction(fileData, this);
	}

	public FileData getFileData() {
		return fileData;
	}

	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	public Individual(FileData fileData) {
		this.fileData = fileData;
		this.capacity = fileData.getCapacity();
	}

	public Individual(FileData fileData, int[] sequence) {
		this.fileData = fileData;
		this.capacity = fileData.getCapacity();
		this.sequence = sequence;
	}

	public static Individual createRandomIndividual(FileData fileData) {
		Individual individual = new Individual(fileData);
		ArrayList<Location> customersLeft = new ArrayList<Location>();
		Random random = new Random();
		Location[] customers = fileData.getCustomers();
		int customersCount = customers.length;

		if (customersCount == 0)
			return null;
		for (int i = 0; i < customersCount; i++) {
			if (customers[i].getDemand() > fileData.getCapacity()) {
				// brak rozwi¹zania
				return null;
			}
			customersLeft.add(customers[i]);
		}
		int[] sequenceTemp = new int[customersLeft.size()];
		int cnt = 0;
		while (!customersLeft.isEmpty()) {
			int randomCustomer = random.nextInt(customersLeft.size());
			sequenceTemp[cnt] = customersLeft.get(randomCustomer).getCustomerNumber();
			cnt++;
			customersLeft.remove(randomCustomer);
		}

		individual.setSequence(sequenceTemp);
		return individual;
	}

	public static Individual createGreedySolution(FileData fileData) {
		Process process = new Process();
		Individual individual = new Individual(fileData);
		ArrayList<Location> customersLeft = new ArrayList<Location>();
		Location[] customers = fileData.getCustomers();
		int customersCount = customers.length;

		if (customersCount == 0)
			return null;
		for (int i = 0; i < customersCount; i++) {
			if (customers[i].getDemand() > fileData.getCapacity()) {
				// brak rozwi¹zania
				return null;
			}
			customersLeft.add(customers[i]);
		}

		int currentDemand = 0;

		int currentLocationNumber = 0;
		Location currentLocation = null;
		Location tempLocation = null;
		int customerNumber = 0;
		int[] sequenceTemp = new int[customersCount];

		while (!customersLeft.isEmpty()) {
			currentLocation = fileData.getCustomers()[currentLocationNumber];
			// closest location
			double minDistance = 0.0;
			boolean first = true;
			for (int i = 0; i < customersLeft.size(); i++) {
				tempLocation = fileData.getCustomers()[customersLeft.get(i).getCustomerNumber() - 1];
				double distance = process.getDistanceBetweenTwoLocations(tempLocation, currentLocation);
				if (distance < minDistance || first) {
					minDistance = distance;
					customerNumber = i;
					first = false;
				}
			}

			if (customersLeft.get(customerNumber).getDemand() + currentDemand > fileData.getCapacity()) {
				currentDemand = 0;
				currentLocationNumber = 0;
			} else {
				sequenceTemp[customersCount - customersLeft.size()] = customersLeft.get(customerNumber)
						.getCustomerNumber();
				currentDemand += customersLeft.get(customerNumber).getDemand();
				customersLeft.remove(customersLeft.get(customerNumber));
				currentLocationNumber = customerNumber;
			}
		}

		individual.setSequence(sequenceTemp);
		return individual;
	}

	public int[] convertSequenceToRoutes() {
		int[] routes;
		int currentRouteAmount = 0;
		ArrayList<Integer> seqTemp = new ArrayList<Integer>();
		seqTemp.add(-1);
		for (int i = 0; i < this.sequence.length; i++) {
			if (currentRouteAmount + fileData.getCustomerByNumber(sequence[i]).getDemand() > this.capacity) {
				seqTemp.add(-1);
				currentRouteAmount = 0;
			}
			seqTemp.add(sequence[i]);
			currentRouteAmount += fileData.getCustomerByNumber(sequence[i]).getDemand();
		}
		seqTemp.add(-1);
		routes = new int[seqTemp.size()];
		for (int i = 0; i < seqTemp.size(); i++) {
			routes[i] = seqTemp.get(i);
		}
		return routes;
	}

	public void print() {
		int routeCnt = 1;
		int[] routes = this.convertSequenceToRoutes();
		for (int i = 0; i < routes.length; i++) {
			if (routes[i] == -1) {
				System.out.println();
				System.out.print("Route #" + routeCnt + ": ");
				routeCnt++;
			} else {
				System.out.print(routes[i] + " ");
			}
		}
	}

}
