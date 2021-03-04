package evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import functions.Function;

public abstract class GeneticAlgorithm {
	
	protected int maxPopulation;
	protected int maxIterations;
	protected double mutationProbability;
	protected int maxFunctionEvals;
	protected boolean binaryIndividuals;
	protected BinaryCoder decoder;
	protected boolean uniformCrossovers;
	
	protected ArrayList<Individual> population;
	protected Function function;
	
	// create a genetic algorithm with a random initial population and set parameters
	public GeneticAlgorithm(Function function, int maxPopulation, int maxIterations, double mutationProbability,
			int maxFunctionEvals, boolean binaryIndividuals, BinaryCoder bd, boolean uniformCrossovers) {
		this.maxPopulation = maxPopulation;
		this.maxIterations = maxIterations;
		this.mutationProbability = mutationProbability;
		this.function = function;
		this.maxFunctionEvals = maxFunctionEvals;
		this.binaryIndividuals = binaryIndividuals;
		this.decoder = bd;
		this.uniformCrossovers = uniformCrossovers;
		
		this.population = new ArrayList<>();
		Random rand = new Random();
		
		for (int popCount=0; popCount<this.maxPopulation; popCount++) {
			Individual newMember;
			
			if (binaryIndividuals)
				newMember = new BinaryIndividual(rand, this.function.getNoOfDimensions(), this.decoder);
			else
				newMember = new DoubleIndividual(rand, this.function.getNoOfDimensions(), this.decoder);
			
			newMember.setCostFunction(this.function.evaluateIndividual(newMember));
			population.add(newMember);
		}
	}
	
	public void execute() {
		
		for(int i=0; i<this.maxIterations; i++) {
			this.createGeneration();
			if(this.maxFunctionEvals < this.function.getNoOfEvaluations())
				break;
//			System.out.println("Generation: "+(i+1));
		}
		Collections.sort(this.population);
		
//		for (Individual i : this.population) {
//			System.out.println(i+ " error "+ Math.abs(i.getCostFunction()-function.getMinimalValue()));
//		}
//		
		double costFunction = this.population.get(0).getCostFunction();
		System.out.println("Best " + this.population.get(0) + "; error = " + Math.abs(function.getMinimalValue() - costFunction));
		
		this.function.resetFunction();
		
	}
	
	protected abstract void createGeneration();
	
}
