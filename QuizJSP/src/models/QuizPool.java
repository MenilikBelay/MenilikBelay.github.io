package models;

public class QuizPool {
	private static final String [] questions = {
		"3, 1, 4, 1, 5", 
		"1, 1, 2, 3, 5", 
		"1, 4, 9, 16, 25",
		"2, 3, 5, 7, 11", 
		"1, 2, 4, 8, 16"
	};
	private static final int [] answers = {
			9, 8, 36, 13, 32
	};
	private static final String [] hints = {
			"This is a number describing the center of pyramid", 
			"This is a well known series of numbers", 
			"When you perform a square of numbers to a sequence of numbers", 
			"As there is composite numbers, there is ...",
			"Think of some number raised to consecutive numbers"
	};
	
	/**
	 * Get question by question number. Indexing starts from 0
	 * @param index the index of the question we are interested on
	 * @return the question if it exists, null if index is not found
	 */
	static String getQuestion (int index) {
		if (index < 0 || index >= questions.length) return null;
		return questions[index];
	}
	
	static int showAnswer (int index) {
		if (index < 0 || index >= questions.length) throw new IllegalArgumentException();
		return answers[index];
	}
	
	/**
	 * Check if the provided answer is correct 
	 * @param questionIndex question number 
	 * @param answer the answer for the question
	 * @return true if the answer is correct, false if not
	 */
	static boolean checkAnswer (int questionIndex, int answer) {
		if (questionIndex < 0 || questionIndex >= questions.length) return false;
		return (answers[questionIndex] == answer);
	}
	
	/**
	 * Return the total number of questions the quiz has 
	 * @return the total number of questions the quiz has 
	 */
	static int getNumberOfQuestions () {
		return questions.length;
	}
	
	/**
	 * Return a hint for the given question index
	 * @param questionIndex question index 
	 * @return the hint for the question
	 */
	static String getHint (int questionIndex) {
		if (questionIndex < 0 || questionIndex >= questions.length) return null;
		return hints[questionIndex];
	}
}
