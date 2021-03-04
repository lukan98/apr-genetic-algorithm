package evolution;

public class BinaryCoder {
	
	private double lowerBound;
	private double upperBound;
	private int precision;
	private int noOfBits;
	
	public BinaryCoder(double lowerBound, double upperBound, int precision) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.precision = precision;
		
		this.noOfBits = (int) Math.ceil(Math.log(Math.floor( 1+(upperBound-lowerBound)*Math.pow(10, precision) )) / Math.log(2));
	}
	
	public double convertFromBinary(int binary) {
		return this.lowerBound + binary/(Math.pow(2, this.noOfBits)-1) * (this.upperBound-this.lowerBound);
	}
	
	public int convertToBinary(double real) {
		return (int) ((real - this.lowerBound)*(Math.pow(2, this.noOfBits)-1)/(this.upperBound-this.lowerBound));
	}

	public double getUpperBound() {
		return upperBound;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public int getPrecision() {
		return precision;
	}

	public int getNoOfBits() {
		return noOfBits;
	}

}
