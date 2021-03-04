package evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import functions.Function;

public class SteadyStateAlgorithm extends GeneticAlgorithm {
	
	private int k;

	public SteadyStateAlgorithm(Function function, int maxPopulation, int maxIterations,
			double mutationProbability, int maxFunctionEvals, boolean binaryIndividuals, BinaryCoder bd, boolean uniformCrossovers, int k) {
		super(function, maxPopulation, maxIterations, mutationProbability, maxFunctionEvals, binaryIndividuals, bd, uniformCrossovers);
		this.k = k;
	}

	@Override
	protected void createGeneration() {
		for(Individual individual : this.population) {
			individual.setCostFunction(this.function.evaluateIndividual(individual));
		}
		
		Random r = new Random();
		
		while (true) {
			Individual individualA = null;
			Individual individualB = null;
			Individual individualC = null;
			
			individualA = Selection.kTournament(this.population, this.k);
			while (!individualA.equals(individualB))
				individualB = Selection.kTournament(this.population, this.k);
			while (!individualA.equals(individualC) && !individualB.equals(individualC))
				individualC = Selection.kTournament(this.population, this.k);
			
			ArrayList<Individual> selectedIndividuals = new ArrayList<>();
			selectedIndividuals.add(individualA);
			selectedIndividuals.add(individualB);
			selectedIndividuals.add(individualC);
			
			Collections.sort(selectedIndividuals);
			
			// tu dodati if u slucaju da imamo binarni prikaz
			Individual newMember = null;
			
			if (super.binaryIndividuals)
				if (super.uniformCrossovers)
					newMember = Crossover.uniformCrossover(selectedIndividuals.get(0), selectedIndividuals.get(1), r);
				else
					newMember = Crossover.singlePointCrossover(selectedIndividuals.get(0), selectedIndividuals.get(1), r);
			else
				if (super.uniformCrossovers)
					newMember = Crossover.uniformCrossoverDouble(selectedIndividuals.get(0), selectedIndividuals.get(1), r);
				else
					newMember = Crossover.arithmeticCrossover(selectedIndividuals.get(0), selectedIndividuals.get(1), r);
			
			newMember.mutate(this.mutationProbability);
			
			newMember.setCostFunction(this.function.evaluateIndividual(newMember));
			
			if (newMember.getCostFunction() <= selectedIndividuals.get(2).getCostFunction()) {
				this.population.set(this.population.indexOf(selectedIndividuals.get(2)), newMember);
				break;
			}
		}
		
	}

}
