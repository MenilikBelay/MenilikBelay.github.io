package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcEngine
 */
@WebServlet("/calculate")
public class CalcEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcEngine() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = "<html><head><title>Calculator</title></head>"
				+ "<body>"
				+ "<form method='POST'>"
				+ "<input type='number' name='add1' /> + <input type='number' name='add2'/>"
				+ "<br />"
				+ "<input type='number' name='mul1' /> * <input type='number' name='mul2'/>"
				+ "<br />"
				+ "<input type='submit'/>"
				+ "</form>"
				+ "</body>"
				+ "</html>";
		PrintWriter out = response.getWriter();
		out.print(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String add1 = request.getParameter("add1");
		String add2 = request.getParameter("add2");
		String mul1 = request.getParameter("mul1");
		String mul2 = request.getParameter("mul2");
		StringBuilder builder = new StringBuilder();
		builder.append("<html><head><title>Calculator</title></head>");
		builder.append("<body>");
		if (add1 != null && add2 != null) {
			builder.append(add1);
			builder.append(" + ");
			builder.append(add2);
			builder.append(" = ");
			builder.append((Integer.valueOf(add1) + Integer.valueOf(add2)));
		}
		builder.append(System.getProperty("line.separator"));	// IDK why it don't work
		if (mul1 != null && mul2 != null) {
			builder.append(mul1);
			builder.append(" * ");
			builder.append(mul2);
			builder.append(" = ");
			builder.append((Integer.valueOf(mul1) * Integer.valueOf(mul2)));
		}
		builder.append("</body>");
		builder.append("</html>");
		out.print(builder.toString());
	}

}
