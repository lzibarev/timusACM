package p1038;

import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		new Solution().solution(in, out);

		out.flush();
	}

	protected void solution(Scanner in, PrintWriter out) {
		int count = 0;
		boolean startOfSentence = true;
		while (in.hasNext()) {
			String text = in.nextLine();
			int start = 0;
			int end = 0;
			while (true) {
				while (end < text.length()) {
					char c = text.charAt(end);
					if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':' || c == '-') {
						break;
					}
					end++;
				}
				if (end < text.length()) {
					end++;
				}
				String word = text.substring(start, end);
				start = end;
				if (word.isEmpty()) {
					break;
				}
				word = word.trim();
				if (word.isEmpty()) {
					continue;
				}
				if (startOfSentence) {
					char c = word.charAt(0);
					if (!isCapital(c)) {
						count++;
						System.out.println("start with \"" + word + "\"");
					}
				}
				for (int i = 1; i < word.length(); i++) {
					char c = word.charAt(i);
					if (isCapital(c)) {
						count++;
						System.out.println("capital \"" + word + "\" " + c);
					}
				}
				startOfSentence = isTerminal(word.charAt(word.length() - 1));
			}
			// System.out.println("\"" + word + "\"");
		}
		out.println(count);
	}

	private boolean isCapital(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private boolean isTerminal(char c) {
		return c == '.' || c == '!' || c == '?';
	}

}
