package evolution;

import java.util.ArrayList;
import java.util.Random;

import functions.Function;

public class GenerationalAlgorithm extends GeneticAlgorithm {
	
	private boolean elitism;
	private int k;

	public GenerationalAlgorithm(Function function, int maxPopulation, int maxIterations, double mutationProbability,
			int maxFunctionEvals, boolean binaryIndividuals, BinaryCoder bd, boolean elitism, boolean uniformCrossovers, int k) {
		super(function, maxPopulation, maxIterations, mutationProbability, maxFunctionEvals, binaryIndividuals, bd, uniformCrossovers);
		this.k = k;
		this.elitism = elitism;
	}

	@Override
	protected void createGeneration() {
		for(Individual individual : this.population) {
			individual.setCostFunction(this.function.evaluateIndividual(individual));
		}
		
		ArrayList<Individual> auxillaryPopulation = new ArrayList<>();
		if (this.elitism) auxillaryPopulation.add(this.population.get(0));
		
		Random r = new Random();
	
		
		for(int i=0; i<this.maxPopulation; i++) {
			if(this.elitism && i==0) continue; // preskočimo jednu iteraciju
			Individual parentA = Selection.kTournament(this.population, this.k);
			Individual parentB = Selection.kTournament(this.population, this.k);

			Individual child = null;
			if (super.binaryIndividuals)
				if (super.uniformCrossovers)
					child = Crossover.uniformCrossover(parentA, parentB, r);
				else
					child = Crossover.singlePointCrossover(parentA, parentB, r);
			else
				if (super.uniformCrossovers)
					child = Crossover.uniformCrossoverDouble(parentA, parentB, r);
				else
					child = Crossover.arithmeticCrossover(parentA, parentB, r);

			child.mutate(this.mutationProbability);
			
			child.setCostFunction(this.function.evaluateIndividual(child));
			auxillaryPopulation.add(child);
		}
		
		this.population = auxillaryPopulation;
		
	}

}
