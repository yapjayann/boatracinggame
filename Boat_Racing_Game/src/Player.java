import java.util.Scanner;

public class Player {
	Scanner input = new Scanner(System.in);
	
	private String name;
	private char boat;
	private int currentLocation;
	private int nextMove;

	// Constructor
	public Player() {
		setName(askName());
        setBoat(askBoat());
        setCurrentLocation(0);
        setNextMove(0);
	}

	// name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String askName() {
		System.out.print("Please enter your nickname (not more than 12 characters): ");
		String nameInput = input.nextLine();
		
		// ask player name again if the input is empty
		if (nameInput.length() == 0) {
			System.out.println("Your name should not be empty...");
			return askName(); 
		}
		// name cannot be more than 12 char
		else if (nameInput.length() < 13) {
			return nameInput;
		}
		else {
			return nameInput.substring(0, 12);
		}
	}			
	
	//boat
	public char getBoat() {
		return boat;
	}
	
	public void setBoat(char boat) {
		this.boat = boat;
	}
	
	public char askBoat() {
        System.out.print("Please choose a symbol as your boat: ");
        return input.next().charAt(0);
    }
	
	// currentLocation
	public int getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	// nextMove
	public void setNextMove(int nextMove) {
		this.nextMove = nextMove;
	}
	
	public int getNextMove() {
		return nextMove;
	}
	
	public void move() {
		currentLocation += nextMove;
	}
	
	// Prompt player to roll a dice with 1 - 6 and display the result
	public void askRollDice() {
		System.out.printf("\n\n%s, press ENTER to roll the dice >", getName());
		input.nextLine(); // to clear input buffer
		input.nextLine(); 
		Rand.rollDice();
		nextMove = Rand.getLastDiceRoll();
		System.out.printf("%s rolled a %d.\n", getName(), Rand.getLastDiceRoll());
	}
	
	// To check whether the boat exceed the start and end boundary
	public boolean checkReachRiverEnd() {
		return currentLocation + nextMove >= River.getRiverLength()? true:false;
	}
	
	public boolean checkReachRiverStart() {
		return currentLocation + nextMove < 0? true:false;
	}
	
	
	@Override
	public String toString() {
		return String.format("Name = %s, Boat = %c, Current Location = %d, Next Move = %d", name, boat, currentLocation, nextMove);
	}
}
