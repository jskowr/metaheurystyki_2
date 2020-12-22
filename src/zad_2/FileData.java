package zad_2;

public class FileData {
	private int dimension;
	private int capacity;
	private int demandSum;
	private int minimumAmountOfVehicles;
	private String name;
	private Location[] customers;
	private Location depot;
	
	public int getDemandSum() {
		return demandSum;
	}
	public void setDemandSum(int demandSum) {
		this.demandSum = demandSum;
	}
	public int getMinimumAmountOfVehicles() {
		return minimumAmountOfVehicles;
	}
	public void setMinimumAmountOfVehicles(int minimumAmountOfVehicles) {
		this.minimumAmountOfVehicles = minimumAmountOfVehicles;
	}
	public Location getDepot() {
		return depot;
	}
	public void setDepot(Location depot) {
		this.depot = depot;
	}
	public Location[] getCustomers() {
		return customers;
	}
	public void setCustomers(Location[] customers) {
		this.customers = customers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public Location getCustomerByNumber(int number) {
		return customers[number-1];
	}
	
	public void print() {
		System.out.println("name: "+this.name+", capacity: "+this.capacity+", dimension: "+this.dimension);
	}
}
