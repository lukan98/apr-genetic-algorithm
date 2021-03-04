package functions;

import java.util.HashMap;
import java.util.Map;

import evolution.Individual;

public class Function6 implements Function {
	
	//Schafferova
	
	int noOfEvaluations;
	int noOfDimensions;
	Map<Individual, Double> memorizedValues;
	
	public Function6(int noOfDimensions) {
		this.noOfDimensions = noOfDimensions;
		this.noOfEvaluations = 0;
		this.memorizedValues = new HashMap<>();
	}

	@Override
	public double evaluateIndividual(Individual individual) {
		if (this.memorizedValues.containsKey(individual))
			return this.memorizedValues.get(individual);
		else {
			double sum = 0;
			int n = individual.getRealGenes().length;
			
			for (int i=0; i<n; i++) {
				sum += Math.pow(individual.getRealGene(i), 2);
			}
			
			double termA = Math.pow(Math.sin(Math.sqrt(sum)), 2) - 0.5;
			double termB = Math.pow(1.0+0.001*sum, 2);
			
			double result = 0.5 + termA/termB;
			this.noOfEvaluations++;
			
			this.memorizedValues.put(individual, result);
			
			return result;	
		}
	}

	@Override
	public int getNoOfEvaluations() {
		return this.noOfEvaluations;
	}

	@Override
	public void resetFunction() {
		this.noOfEvaluations=0;
	}

	@Override
	public int getNoOfDimensions() {
		return this.noOfDimensions;
	}

	@Override
	public double getMinimalValue() {
		return 0;
	}

}