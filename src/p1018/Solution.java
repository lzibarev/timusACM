package p1018;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<Branch> data = new ArrayList<>();

	protected static void solution(Scanner in, PrintWriter out) {
		int n = in.nextInt();
		int q = in.nextInt();
		Branch root = null;
		int summ = 0;
		for (int i = 1; i < n; i++) {
			Branch b = new Branch();
			b.start = in.nextInt();
			b.end = in.nextInt();
			b.weight = in.nextInt();
			summ += b.weight;
			data.add(b);
			if (root == null) {
				root = new Branch();
				root.start = 0;
				root.end = b.start;
			}
		}

		buildDown(root);

		for (int i = 0; i < q; i++) {
			List<Branch> leaves = new ArrayList<>();
			for (Branch branch : data) {
				if (branch.left == null && branch.right == null) {
					leaves.add(branch);
				}
			}
			Branch min = leaves.get(0);
			for (Branch branch : leaves) {
				if (min.weight > branch.weight) {
					min = branch;
				}
			}
			// remove min
			data.remove(min);
			min.parent.remove(min);
			summ -= min.weight;
			// System.out.println("remove " + min.toString());
		}
		out.println(summ + "");
	}

	private static void buildDown(Branch root) {
		for (Branch b2 : data) {
			if (root == b2) {
				continue;
			}
			if (root.end == b2.start) {
				root.add(b2);
				buildDown(b2);
			}
			if (root.end == b2.end) {
				b2.changeStartAndEnd();
				root.add(b2);
				buildDown(b2);
			}
			if (root.left != null && root.right != null) {
				return;
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

		private void remove(Branch b) {
			if (left == b) {
				left = null;
			}
			right = null;
		}

		@Override
		public String toString() {
			return start + " " + end + " " + weight;
		}
	}

}
