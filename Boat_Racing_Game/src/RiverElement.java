

public class RiverElement {
	
	private int strength;
	private char symbol;
	
	public RiverElement() {
		
	}
	
	// strength
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return String.format("Strength = %d, Symbol = %c", strength, symbol);
	}
	
	
	
}
