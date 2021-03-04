package functions;

import java.util.HashMap;
import java.util.Map;

import evolution.Individual;

public class Function7 implements Function {
	
	// kinda Schafferova
	
	int noOfEvaluations;
	int noOfDimensions;
	Map<Individual, Double> memorizedValues;
	
	public Function7(int noOfDimensions) {
		this.noOfEvaluations = 0;
		this.noOfDimensions = noOfDimensions;
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
			
			double result = Math.pow(sum, 0.25) * (1 + Math.pow(Math.sin(50 * Math.pow(sum, 0.1)), 2));
			
			this.memorizedValues.put(individual, result);
			this.noOfEvaluations++;
			
			return result;
		}
	}

	@Override
	public int getNoOfEvaluations() {
		return this.noOfEvaluations;
	}

	@Override
	public void resetFunction() {
		this.noOfEvaluations = 0;
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
