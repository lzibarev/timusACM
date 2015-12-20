package p1018;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
	private static List<Branch> dataInput;
	private static int removeCount = 0;

	protected static void solution(Scanner in, PrintWriter out) {
		data = new ArrayList<>();
		dataInput = new ArrayList<>();
		minSumm = Integer.MAX_VALUE;
		int summ = 0;
		int n = in.nextInt();
		int q = in.nextInt();
		removeCount = n - q - 1;
		Map<Integer, Integer> count = new HashMap<>();
		for (int i = 1; i < n; i++) {
			Branch b = new Branch();
			b.start = in.nextInt();
			b.end = in.nextInt();
			b.weight = in.nextInt();
			summ += b.weight;
			data.add(b);
			dataInput.add(b);
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
		buildDown(root);

		List<Branch> checked = new ArrayList<>();
		findWay(checked, 0);

		out.println((summ - minSumm) + "");
	}

	private static int minSumm;

	private static void findWay(List<Branch> checked, int s) {
		if (checked.size() == removeCount) {
			if (minSumm > s) {
				minSumm = s;
			}
			return;
		}
		if (s >= minSumm) {
			return;
		}
		for (Branch next : data) {
			if (checked.contains(next)) {
				continue;
			}
			if (next.left != null && !checked.contains(next.left)) {
				continue;
			}
			if (next.right != null && !checked.contains(next.right)) {
				continue;
			}
			checked.add(next);
			s += next.weight;
			findWay(checked, s);
			checked.remove(next);
			s -= next.weight;
		}
	}

	private static void buildDown(Branch root) {
		for (Branch b : data) {
			if (b == root) {
				continue;
			}
			if (b.start == root.end) {
				root.add(b);
				buildDown(b);
			} else if (b.end == root.end) {
				b.changeStartAndEnd();
				root.add(b);
				buildDown(b);
			}
		}
	}

	private static class Input {
		private int start, end, weight;
	}

	private static class Branch {
		private int start, end, weight;

		private Branch parent, left, right;

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
