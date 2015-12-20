package p1018;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<Branch> data;
	private static int safeCount = 0;

	protected static void solution(Scanner in, PrintWriter out) {
		data = new ArrayList<>();
		maxSumm = 0;
		int n = in.nextInt();
		int q = in.nextInt();
		safeCount = q;
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
		buildDown(root);

		ArrayList<Branch> front = new ArrayList<>();
		front.add(root);
		skip = new HashSet<>();
		findWay(0, front, 0);

		out.println(maxSumm + "");
	}

	private static int maxSumm;
	private static Set<Branch> skip;

	private static void findWay(int count, ArrayList<Branch> front, int s) {
		if (count > safeCount) {
			return;
		}
		if (count == safeCount) {
			if (maxSumm < s) {
				maxSumm = s;
			}
			return;
		}

		for (int i = 0; i < front.size(); i++) {
			Branch b = front.get(i);
			if (skip.contains(b)) {
				continue;
			}
			if (b.left != null && !b.left.checked) {
				count += 1;
				front.add(b.left);
				s += b.left.weight;
				b.left.checked = true;
				findWay(count, front, s);
				b.left.checked = false;
				s -= b.left.weight;
				front.remove(count);
				count -= 1;
			}
			if (b.right != null && !b.right.checked) {
				count += 1;
				front.add(b.right);
				s += b.right.weight;
				b.right.checked = true;
				if (b.left.checked) {
					skip.add(b);
				}
				findWay(count, front, s);
				if (b.left.checked) {
					skip.remove(b);
				}
				b.right.checked = false;
				s -= b.right.weight;
				front.remove(count);
				count -= 1;
			}
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

	private static class Branch {
		private int start, end, weight;
		boolean checked = false;
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
