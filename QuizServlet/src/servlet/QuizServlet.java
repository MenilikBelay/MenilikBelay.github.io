package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Quiz;

import java.io.PrintWriter;

/**
 * Servlet implementation class QuizServlet
 */
@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String QUIZ_SESSION = "QUIZ";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); 
		Quiz quiz = (Quiz) request.getSession().getAttribute(QUIZ_SESSION);
		if (quiz == null) quiz = new Quiz();
		StringBuilder output = new StringBuilder();
		output.append("<html>");
		output.append("<head><title>Quiz</title></head>");
		output.append("<body>");
		output.append("<h1>The Number Quiz</h1>");
		output.append("<p>Your current score is ");
		output.append(quiz.getScore());
		output.append("</p>");
		output.append("<br />");
		if (!quiz.hasFinished()) {
			// case 1, user starting the question -- zero attempt 
			// case 2, user in the middle of quiz -- has more than zero attempts and hasn't finished quiz
			output.append("<p>Guess the next number in the sequence</p>");
			output.append("<p>");
			output.append(quiz.getQuestion());
			output.append("</p>");
			output.append("<form method='POST'>");
			output.append("Your answer: <input type='number' name='guess'/>");
			output.append("<br />");
			output.append("<input type='submit' />");
			output.append("</form>");
		} else { // case 3, user finished the quiz -- has finished quiz 
			output.append("<p>You have completed the Number Quiz with a score of ");
			output.append(quiz.getScore());
			output.append(" out of ");
			output.append(quiz.getAttempts());
			output.append("</p>");
		}
		output.append("</body>");
		output.append("</html>");
		// return the appropriate things back
		request.getSession().setAttribute(QUIZ_SESSION, quiz);
		out.write(output.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute(QUIZ_SESSION);
		String answer = request.getParameter("guess");
		if (quiz != null && answer != null) {
			quiz.grade(Integer.valueOf(answer));
			request.getSession().setAttribute(QUIZ_SESSION, quiz);
		}
		doGet(request, response);
	}

}
