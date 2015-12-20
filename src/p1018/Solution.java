package p1018;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<Branch> data;

	protected static void solution(Scanner in, PrintWriter out) {
		data = new ArrayList<>();
		int n = in.nextInt();
		int q = in.nextInt();
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 1; i < n; i++) {
			Branch b = new Branch();
			b.start = in.nextInt();
			b.end = in.nextInt();
			b.weight = in.nextInt();
			data.add(b);
			count.put(b.start, count.getOrDefault(b.start, 0) + 1);
			count.put(b.end, count.getOrDefault(b.end, 0) + 1);
		}

		Branch root = new Branch();
		root.start = -1;
		root.end = -1;
		for (Entry<Integer, Integer> c : count.entrySet()) {
			if (c.getValue() == 2) {
				if (root.end == -1) {
					root.end = c.getKey();
				} else {
					(new int[1])[10] = 1;// throw error
				}
			}
		}
		leaves = new HashSet<>();
		buildDown(root);
		while (!leaves.isEmpty()) {
			HashSet<Branch> tmp = new HashSet<>();
			for (Branch b : leaves) {
				if (b.parent != null) {
					if (b.parent.countChild != 0) {
						tmp.add(b.parent);
					}
					b.parent.countChild += b.countChild + 1;
				}
			}
			leaves = tmp;
		}

		ArrayList<Branch> front = new ArrayList<>();
		front.add(root);
		long max = findWay(root, q + 1);

		out.println(max + "");
	}

	private static long findWay(Branch root, int count) {
		if (count == 0) {
			return 0;
		}
		count--;
		long max = 0;
		if (root.left != null) {
			for (int i = 0; i <= root.left.countChild + 1 && i <= count; i++) {
				long maxLeft = 0;
				long maxRight = 0;
				if (root.left != null) {
					maxLeft = findWay(root.left, i);
				}
				if (root.right != null) {
					maxRight = findWay(root.right, count - i);
				}
				if (maxLeft + maxRight > max) {
					max = maxLeft + maxRight;
				}
			}
		}
		return max + root.weight;
	}

	private static HashSet<Branch> leaves;

	private static void buildDown(Branch root) {
		boolean hasChild = false;
		for (Branch b : data) {
			if (b == root) {
				continue;
			}
			if (b.start == root.end) {
				root.add(b);
				buildDown(b);
				hasChild = true;
			} else if (b.end == root.end) {
				b.changeStartAndEnd();
				root.add(b);
				buildDown(b);
				hasChild = true;
			}
		}
		if (!hasChild) {
			leaves.add(root);
		}
	}

	private static class Branch {
		private int start, end, weight;
		private Branch parent, left, right;
		private int countChild = 0;

		private void changeStartAndEnd() {
			int tmp = start;
			start = end;
			end = tmp;
		}

		private void add(Branch b) {
			b.parent = this;
			if (left == null) {
				left = b;
			} else {
				right = b;
			}
		}

		@Override
		public String toString() {
			return start + " " + end + " " + weight;
		}
	}

}
