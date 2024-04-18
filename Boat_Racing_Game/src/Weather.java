
public class Weather {
	private static String weather;
	private static int currentQuantity;
	private static int maxCurrentStrength;
	private static int trapQuantity;
	private static int maxTrapStrength;
	

	public static void setWeatherCondition() {
		weather = generateWeather();
		if (weather.equals("sunny")) {
			setRiverElementCharacteristics(25, 20, 15, 3);
	    } 	     
	    else if (weather.equals("windy")) {
	    	setRiverElementCharacteristics(20, 20, 20, 10);
	    } 
	    else if (weather.equals("stormy")) {	         
	    	setRiverElementCharacteristics(15, 15, 25, 10);
	    }
	    
	}
	
	 private static String generateWeather() {            
		 switch (Rand.randWeather()) {	    		
			case 1: return "sunny";	    		
			case 2: return "windy";	    		
			case 3: return "stormy";	    		
		 	default: return "sunny";
		 }
	}
	
	public static String getWeather() {
		return weather;
	}
	
	private static void setRiverElementCharacteristics(int currentQuantity, int maxCurrentStrength, int trapQuantity, int maxTrapStrength) {
		Weather.currentQuantity = currentQuantity;
		Weather.maxCurrentStrength = maxCurrentStrength;
		Weather.trapQuantity = trapQuantity;
		Weather.maxTrapStrength = maxTrapStrength;
	}

	public static int getCurrentQuantity() {
		return currentQuantity;
	}

	public static int getMaxCurrentStrength() {
		return maxCurrentStrength;
	}

	public static int getTrapQuantity() {
		return trapQuantity;
	}
	
	public static int getMaxTrapStrength() {
		return maxTrapStrength;
	}
	
	public static void printWeather() {
		System.out.printf("The weather today is %s.\n", weather);
		switch(weather) {
			case "sunny":{
				System.out.println("The sun is shining brightly, providing a boost to your boat's speed!");
				break;
			}
			case "windy":{
				System.out.println("Strong winds are blowing, adding an element of unpredictability to your boat's movement!");
				break;
			}
			case "stormy":{
				System.out.println("A storm is raging, brace yourself as the turbulent conditions may challenge your boat's journey!");
				break;
			}
		}
	}
 }
	

