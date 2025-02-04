package com.loxx3450.hw_13_02_25.task1;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "servletTask1", value = "/servlet-task1")
public class ServletTask1 extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {
			out.println("<html><body>");
			out.println("<i>Bad programmers worry about the code. <br>Good programmers worry about data structures and their relationships.</i>");
			out.println("</body></html>");
		}
	}
}