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
	
	/**
	 * Get question by question number. Indexing starts from 0
	 * @param index the index of the question we are interested on
	 * @return the question if it exists, null if index is not found
	 */
	static String getQuestion (int index) {
		if (index < 0 || index >= questions.length) return null;
		return questions[index];
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
}
