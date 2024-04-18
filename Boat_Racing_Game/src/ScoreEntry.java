
public class ScoreEntry {
        private String winner;
        private int score;
        private String weather;

        public ScoreEntry(String winner, int score, String weather) {
            this.winner = winner;
            this.score = score;
            this.weather = weather;
        }
        
        public String getWeather() {
			return weather;
		}

		public void setWeather(String weather) {
			this.weather = weather;
		}
		
		public String getWinner() {
            return winner;
        }

		public void setWinner(String winner) {
			this.winner = winner;
		}

		public int getScore() {
	        return score;
	    }

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return String.format("player = %s, score = %d, weather = %s", winner, score, weather);
		}
        
    }
