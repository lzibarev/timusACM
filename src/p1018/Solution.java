package p1018;

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

	private static List<Branch> data;
	private static List<Branch> dataInput;
	private static Branch root = new Branch();
	private static int q = 0;

	protected static void solution(Scanner in, PrintWriter out) {
		data = new ArrayList<>();
		dataInput = new ArrayList<>();
		minSumm = Integer.MAX_VALUE;
		int summ = 0;
		int n = in.nextInt();
		q = in.nextInt();
		for (int i = 1; i < n; i++) {
			Branch b = new Branch();
			b.start = in.nextInt();
			b.end = in.nextInt();
			b.weight = in.nextInt();
			summ += b.weight;
			data.add(b);
			dataInput.add(b);
		}

		buildDown();

		List<Branch> checked = new ArrayList<>();
		findWay(checked, 0);

		out.println((summ - minSumm) + "");
	}

	private static int minSumm;

	private static void findWay(List<Branch> checked, int s) {
		if (checked.size() == q) {
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

	private static void buildDown() {
		List<Branch> leaves = new ArrayList<>();
		Map<Integer, Integer> count = new HashMap<>();
		// Получить все листья
		for (Branch l : dataInput) {
			boolean emptyStart = true;
			boolean emptyEnd = true;
			for (Branch b2 : dataInput) {
				if (b2 == l) {
					continue;
				}
				if (l.start == b2.start || l.start == b2.end) {
					emptyStart = false;
				}
				if (l.end == b2.start || l.end == b2.end) {
					emptyEnd = false;
				}
			}
			if (emptyStart) {
				l.changeStartAndEnd();
				leaves.add(l);
			} else if (emptyEnd) {
				leaves.add(l);
			}
			count.put(l.start, count.getOrDefault(l.start, 0) + 1);
			count.put(l.end, count.getOrDefault(l.end, 0) + 1);

		}
		// вырезать каждый лист указав у него парента
		for (Branch l : leaves) {
			for (Branch b2 : dataInput) {
				if (leaves.contains(b2)) {// парент не может быть листом
					continue;
				}
				if (l.start == b2.end || l.start == b2.start) {
					b2.add(l);
					break;
				}
			}
		}
		// удалить листья
		for (Branch l : leaves) {
			if (l.parent.left == null || leaves.contains(l.parent.left)) {
				if (l.parent.right == null || leaves.contains(l.parent.right)) {
					dataInput.remove(l);
				}
			}
		}

		if (dataInput.size() != 0) {
			buildDown();
		} else {
			for (Branch l : leaves) {
				root.add(l);
			}
		}

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

		private int count() {
			return (left == null ? 0 : 1) + (right == null ? 0 : 1);
		}

		@Override
		public String toString() {
			return start + " " + end + " " + weight;
		}
	}

}
