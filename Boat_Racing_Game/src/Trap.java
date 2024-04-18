import java.util.Random;

public class Trap extends RiverElement{
	
	
	public Trap() {
		setStrength(generateStrength());
		super.setSymbol('#');
	}
	
	@Override
    public void setStrength(int strength) {
		super.setStrength(-strength);
    }
    
	public int generateStrength() {
		return new Random().nextInt(Weather.getMaxTrapStrength()) + 1;
		}
    
    
}
