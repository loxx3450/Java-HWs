package com.loxx3450.hw_13_02_25.task4;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@WebServlet(name = "servletTask4", value = "/servlet-task4")
public class ServletTask4 extends HttpServlet {
	private static final Set<Character> VOWELS = Set.of(
		'A', 'E', 'I', 'O', 'U'
	);
	private static final Set<Character> CONSONANTS = Set.of(
		'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'
	);
	private static final Set<Character> PUNCTUATION_MARKS = Set.of(
		'.', ',', '!', '?', ':', ';', '-', '_', '(', ')', '[', ']', '{', '}',
		'\'', '\"', '/', '\\', '|', '@', '#', '$', '%', '^', '&', '*', '+', '=',
		'<', '>', '`', '~'
	);


	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		try (PrintWriter out = response.getWriter()) {
			out.println("<html><body>");

			String text = request.getParameter("text");

			if (text == null || text.trim().isEmpty()) {
				out.println("<p><b>No text provided. Please enter some text.</b></p>");
				return;
			}

			out.println("<p>Your text: <b>" + text + "</b></p>");

			analyzeTextOnVowels(text, out);
			out.println("<br><br>");
			analyzeTextOnConsonants(text, out);
			out.println("<br><br>");
			analyzeTextOnPunctuation(text, out);
			out.println("<br><br>");

			out.println("</body></html>");
		}
	}

	private void analyzeTextOnVowels(String text, PrintWriter out) {
		Set<Character> foundVowels = new HashSet<>();
		int counter = 0;

		for (char c : text.toUpperCase().toCharArray()) {
			if (VOWELS.contains(c)) {
				foundVowels.add(c);
				counter++;
			}
		}

		out.println("<p>Number of vowels: " + counter + "</p>");
		out.println("<p>Vowels(unique): </p>");
		printUnorderedList(out, foundVowels);
	}

	private void analyzeTextOnConsonants(String text, PrintWriter out) {
		Set<Character> foundConsonants = new HashSet<>();
		int counter = 0;

		for (char c : text.toUpperCase().toCharArray()) {
			if (CONSONANTS.contains(c)) {
				foundConsonants.add(c);
				counter++;
			}
		}

		out.println("<p>Number of consonants: " + counter + "</p>");
		out.println("<p>Consonants(unique): </p>");
		printUnorderedList(out, foundConsonants);
	}

	private void analyzeTextOnPunctuation(String text, PrintWriter out) {
		Set<Character> foundPunctuations = new HashSet<>();
		int counter = 0;

		for (char c : text.toCharArray()) {
			if (PUNCTUATION_MARKS.contains(c)) {
				foundPunctuations.add(c);
				counter++;
			}
		}

		out.println("<p>Number of punctuations: " + counter + "</p>");
		out.println("<p>Punctuation marks(unique): </p>");
		printUnorderedList(out, foundPunctuations);
	}

	private <T> void printUnorderedList(PrintWriter out, Collection<T> collection) {
		out.println("<ul>");
		collection.forEach((item) -> out.println("<li>" + item + "</li>"));
		out.println("</ul>");
	}
}
