package evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Selection {
	
	public static Individual kTournament(ArrayList<Individual> population, int k) {
		ArrayList<Individual> auxillaryPopulation = new ArrayList<>();
		
		Random rand = new Random();
		
		for(int i=0; i<k; i++) {
			Individual selected = population.get(rand.nextInt(population.size()));
			while(auxillaryPopulation.contains(selected))
				selected = population.get(rand.nextInt(population.size()));
			
			auxillaryPopulation.add(selected);
		}
		
		Collections.sort(auxillaryPopulation);
		
		return auxillaryPopulation.get(0);
	}

}
