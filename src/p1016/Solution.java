package p1016;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	protected static void solution(Scanner in, PrintWriter out) {
		Coube initCoube = new Coube(null);
		String start = in.next();
		String finish = in.next();
		initCoube.back = in.nextInt();
		initCoube.forward = in.nextInt();
		initCoube.top = in.nextInt();
		initCoube.right = in.nextInt();
		initCoube.butom = in.nextInt();
		initCoube.left = in.nextInt();
		initCoube.summ = initCoube.butom;

		initCoube.x = start.charAt(0) - 'a';
		initCoube.y = start.charAt(1) - '1';

		x = finish.charAt(0) - 'a';
		y = finish.charAt(1) - '1';

		findWay(initCoube);
		List<Coube> list = new ArrayList<>();
		out.print(minSumm.summ);
		while (minSumm != null) {
			list.add(minSumm);
			minSumm = minSumm.prev;
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			out.print(" " + list.get(i).getXY());
		}
	}

	private static int x, y;
	private static Coube minSumm = null;
	private static Map<Coube, Integer> possibleCases = new HashMap<>();

	private static void findWay(Coube c) {
		if (c == null) {
			return;
		}
		// System.out.println(c.x + " " + c.y);
		if (minSumm != null && c.summ >= minSumm.summ) {
			return;
		}
		Integer summ = possibleCases.get(c);
		if (summ != null && summ <= c.summ) {
			return;
		}
		if (c.x == x && c.y == y) {
			minSumm = c;
		}
		possibleCases.put(c, c.summ);
		findWay(c.forward());
		findWay(c.left());
		findWay(c.back());
		findWay(c.right());
	}

	private static class Coube {
		int back, forward, left, right, top, butom;
		Coube prev;
		int summ = 0;
		int x, y;

		private Coube(Coube prev) {
			this.prev = prev;
		}

		private Coube forward() {
			Coube next = new Coube(this);
			next.left = left;
			next.right = right;
			next.butom = forward;
			next.forward = top;
			next.top = back;
			next.back = butom;
			next.summ = summ + forward;
			next.y = y + 1;
			next.x = x;
			if (next.y >= 8) {
				return null;
			}
			return next;
		}

		private Coube back() {
			Coube next = new Coube(this);
			next.left = left;
			next.right = right;
			next.butom = back;
			next.forward = butom;
			next.top = forward;
			next.back = top;
			next.summ = summ + back;
			next.y = y - 1;
			next.x = x;
			if (next.y < 0) {
				return null;
			}

			return next;
		}

		private Coube left() {
			Coube next = new Coube(this);
			next.forward = forward;
			next.back = back;
			next.left = top;
			next.right = butom;
			next.butom = left;
			next.top = right;
			next.summ = summ + left;
			next.y = y;
			next.x = x - 1;
			if (next.x < 0) {
				return null;
			}

			return next;
		}

		private Coube right() {
			Coube next = new Coube(this);
			next.forward = forward;
			next.back = back;
			next.left = butom;
			next.right = top;
			next.butom = right;
			next.top = left;
			next.summ = summ + right;
			next.y = y;
			next.x = x + 1;
			if (next.x >= 8) {
				return null;
			}
			return next;
		}

		private String getXY() {
			return ((char) (x + 'a')) + "" + ((char) (y + '1'));
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Coube) {
				Coube c = (Coube) obj;
				return c.x == x && c.y == y && back == c.back && forward == c.forward && c.left == left
						&& c.right == right && top == c.top && butom == c.butom;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return x + y + left + right + top + butom + forward + butom;
		}

	}
}
