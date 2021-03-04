package evolution;

import java.util.Arrays;
import java.util.Random;

public class BinaryIndividual implements Individual {
	
	private int genes[];
	private double costFunction;
	private BinaryCoder coder;
	
	public BinaryIndividual(int genes[], BinaryCoder bc) {
		this.genes = new int[genes.length];
		this.coder = bc;
		
		for(int i=0; i<genes.length; i++) {
			this.genes[i] = genes[i];
		}
	}
	
	public BinaryIndividual(Random r, int size, BinaryCoder bc) {
		this.genes = new int[size];
		this.coder = bc;
		
		int n = bc.getNoOfBits();
		
		for (int i=0; i<size; i++) {
			this.genes[i] = r.nextInt((int) Math.pow(2, n));
		}
	}

	@Override
	public int compareTo(Individual o) {
		Double cfA = this.costFunction;
		Double cfB = o.getCostFunction();
		
		return cfA.compareTo(cfB);
	}
	
	@Override
	public String toString() {
		return "Individual: genes=" + Arrays.toString(genes) +"; real values: " + Arrays.toString(this.getRealGenes());
	}

	@Override
	public void setCostFunction(double value) {
		this.costFunction = value;
	}

	@Override
	public double getCostFunction() {
		return this.costFunction;
	}

	@Override
	public double[] getRealGenes() {
		double realGenes[] = new double[this.genes.length];
		
		for (int i=0; i<this.genes.length; i++) {
			realGenes[i] = this.coder.convertFromBinary(this.genes[i]);
		}
		
		return realGenes;
	}
	
	@Override
	public double getRealGene(int index) {
		return this.coder.convertFromBinary(this.genes[index]);
	}

	@Override
	public void mutate(double mutationProbability) {
		Random r = new Random();
		
		for(int i=0; i<this.genes.length; i++) {
			int bin[] = this.getBinaryArray(i);
			
			for (int j=0; j<bin.length; j++)
				if (r.nextDouble()<mutationProbability)
					bin[j] = 1-bin[j];
			
			int value = 0;
			
			for (int j=0; j<bin.length; j++)
				if (bin[bin.length-1-j] > 0) {
					value += Math.pow(2, bin.length-1 - (bin.length-1-j));	
				}
			
			this.genes[i] = value;
			
//			String binString = "";
//			
//			for (int j=0; j<bin.length; j++)
//				binString = binString + Integer.toString(bin[j]);
//			
//			System.out.println(binString+" "+value);
			
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(genes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryIndividual other = (BinaryIndividual) obj;
		if (!Arrays.equals(genes, other.genes))
			return false;
		return true;
	}

	@Override
	public int getSize() {
		return this.genes.length;
	}

	@Override
	public BinaryCoder getBinaryCoder() {
		return this.coder;
	}

	@Override
	public int[] getBinaryArray(int index) {
		int bin[] = new int[this.coder.getNoOfBits()];
		String binString = Integer.toBinaryString(this.genes[index]);
		
		int difference = this.coder.getNoOfBits() - binString.length();
		
		for (int i=0; i<difference; i++)
			binString = "0"+binString;
		
		for (int i=0; i<binString.length(); i++) {
			bin[i] = Integer.parseInt(String.valueOf(binString.charAt(i)));
		}
		
		return bin;
	}

}
