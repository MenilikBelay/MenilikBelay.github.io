package models;

public class Quiz {
	private int questionNumber = 0;
	private int score = 0;
	
	public String getQuestion () {
		return QuizPool.getQuestion(questionNumber);
	}
	
	public void grade (int answer) {
		if (QuizPool.checkAnswer(questionNumber, answer)) {
			score++;
		} 
		questionNumber++;
	}
	
	public int getScore () {
		return score;
	}
	
	/**
	 * Returns the number of questions attempted 
	 * @return the number of questions attempted 
	 */
	public int getAttempts () {
		return questionNumber;
	}
	
	/**
	 * Is the quiz over? 
	 * @return True if it is over, false if not
	 */
	public boolean hasFinished () {
		return questionNumber == QuizPool.getNumberOfQuestions();
	}
}
