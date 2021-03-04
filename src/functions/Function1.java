package functions;

import java.util.HashMap;
import java.util.Map;

import evolution.Individual;

public class Function1 implements Function {
	
	// Rosenbrockova "banana" funkcija
	
	int noOfEvaluations;
	Map<Individual, Double> memorizedValues;
	
	public Function1() {
		this.noOfEvaluations = 0;
		this.memorizedValues = new HashMap<>();
	}
	
	@Override
	public double evaluateIndividual(Individual individual) {
		if(this.memorizedValues.containsKey(individual))
			return this.memorizedValues.get(individual);
		else {
			double a = individual.getRealGene(1) - Math.pow(individual.getRealGene(0), 2);
			double result = 100*Math.pow(a, 2) + Math.pow((1-individual.getRealGene(0)), 2);
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
		this.noOfEvaluations = 0;
	}

	@Override
	public int getNoOfDimensions() {
		return 2;
	}

	@Override
	public double getMinimalValue() {
		return 0;
	}

}
