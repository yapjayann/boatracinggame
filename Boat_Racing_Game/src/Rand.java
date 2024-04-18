import java.util.Random;

public class Rand {
	private static int lastDiceRoll;
		
	public static void rollDice() {
		lastDiceRoll = new Random().nextInt(6) + 1;
	}
	
	public static int getLastDiceRoll() {
		return lastDiceRoll;
	}
	
	public static int randElemLocation() {
		return new Random().nextInt(River.getRiverLength()-1) + 1;
	}
	
	public static int randWeather() {
		return new Random().nextInt(3) + 1;
	}
	
}
