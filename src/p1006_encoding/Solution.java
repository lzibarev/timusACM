package p1006_encoding;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("ISO-8859-1")));
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
		// print();
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

	private static List<Character> leftRight = Arrays.asList((char) CONST.l, 'a');
	private static List<Character> topButtom = Arrays.asList((char) CONST.t, 'a');

	private static void print() {
		for (List<Integer> row : inputData) {
			for (Integer i : row) {
				System.out.print((char) i.intValue() + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static Result hasAnyOnTop() {
		Point p = new Point();
		p.y = 0;
		for (List<Integer> row : inputData) {
			p.x = 0;
			for (Integer i : row) {
				boolean hasReal = false;
				if (i == CONST.tl || i == 'a') {
					hasReal |= i == CONST.tl;
					for (int length = 1; length < row.size() - p.x && length < inputData.size() - p.y; length++) {
						int end = inputData.get(p.y + length).get(p.x + length);
						if (end == CONST.br || end == 'a') {
							hasReal |= end == CONST.br;
							boolean isCorrect = true;
							int tr = inputData.get(p.y).get(p.x + length);
							if (tr == CONST.tr || tr == 'a') {
								hasReal |= tr == CONST.tr;
							} else {
								isCorrect = false;
							}
							int bl = inputData.get(p.y + length).get(p.x);
							if (bl == CONST.bl || bl == 'a') {
								hasReal |= bl == CONST.bl;
							} else {
								isCorrect = false;
							}
							for (int tempLength = 1; tempLength < length; tempLength++) {
								int left = inputData.get(p.y + tempLength).get(p.x);
								int right = inputData.get(p.y + tempLength).get(p.x + length);
								int top = inputData.get(p.y).get(p.x + tempLength);
								int buttom = inputData.get(p.y + length).get(p.x + tempLength);
								if (!leftRight.contains((char) left) && !leftRight.contains((char) right)) {
									hasReal |= left != 'a';
									hasReal |= right != 'a';
									isCorrect = false;
									break;
								}
								if (!topButtom.contains((char) top) && !topButtom.contains((char) buttom)) {
									hasReal |= top != 'a';
									hasReal |= buttom != 'a';
									isCorrect = false;
									break;
								}
							}
							if (isCorrect && hasReal) {
								Result r = new Result();
								r.length = length + 1;
								r.p = p;
								return r;
							}
						}
					}
				}
				p.x++;
			}
			p.y++;
		}
		return null;
	}

	private static class CONST {
		private static int tl = 218, t = 196, tr = 191;
		private static int l = 179, r = 179;
		private static int bl = 192, b = 196, br = 217;
	}

	private static void removeFromTop(Result r) {
		for (int y = r.p.x; y < r.p.x + r.length; y++) {
			inputData.get(r.p.y).set(y, (int) 'a');
			inputData.get(r.p.y + r.length - 1).set(y, (int) 'a');
		}
		for (int x = r.p.y; x < r.p.y + r.length; x++) {
			inputData.get(x).set(r.p.x, (int) 'a');
			inputData.get(x).set(r.p.x + r.length - 1, (int) 'a');
		}
	}

	private static class Point {
		private int y, x;
	}

	private static class Result {
		private Point p;
		private int length;
	}
}
