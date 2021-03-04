package evolution;

import java.util.Arrays;
import java.util.Random;

public class DoubleIndividual implements Individual {
	
	private double[] genes;
	private double costFunction;
	private BinaryCoder bc;
	
	// creates individual with genes passed as arguments
	public DoubleIndividual(double[] genes, BinaryCoder bc) {
		this.genes = new double[genes.length];
		this.bc = bc;
		
		for(int i=0; i<genes.length; i++) {
			this.genes[i] = genes[i];
		}
	}
	
	// creates individual with random genes
	public DoubleIndividual(Random r, int size, BinaryCoder bc) {
		this.genes = new double[size];
		this.bc = bc;
		
		double lowerBound = bc.getLowerBound();
		double upperBound = bc.getUpperBound();
		
		for(int i=0; i<genes.length; i++) {
			this.genes[i] = lowerBound + (upperBound-lowerBound)*r.nextDouble();
		}
	}
	
	public double[] getRealGenes() {
		return this.genes;
	}
	
	public void setCostFunction(double value) {
		this.costFunction = value;
	}
	
	public double getCostFunction() {
		return this.costFunction;
	}
	
	public BinaryCoder getBinaryCoder() {
		return this.bc;
	}
	
	public void mutate(double mutationProbability) {
		Random r = new Random();
		
		for(int i=0; i<this.genes.length; i++) {
			if(r.nextDouble() < mutationProbability) this.genes[i] = bc.getLowerBound() + (bc.getUpperBound()-bc.getLowerBound())*r.nextDouble();
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
		DoubleIndividual other = (DoubleIndividual) obj;
		if (!Arrays.equals(genes, other.genes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Individual: genes=" + Arrays.toString(genes);
	}

	@Override
	public int compareTo(Individual o) {
		Double cfA = this.costFunction;
		Double cfB = o.getCostFunction();
		
		return cfA.compareTo(cfB);
	}

	@Override
	public double getRealGene(int index) {
		return this.genes[index];
	}

	@Override
	public int getSize() {
		return this.genes.length;
	}

	@Override
	public int[] getBinaryArray(int index) {
		return null;
	}

}
