package evolution;

import java.util.Random;

public class Crossover {
	
	// za realne jedinke
	public static Individual arithmeticCrossover(Individual parentA, Individual parentB, Random r) {
		double childGenes[] = new double[parentA.getSize()];
		
		double rand = r.nextDouble();
		
		for(int i=0; i<parentA.getSize(); i++) {
			childGenes[i] = rand*parentA.getRealGene(i) + (1-rand)*parentB.getRealGene(i);
		}
		
		return new DoubleIndividual(childGenes, parentA.getBinaryCoder());
	}
	
	// za binarne jedinke
	public static Individual singlePointCrossover(Individual parentA, Individual parentB, Random r) {
		int childGenes[] = new int[parentA.getSize()];
		
		int crossoverIndex = r.nextInt(parentA.getSize());
		
		for (int i=0; i<childGenes.length; i++) {
			if (i<crossoverIndex)
				childGenes[i] = parentA.getBinaryCoder().convertToBinary(parentA.getRealGene(i));
			else
				childGenes[i] = parentB.getBinaryCoder().convertToBinary(parentB.getRealGene(i));
		}
		
		return new BinaryIndividual(childGenes, parentA.getBinaryCoder());
	}
	
	// za binarne jedinke
	public static Individual uniformCrossover(Individual parentA, Individual parentB, Random r) {
		int childGenes[] = new int[parentA.getSize()];
		
		for (int i=0; i<parentA.getSize(); i++) {
			int binA[] = parentA.getBinaryArray(i);
			int binB[] = parentB.getBinaryArray(i);
			
			int binChild[] = new int[binA.length];
			
			for (int j=0; j<binChild.length; j++) {
				if (r.nextDouble() > 0.5)
					binChild[j] = binA[j];
				else
					binChild[j] = binB[j];
			}
			
			int value = 0;
			
			for (int j=0; j<binChild.length; j++)
				if (binChild[binChild.length-1-j] > 0) {
					value += Math.pow(2, binChild.length-1 - (binChild.length-1-j));	
				}
			
			childGenes[i] = value;
			
		}
				
		return new BinaryIndividual(childGenes, parentA.getBinaryCoder());
	}
	
	// za realne jedinke
	public static Individual uniformCrossoverDouble(Individual parentA, Individual parentB, Random r) {
		double childGenes[] = new double[parentA.getSize()];
		
		for (int i=0; i<parentA.getSize(); i++) {
			double valueA = parentA.getRealGene(i);
			double valueB = parentB.getRealGene(i);
			
			if (r.nextDouble() > 0.5)
				childGenes[i] = valueA;
			else
				childGenes[i] = valueB;
		}
		
		return new DoubleIndividual(childGenes, parentA.getBinaryCoder());
	}

}
