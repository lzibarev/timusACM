package p1039;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		new Solution().solution(in, out);

		out.flush();
	}

	private List<Emploee> emploees = new ArrayList<>();

	protected void solution(Scanner in, PrintWriter out) {
		int count = in.nextInt();
		emploees = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Emploee e = new Emploee();
			e.index = i;
			e.rating = in.nextInt();
			emploees.add(e);
		}
		while (true) {
			int child = in.nextInt();
			int parent = in.nextInt();
			if (child == 0 && parent == 0) {
				break;
			}
			emploees.get(parent - 1).addChild(emploees.get(child - 1));
		}

		int summ = 0;
		out.println(summ);
	}

	private static class Emploee {
		int index = 0;
		int rating = 0;

		private Emploee parent;
		private List<Emploee> childs = new ArrayList<>();

		@Override
		public String toString() {
			return index + " (" + rating + ")";
		}

		public void addChild(Emploee emploee) {
			this.childs.add(emploee);
			emploee.parent = this;
		}
	}
}
