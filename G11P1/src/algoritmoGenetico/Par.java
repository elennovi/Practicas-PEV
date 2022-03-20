package algoritmoGenetico;

public class Par {
	private int pos;
	private double fitness;
	
	// El constructor para crear el par
	public Par(int pos, double fitness) {
		this.pos = pos;
		this.fitness = fitness;
	}
	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public boolean equals(Par p2) {
		return p2.getFitness() == fitness;
	}
	
	public boolean greaterThan(Par p2) {
		return fitness > p2.getFitness();
	}
	
}
