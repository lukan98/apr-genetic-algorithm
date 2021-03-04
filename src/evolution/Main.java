package evolution;

import java.util.Random;

import functions.*;

public class Main {

	public static void main(String[] args) {
	
		first();
	}
	
	public static void first() {
		BinaryCoder coder = new BinaryCoder(-50, 150, 3);
		
		GeneticAlgorithm ssa;
		
		Function rosenbrock = new Function1();
		Function weirdSum = new Function3(5);
		Function schaffer = new Function6(2);
		Function nearlySchaffer = new Function7(2);
		
//		(Function function, int maxPopulation, int maxIterations,
//		double mutationProbability, int maxFunctionEvals, boolean binaryIndividuals, BinaryCoder bd, boolean uniformCrossovers, int tournamentSize)
		
		System.out.println("FUNKCIJA:");
		
		System.out.println("Pomična točka:");
		
		ssa = new SteadyStateAlgorithm(nearlySchaffer, 50, 100000, 0.2, 100000, false, coder, false, 3);
		
		for (int i=0; i<10; i++)
			ssa.execute();
		
		System.out.println("Binarni prikaz:");

		for (int i=0; i<10; i++) {
			ssa = new SteadyStateAlgorithm(nearlySchaffer, 50, 1000, 0.2, 100000, true, coder, true, 3);
			ssa.execute();
		}
			
		
	}
	
	public static void second() {
		
		int dimensions[] = new int[] {1, 3, 6, 10};
		GeneticAlgorithm ssa;
		
		BinaryCoder coder = new BinaryCoder(-50, 150, 3);
		
		for (int dimension : dimensions) {
			Function schaffer = new Function6(dimension);
			Function nearlySchaffer = new Function7(dimension);
			System.out.println("Broj dimenzija "+ dimension);
			
			ssa = new SteadyStateAlgorithm(nearlySchaffer, 50, 100000, 0.2, 0, false, coder, false, 3);
			ssa.execute();
			
		}
	}
	
	public static void third() {
		BinaryCoder coder = new BinaryCoder(-50, 150, 4);
		Function schaffer3 = new Function6(3);
		Function nearlySchaffer3 = new Function7(3);
		Function schaffer6 = new Function6(6);
		Function nearlySchaffer6 = new Function7(6);
		
		GeneticAlgorithm ssa;
		
		ssa = new SteadyStateAlgorithm(nearlySchaffer6, 50, 100000000, 0.2, 100000, false, coder, false, 3);
		for(int i=0; i<5; i++)
			ssa.execute();
		
	}
	
	public static void fourth() {
		BinaryCoder coder = new BinaryCoder(-50, 150, 4);
		int popSizes[] = new int [] {30, 50, 100, 200};
		double mutationProbs[] = new double[] {0.3};
		
		Function f;
		GeneticAlgorithm ssa;
		
		for(int popSize : popSizes) {
			for (double mp : mutationProbs) {
				for (int i=0; i<6;i++) {
					System.out.println("Veličina populacije: "+popSize+", vjerojatnost mutacije: "+mp);
					f = new Function6(2);
					ssa = new SteadyStateAlgorithm(f, popSize, 100000, mp, 100000, false, coder, false, 3);
					ssa.execute();
				}
			}
		}
		
	}
	
	public static void fifth() {
		BinaryCoder coder = new BinaryCoder(-50, 150, 4);
		
		int ks[] = new int[] {3, 5, 7, 9};
		
		Function f = new Function6(2);
		for (int k: ks) {
			System.out.println("k="+k);
			for (int i=0; i<10; i++) {
				GeneticAlgorithm ssa = new SteadyStateAlgorithm(f, 40, 100000, 0.3, 100000, false, coder, false, k);
				
				ssa.execute();
			}
		}
	}

}
