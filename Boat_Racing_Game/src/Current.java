import java.util.Random;

public class Current extends RiverElement{
	
	public Current() {
		setStrength(generateStrength());
		super.setSymbol('C');
	}
		
	public int generateStrength() {
		return new Random().nextInt(Weather.getMaxCurrentStrength()) + 1;
		}
	}
