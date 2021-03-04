package functions;

import java.util.HashMap;
import java.util.Map;

import evolution.Individual;

public class Function3 implements Function{
	
	// ona kao suma xi-i na kvadrat
	
	int noOfEvaluations;
	int noOfDimensions;
	Map<Individual, Double> memorizedValues;
	
	public Function3(int noOfDimensions) {
		this.noOfDimensions = noOfDimensions;
		this.noOfEvaluations = 0;
		this.memorizedValues = new HashMap<>();
	}

	@Override
	public double evaluateIndividual(Individual individual) {
		if (this.memorizedValues.containsKey(individual))
			return this.memorizedValues.get(individual);
		else {
			double genes[] = individual.getRealGenes();
			double sum = 0;
			
			for (int i=0; i<genes.length; i++) {
				sum += Math.pow(genes[i]-i-1, 2);
			}
			
			this.noOfEvaluations++;
			
			this.memorizedValues.put(individual, sum);
			
			return sum;
		}
	}

	@Override
	public int getNoOfEvaluations() {
		return noOfEvaluations;
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
