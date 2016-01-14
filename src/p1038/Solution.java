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
		boolean firstLetterOfSentence = true;
		while (in.hasNext()) {
			String text = in.nextLine();
			int end = 0;
			boolean inWord = false;
			while (end < text.length()) {
				char c = text.charAt(end);
				if (isCapital(c) || isLittle(c)) {
					if (firstLetterOfSentence) {
						if (isLittle(c)) {
							count++;
						}
						firstLetterOfSentence = false;
					}

					if (inWord) {
						if (isCapital(c)) {
							count++;
						}
					}
					inWord = true;
				} else {
					inWord = false;
					if (isTerminal(c)) {
						firstLetterOfSentence = true;
					}
				}
				end++;
			}
		}
		out.println(count);
	}

	private boolean isCapital(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private boolean isLittle(char c) {
		return c >= 'a' && c <= 'z';
	}

	private boolean isTerminal(char c) {
		return c == '.' || c == '!' || c == '?';
	}

}
