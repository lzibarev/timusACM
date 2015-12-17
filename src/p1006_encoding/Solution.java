package p1006_encoding;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<ArrayList<Integer>> inputData;
	private static List<Result> result;

	protected static void solution(Scanner in, PrintWriter out) {
		inputData = new ArrayList<>();
		result = new ArrayList<>();
		while (in.hasNext()) {
			String line = in.nextLine();
			ArrayList<Integer> row = new ArrayList<>();
			line.chars().forEach(c -> row.add(c));
			inputData.add(row);
		}
		Result r = hasAnyOnTop();
		print();
		while (r != null) {
			result.add(r);
			removeFromTop(r);
			// print();
			r = hasAnyOnTop();
		}

		out.println(result.size());
		for (int i = result.size() - 1; i >= 0; i--) {
			r = result.get(i);
			out.println(r.p.x + " " + r.p.y + " " + r.length);
		}
	}

	// '|', '*'
	private static List<Character> leftRight = Arrays.asList((char) 179, '|', 'a');
	// '*'
	private static List<Character> topButtom = Arrays.asList((char) 196, '-', 'a');

	private static void print() {
		for (List<Integer> row : inputData) {
			for (Integer i : row) {
				System.out.print(i.intValue() + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static Result hasAnyOnTop() {
		Point p = new Point();
		p.x = 0;
		for (List<Integer> row : inputData) {
			p.y = 0;
			for (Integer i : row) {
				if (i == 218 || i == 9484) {
					for (int length = 1; length <= row.size() - p.y && length <= inputData.size() - p.x; length++) {
						int end = inputData.get(p.x + length).get(p.y + length);
						if ((char) end == 217 || end == 9496) {
							boolean isCorrect = true;
							for (int tempLength = 1; tempLength < length; tempLength++) {
								int left = inputData.get(p.x + tempLength).get(p.y);
								int right = inputData.get(p.x + tempLength).get(p.y + length);
								int top = inputData.get(p.x).get(p.y + tempLength);
								int buttom = inputData.get(p.x + length).get(p.y + tempLength);
								if (!leftRight.contains((char) left) && !leftRight.contains((char) right)) {
									isCorrect = false;
									break;
								}
								if (!topButtom.contains((char) top) && !topButtom.contains((char) buttom)) {
									isCorrect = false;
									break;
								}
							}
							if (isCorrect) {
								Result r = new Result();
								r.length = length + 1;
								r.p = p;
								return r;
							}
						}
					}
				}
				p.y++;
			}
			p.x++;
		}
		return null;
	}

	private static void removeFromTop(Result r) {
		for (int y = r.p.y; y < r.p.y + r.length; y++) {
			inputData.get(r.p.x).set(y, (int) '*');
			inputData.get(r.p.x + r.length - 1).set(y, (int) '*');
		}
		for (int x = r.p.x; x < r.p.x + r.length; x++) {
			inputData.get(x).set(r.p.y, (int) '*');
			inputData.get(x).set(r.p.y + r.length - 1, (int) '*');
		}
	}

	private static class Point {
		private int x, y;
	}

	private static class Result {
		private Point p;
		private int length;
	}
}
