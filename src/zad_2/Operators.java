
package zad_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Operators {
	
	private FileData fileData;
	private int k_size;
	private int tabu_size;
	
	public FileData getFileData() {
		return fileData;
	}

	public void setFileData(FileData fileData) {
		this.fileData = fileData;
	}

	public Operators(FileData fileData, int k_size, int tabu_size) {
		this.fileData = fileData;
		this.k_size = k_size;
		this.tabu_size = tabu_size;
	}
	
	public int[] mutationSwap(Individual individual) {
		int[] sequence = individual.getSequence();
		Random random = new Random();
		int random1 = random.nextInt(sequence.length);
		int random2 = random1;
		while(random1 == random2) {
			random2 = random.nextInt(sequence.length);
		}
		int[] newSequence = new int[sequence.length];
		for(int i=0; i<newSequence.length; i++) {
			newSequence[i] = sequence[i];
		}
		int tempValue = newSequence[random2];
		newSequence[random2] = newSequence[random1];
		newSequence[random1] = tempValue;
		
		return newSequence;
	}
	
	public int[] mutationSwapGene(Individual individual) {
		int[] sequence = individual.getSequence();
		Random random = new Random();
		for(int i=0; i<sequence.length; i++) {
		int random1 = random.nextInt(sequence.length);
		while(random1 == i) {
			random1 = random.nextInt(sequence.length);
		}
		int tempValue = sequence[i];
		sequence[i] = sequence[random1];
		sequence[random1] = tempValue;
		}
		
		int[] newSequence = new int[sequence.length];
		for(int i=0; i<newSequence.length; i++) {
			newSequence[i] = sequence[i];
		}
		
		return newSequence;
	}
	
	public int[] mutationInversion(Individual individual) {
		int[] sequence = individual.getSequence();
		Random random = new Random();
		int random1 = random.nextInt(sequence.length);
		int random2 = random1;
		while(random1 == random2) {
			random2 = random.nextInt(sequence.length);
		}
		int start = -1;
		int end = - 1;
		if(random1<random2) {
			start = random1;
			end = random2;
		}
		else {
			start = random2;
			end = random1;
		}
		
		int[] newSequence = new int[sequence.length];
		for(int i=0; i<newSequence.length; i++) {
			newSequence[i] = sequence[i];
		}
		
		ArrayList<Integer> seqTemp = new ArrayList<Integer>();
		for(int i=start; i<=end; i++) {
			seqTemp.add(newSequence[i]);
		}
		Collections.reverse(seqTemp);
		for(int i=start; i<=end; i++) {
			newSequence[i] = seqTemp.get(i-start);
		}
		
		return newSequence;
	}
	
	public boolean checkIfSoultionsAreEqual(Individual individual1, Individual individual2) {
		for(int i=0; i<individual1.getSequence().length; i++) {
			if(individual1.getSequence()[i] != individual2.getSequence()[i]) return false;
		}
		return true;
	}
	
	public Individual[] createNeighbourSwap(Individual individual, ArrayList<Individual> tabuList) {
		
		Individual[] neighbours = new Individual[k_size];
		Individual ind = new Individual(individual);
		
		for(int i=0; i<k_size; i++) {
			do {
			int[] seq = mutationSwap(ind);
			int[] seq2 = new int[seq.length];
			for(int j=0; j<seq.length; j++) {
				seq2[j] = seq[j];
			}
			neighbours[i] = new Individual(ind);
			neighbours[i].setSequence(seq2);
			
			}while(checkIfInTabuList(neighbours[i], tabuList));
		}
		
		return neighbours;
	}
	
	public Individual[] createNeighbourInversion(Individual individual, ArrayList<Individual> tabuList) {
		
		Individual[] neighbours = new Individual[k_size];
		Individual ind = new Individual(individual);

		for(int i=0; i<k_size; i++) {
			do {
				int[] seq = mutationInversion(ind);
				int[] seq2 = new int[seq.length];
				for(int j=0; j<seq.length; j++) {
					seq2[j] = seq[j];
				}
				neighbours[i] = new Individual(ind);
				neighbours[i].setSequence(seq2);
				}while(checkIfInTabuList(neighbours[i], tabuList));
		}
		
		return neighbours;
	}
	
	public boolean checkIfInTabuList(Individual individual, ArrayList<Individual> tabuList) {
		for(int i=0; i<tabuList.size(); i++) {
			if(checkIfSoultionsAreEqual(individual, tabuList.get(i))) {
				return true;
			}
		}
		return false;
	}
	
}
