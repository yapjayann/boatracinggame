
public class River {
	private static int riverLength = 100;
	private RiverElement[] riverElements = new RiverElement[riverLength + 1]; // +1 for the starting point
	
	// constructor
	public River() {
		generateRiverElements();
	}
	
	// river length
	public static int getRiverLength() {
		return riverLength;
	}
	
	public void generateRiverElements() {
		// populate the river with empty element
		for (int i = 0; i < riverElements.length; i++) {
			riverElements[i] = new Empty();
		}
		
		// add current element
		for (int i = 0; i < Weather.getCurrentQuantity(); i++) {
			riverElements[Rand.randElemLocation()] = new Current();
		}
		
		// add trap element
		for (int i = 0; i < Weather.getTrapQuantity(); i++) {
			riverElements[Rand.randElemLocation()] = new Trap();
		}
	}
	
	// to get the riverElements list
	public RiverElement[] getRiverElements() {
		return riverElements;
	}
		
	public int getRiverElementStrength(Player player) {
		return riverElements[player.getCurrentLocation()].getStrength();
	}
		
	public boolean isEmpty(Player player) {
		RiverElement riverElement = riverElements[player.getCurrentLocation()];
		if (riverElement instanceof Current) {
			System.out.printf("Woah, %s encounter a current! Current effect is %d.\n", player.getName(), riverElement.getStrength());
			return false;
		}
		else if (riverElement instanceof Trap) {
			System.out.printf("Woops, %s encounter a trap! Trap effect is %d.\n", player.getName(), riverElement.getStrength());
			return false;
		}
		else {
			return true;
		}
	}
	
	public void printRiver(Player[] players) {
		System.out.println();
		printNum();
		printBoundary();
		printRiverElements();
		printPlayersLocation(players);
	}
	
	// print the number label of the river 
	public void printNum() {
		for (int i = 0; i <= riverLength; i++) {
			if (i == 0) {
				System.out.print("            |");
				System.out.print(" "+i+" |");
			}
			else if (i<10) { // for one-digit number
				System.out.print(" "+i+" |");
			}
			else if (i >99) { // for three-digit number
				System.out.print(i+"|");
			}
			else { // for two-digit number
				System.out.print(i + " |");
			}
		}
		System.out.println();
	}
	
	// print the boundary between the numbers and river elements
	public void printBoundary() {
		for (int i = 1; i <= riverLength; i++) {
			if (i == 1) {
				System.out.print("            |");
				System.out.print("----");
			}
			System.out.print("----");
		}
		System.out.println();
	}
	
	// print river elements according to the river element array
	public void printRiverElements() {
		for (int i = 0; i <= riverLength; i++) {
			if (i == 0) {
				System.out.print("            |   |");
			}
			else {
				System.out.printf(" %c |", riverElements[i].getSymbol());
			}
		}
		System.out.println();
	}
	
	// print the location of all players in the array
	public void printPlayersLocation(Player[] players) {
		for (Player player : players) {
			for (int i = 0; i <= riverLength; i++) {
				if (i == 0) { // display the name of the player
					System.out.printf("%12s|", player.getName());
				}
				if(i == player.getCurrentLocation()) { // display the boat
					System.out.printf(" %c |", player.getBoat());
				}
				else {
					System.out.print(" _ |");
				}
			}
			System.out.println();
		}
	}
	
	@Override
	public String toString() {
		return String.format("");
	}
		
}
