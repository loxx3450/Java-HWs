package com.loxx3450.hw_13_02_25.task2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servletTask2", value = "/servlet-task2")
public class ServletTask2 extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {
			out.println("<html><body>");

			int number1 = Integer.parseInt(request.getParameter("number1"));
			int number2 = Integer.parseInt(request.getParameter("number2"));
			int number3 = Integer.parseInt(request.getParameter("number3"));
			OperationType operationType = OperationType.fromString(request.getParameter("operation"));

			out.println("<p>Number 1: " + number1 + "</p>");
			out.println("<p>Number 2: " + number2 + "</p>");
			out.println("<p>Number 3: " + number3 + "</p>");

			switch (operationType) {
				case Max -> calculateMax(number1, number2, number3, out);
				case Min -> calculateMin(number1, number2, number3, out);
				case Avg -> calculateAvg(number1, number2, number3, out);
			}

			out.println("</body></html>");
		}
	}

	private void calculateMax(int number1, int number2, int number3, PrintWriter out) {
		int max = Math.max(Math.max(number1, number2), number3);

		out.println("<b>Max: " + max + "</b>");
	}

	private void calculateMin(int number1, int number2, int number3, PrintWriter out) {
		int min = Math.min(Math.min(number1, number2), number3);

		out.println("<b>Min: " + min + "</b>");
	}

	private void calculateAvg(int number1, int number2, int number3, PrintWriter out) {
		double sum = number1 + number2 + number3;
		double avg = sum / 3;

		out.println("<b>Avg: " + avg + "</b>");
	}
}
