import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leaderboard {
    private ArrayList<ScoreEntry> topScores = new ArrayList<ScoreEntry>(); // ArrayList to store the top scores
    private Formatter fileWriter; //file writer for updating data
    private Scanner fileReader; 

    public Leaderboard() {
        File file = new File("TopScores.txt");
        try {
            file.createNewFile();
            // Create a new file named "TopScores.txt" if it doesn't exist
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception if any occurs
        }
        scanFile();
    }
    
    //scan any existing data on file
    private void scanFile() {
        try {
            fileReader = new Scanner(new File("TopScores.txt"));
            // Create a new Scanner object to read from the file

            int count = 0;
            while (fileReader.hasNextLine() && count < 5) {
                String tempName = fileReader.nextLine();
                int tempScore = Integer.parseInt(fileReader.nextLine());
                String tempWeather = fileReader.nextLine();

                ScoreEntry temp = new ScoreEntry(tempName, tempScore, tempWeather);
                topScores.add(temp);

                count++;
            }

            if (fileReader != null) {
                fileReader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error opening file.");
        } catch (NoSuchElementException e) {
            System.out.println("File improperly formed.");
        }
    }


    public void updateList(String winner, int score, String weather) {
    	ScoreEntry entry = new ScoreEntry(winner, score, weather);
    	topScores.add(entry);
    	
    	// Sort the scores in ascending order
    	Collections.sort(topScores, Comparator.comparingInt(ScoreEntry::getScore));
    	
    	//remove extra entries beyond top 5
    	if (topScores.size() > 5) {
            topScores.subList(5, topScores.size()).clear();
        }
    	updateFile();
    }
    
    
    private void updateFile() {
        try {
            fileWriter = new Formatter(new FileWriter("TopScores.txt"));
            for (ScoreEntry entry : topScores) {
                fileWriter.format("%s\n", entry.getWinner());
                fileWriter.format("%d\n", entry.getScore());
                fileWriter.format("%s\n",entry.getWeather());
            }
            fileWriter.close();
        } catch (IOException e) {
        	System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void printScores(){
    	System.out.println();
    	System.out.println("----------------Leaderboard----------------");
    	int position = 1;
    	for (ScoreEntry scoreEntry : topScores) {
    		System.out.printf("%d)", position++);
    		System.out.println(scoreEntry);
    	}
    }
    
    
    
}








