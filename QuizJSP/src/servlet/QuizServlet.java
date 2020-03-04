package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		Quiz quiz = (Quiz) request.getSession().getAttribute(QUIZ_SESSION);
		if (quiz == null) quiz = new Quiz();
		request.getSession().setAttribute(QUIZ_SESSION, quiz);
		// forward the request to JSP and test
		RequestDispatcher rd;
		if (!quiz.hasFinished()) {
			// process cookie for hint request and remove it 
			final Cookie [] cookies = request.getCookies();
			String hintRequest = null;
			for (int i = 0; i < cookies.length; i++) {
				if (cookies [i].getName().equalsIgnoreCase("hint")) {
					hintRequest = cookies[i].getValue();
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
			request.setAttribute("hint", hintRequest);
			rd = getServletContext().getRequestDispatcher("/QuizServlet.jsp");			
		} else { // case 3, user finished the quiz -- has finished quiz 
			rd = getServletContext().getRequestDispatcher("/QuizResult.jsp");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quiz quiz = (Quiz) request.getSession().getAttribute(QUIZ_SESSION);
		String answer = request.getParameter("guess");
		String ageString = request.getParameter("age");
		request.setAttribute("age", ageString);
		// check age string 
		try {
			Integer age = Integer.valueOf(ageString);
			// store anything if criteria is not fulfilled  
			if (age < 4 || age > 100) {
				request.setAttribute("age_exception", "Age should be between 4 and 100");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("age_exception", "Age is required and must be a number between 4 and 100");
		}
		if (quiz != null && answer != null) {
			quiz.grade(Integer.valueOf(answer));
			request.getSession().setAttribute(QUIZ_SESSION, quiz);
		}
		doGet(request, response);
	}

}
