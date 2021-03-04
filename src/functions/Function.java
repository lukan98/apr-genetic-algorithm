package functions;

import evolution.Individual;

public interface Function {
	public double evaluateIndividual(Individual individual);
	
	public int getNoOfEvaluations();
	
	public void resetFunction();
	
	public int getNoOfDimensions();
	
	public double getMinimalValue();
}
