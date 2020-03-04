package models;

public class Quiz {
	private int questionNumber = 0;
	private int score = 0;
	private String question;	// dummy variable that is never set [to make POJO]
	private String hint;	// another dummy data 
	private String grade;	// another dummy data 
	private int trial = 10;	// holds how many times the user has tried on a single question [start with full marks]
	
	private void resetTrial () {
		trial = 10;
	}
	
	private void decreaseTrial () {
		trial /= 2;
	}
	
	public String getHint () {
		hint = QuizPool.getHint(questionNumber);
		return hint;
	}
	
	public String getGrade () {
		grade = calculateGrade();
		return grade;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public String getQuestion() {
		question = QuizPool.getQuestion(questionNumber);
		return question;
	}
	
	/**
	 * Show answer because user didn't answer the question
	 * @return
	 */
	public boolean showAnswer() {
		return trial == 1;
	}
	
	public String getAnswer () {
		if (trial != 1) return null;
		return String.valueOf(QuizPool.showAnswer(questionNumber));
	}

	public void grade(int answer) {
		if (QuizPool.checkAnswer(questionNumber, answer)) {
			if (trial != 1)	// if trial is 1, we showed the answer
				score += trial;
		} else {	// check if this is the last trial 
			decreaseTrial();
			if (trial != 0) {
				return;
			}
		}
		resetTrial();
		questionNumber++;
	}

	public int getScore() {
		return score;
	}

	/**
	 * Returns the number of questions attempted
	 * 
	 * @return the number of questions attempted
	 */
	public int getAttempts() {
		return questionNumber;
	}

	/**
	 * Is the quiz over?
	 * 
	 * @return True if it is over, false if not
	 */
	public boolean hasFinished() {
		return questionNumber == QuizPool.getNumberOfQuestions();
	}
	
	/**
	 * Calculate grade based on criteria 
	 * @return
	 */
	private String calculateGrade() {
		if (score >= 45 && score <= 50) {
			return "A";
		} else if (score >= 35 && score <= 44) {
			return "B";
		} else if (score >= 25 && score <= 34) {
			return "C";
		}
		return "NC";
	}
}
