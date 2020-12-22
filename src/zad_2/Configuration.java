package zad_2;

public class Configuration {

	private String[] fileNames;
	private int filesCount;
	private int iter;
	private int n_size;
	private int tabu_size;
	private int runs;
	private boolean isSwap;
	private boolean isInversion;
	
	public Configuration() {
		filesCount = 1;
		
		fileNames = new String[this.filesCount];
//		this.fileNames[0] = "A-n32-k5";
//		this.fileNames[0] = "A-n37-k6";
//		this.fileNames[0] = "A-n39-k5";
//		this.fileNames[0] = "A-n45-k6";
//		this.fileNames[0] = "A-n48-k7";
//		this.fileNames[0] = "A-n54-k7";
		this.fileNames[0] = "A-n60-k9";
		
		iter = 15000;
		n_size = 120;
		tabu_size = 500;
		runs = 10;
		isSwap = false;
		isInversion = true;
		
		//TABU ... best: 1446, w: 4143, avg: 1476, std: 29
	}
	
	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public boolean isSwap() {
		return isSwap;
	}

	public void setSwap(boolean isSwap) {
		this.isSwap = isSwap;
	}

	public boolean isInversion() {
		return isInversion;
	}

	public void setInversion(boolean isInversion) {
		this.isInversion = isInversion;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public int getFilesCount() {
		return filesCount;
	}

	public void setFilesCount(int filesCount) {
		this.filesCount = filesCount;
	}

	public int getIter() {
		return iter;
	}

	public void setIter(int iter) {
		this.iter = iter;
	}

	public int getN_size() {
		return n_size;
	}

	public void setN_size(int n_size) {
		this.n_size = n_size;
	}

	public int getTabu_size() {
		return tabu_size;
	}

	public void setTabu_size(int tabu_size) {
		this.tabu_size = tabu_size;
	}
	
}
