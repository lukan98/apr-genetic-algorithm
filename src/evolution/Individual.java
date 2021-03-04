package evolution;

public interface Individual extends Comparable<Individual> {
	
	public void setCostFunction(double value);
	
	public double getCostFunction();
	
	public double[] getRealGenes();
	
	public double getRealGene(int index);
	
	public int getSize();
	
	public void mutate(double mutationProbability);
	
	public BinaryCoder getBinaryCoder();
	
	public int[] getBinaryArray(int index);

}
