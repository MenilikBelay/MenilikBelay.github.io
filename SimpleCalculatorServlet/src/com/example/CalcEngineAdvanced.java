package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcEngineAdvanced
 */
@WebServlet("/Calcad")
public class CalcEngineAdvanced extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalcEngineAdvanced() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = "<html><head><title>Calculator</title></head>" + "<body>" + "<form method='POST'>"
				+ "<input type='number' name='add1' /> + <input type='number' name='add2'/>"
				+ "= <input type='number' name='sum'/>" + "<br />"
				+ "<input type='number' name='mul1' /> * <input type='number' name='mul2'/>"
				+ "= <input type='number' name='product'/>" + "<br />" + "<input type='submit'/>" + "</form>"
				+ "</body>" + "</html>";
		PrintWriter out = response.getWriter();
		out.print(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String add1 = request.getParameter("add1");
		String add2 = request.getParameter("add2");
		String mul1 = request.getParameter("mul1");
		String mul2 = request.getParameter("mul2");
		String product = "";
		String sum = "";
		if (mul1 != null && mul2 != null)
			product = String.valueOf((Double.valueOf(mul1) * Double.valueOf(mul2)));
		if (add1 != null && add2 != null)
			sum= String.valueOf((Double.valueOf(add1) + Double.valueOf(add2)));
		add1 = add1 == null ? "" : add1;
		add2 = add2 == null ? "" : add2;
		mul1 = mul1 == null ? "" : mul1;
		mul2 = mul2 == null ? "" : mul2;
		String page = "<html><head><title>Calculator</title></head>" 
				+ "<body>" 
				+ "<form method='POST'>"
				+ "<input type='number' name='add1' value='" + add1
				+ "'/> + " 
				+ "<input type='number' name='add2' value='" + add2
				+ "'/>" 
				+ " = <input type='number' name='sum' value='" + sum
				+ "'/>" + "<br />"
				+ "<input type='number' name='mul1' value='" + mul1
				+ "'/> * <input type='number' name='mul2' value='" + mul2
				+ "'/>"
				+ "= <input type='number' name='product' value='" + product
				+ "'/>" + "<br />"
				+ "<input type='submit'/>" + "</form>" + "</body>" + "</html>";
		PrintWriter out = response.getWriter();
		out.print(page);
	}

}
