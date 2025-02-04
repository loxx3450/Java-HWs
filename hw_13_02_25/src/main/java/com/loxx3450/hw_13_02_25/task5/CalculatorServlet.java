package com.loxx3450.hw_13_02_25.task5;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calculatorServlet", value = "/calculator-servlet")
public class CalculatorServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {
			out.println("<html><body>");

			int value1 = Integer.parseInt(request.getParameter("value1"));
			int value2 = Integer.parseInt(request.getParameter("value2"));
			OperationType operationType = OperationType.fromString(request.getParameter("operation"));

			out.println("<p>Value 1: " + value1 + "</p>");
			out.println("<p>Value 2: " + value2 + "</p>");

			double result = switch (operationType) {
				case Addition -> value1 + value2;
				case Subtraction -> value1 - value2;
				case Multiplication -> value1 * value2;
				case Division -> {
					if (value2 == 0) yield Double.NaN;
					yield (double) value1 / value2;
				}
				case Exponentiation -> Math.pow(value1, value2);
				case Percentage -> {
					if (value2 == 0) yield Double.NaN;
					yield (double) value1 / value2 * 100;
				}
			};

			out.println("<p>Result: " + result + "</p>");

			out.println("</body></html>");
		}
	}
}
