import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	Scanner input = new Scanner(System.in);
	
	private Leaderboard leaderboard;
	private int numPlayer;
	private Player[] players;
	private River river;
	private int totalRounds;
	private String winner;
	
	// constructor
	public Game() {
		leaderboard = new Leaderboard();
	}
	
    public void run() {
        // initialize values
        totalRounds = 0;
        winner = null;
        
        // Display game rules
        printRules();
        leaderboard.printScores();

        // create player objects
        setNumPlayer(askNumPlayer());
        createPlayers();

        // set the weather condition
        Weather.setWeatherCondition();
        
        // create river object with currents and traps
        river = new River();
        // display game board
        river.printRiver(players);
        
        // display weather
        Weather.printWeather();
        System.out.println("May the best captain win! Let the race begin!");

        while (winner == null) { // end the game when the winner is determined
            totalRounds++; // increment by 1 when each new round starts
            for (Player player : players) { // player to take turn in each round
                if (winner == null) {
                    player.askRollDice();
                    checkAndMove(player);
                } else { // stop passing the turn to the next player when the winner is determined by exiting the for loop
                    break;
                }
            }
        }

        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Winner is %s\n", winner);
        System.out.printf("Total rounds: %d\n", totalRounds);
        leaderboard.updateList(winner, totalRounds, Weather.getWeather());
        leaderboard.printScores();
}
	
	// number of player
    public int askNumPlayer() {
        int numPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
            	System.out.println();
                System.out.print("Enter the number of players: ");
                numPlayers = input.nextInt();
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException A) {
                System.out.println("An error occurred: Input should be an integer.");
                input.nextLine(); // Clear the invalid input from the Scanner
                // Loop will continue until a valid integer is entered
            }
        }

        return numPlayers;
    }
	
	public int getNumPlayer() {
		return numPlayer;
	}
	
	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}
	
	
	// create players and add into the array according to the number of player
	public void createPlayers() {
		players = new Player[numPlayer];
		for (int i = 0; i < numPlayer; i++) {
			players[i] = new Player();
		}
	}
	
	public void checkAndMove(Player player) {
		checkBoundary(player);
		checkRiverElement(player);
	}
	
	public void checkRiverElement(Player player) {
		// check if the player encounter any current or trap
		if (!river.isEmpty(player)) {
			player.setNextMove(river.getRiverElementStrength(player));
			if (!(checkBoundary(player))) {
				movePlayer(player); //move the player according to the strength of current or trap
			}
		}
		// to exit the recursive function if the nextMove is 0
		else if (player.getNextMove() != 0){
			movePlayer(player);
		}
	}
	
	public boolean checkBoundary(Player player) {
		if (player.checkReachRiverEnd()) {
			player.setCurrentLocation(River.getRiverLength()); // move the player to end point
			player.setNextMove(0);
			printPlayerMove(player);
			river.printRiver(players);
			winner = player.getName();
			return true;
		}
		// check if there is player exceed the start point due to trap
		else if (player.checkReachRiverStart()) {
			player.setCurrentLocation(0); // move the player to start point
			player.setNextMove(0);
			printPlayerMove(player);
			river.printRiver(players);
			return true;
		}
		else {
			return false;
		}
	}
	
	// move the player to the next location
	public void movePlayer(Player player) {
		player.move();
		player.setNextMove(0);
		printPlayerMove(player);
		river.printRiver(players);
		checkAndMove(player); // check if condition again after moving 
	}
	
	public void printPlayerMove(Player player) {
		System.out.printf("%s move to %d.\n", player.getName(), player.getCurrentLocation());
	}
	
	public void printRules() {
		System.out.println("WELCOME TO THE BOAT RACING GAME!");
		System.out.println();
        System.out.println("Game rules:\n1. The game can be played by one or more players, and not limited to just two players\n"
        		+ "2. At the beginning of the game, each player is allocated a boat.\n"
        		+ "3. A random weather condition (sunny, windy, stormy) will be set for each game.\n"
        		+ "4. Players take turns to throw a dice to determine how many steps their boats will move forward.\n"
        		+ "5. There will be traps and current scattered randomly in the river. When the player hits a trap, they will move backwards.\n"
        		+ "   If the player hits a current, their boat will move forward.\n"
        		+ "6. The number of steps needed to move forward and backwards depends on the strength of the trap and current, which will be affected by the weather.\n"
        		+ "7. The game ends when a player's boat reaches the end of the river.");
	}

}