package p1039;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		new Solution().solution(in, out);

		out.flush();
	}

	private List<Emploee> emploees = new ArrayList<>();

	private Map<Integer, LinkedList<Emploee>> levels = new HashMap<>();

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

		Emploee root = getRoot();

		int level = 0;
		LinkedList<Emploee> levelList = new LinkedList<>();
		levelList.add(root);
		while (!levelList.isEmpty()) {
			levels.put(level, levelList);

			level++;
			LinkedList<Emploee> newLevelList = new LinkedList<>();
			for (Emploee emploee : levelList) {
				newLevelList.addAll(emploee.childs);
			}
			levelList = newLevelList;
		}

		int summ = getSumm(level);
		out.println(summ);
	}

	private Emploee getRoot() {
		Emploee e = emploees.get(0);
		while (e.parent != null) {
			e = e.parent;
		}
		return e;
	}

	private int getSumm(int maxLevel) {
		int summ = 0;
		while (maxLevel >= 0) {
			LinkedList<Emploee> list = levels.get(maxLevel);
			Emploee unchecked = null;
			while (list != null && !list.isEmpty()) {
				unchecked = list.pollFirst();
				if (unchecked.checked) {
					unchecked = null;
				} else {
					break;
				}
			}
			if (unchecked == null) {
				maxLevel--;
				continue;
			}

			summ += getSumm(unchecked);
		}
		return summ;
	}

	private int getSumm(Emploee unchecked) {
		if (unchecked.parent == null) {
			return unchecked.rating;
		}

		int sumOfChild = 0;
		for (Emploee emploee : unchecked.parent.childs) {
			sumOfChild += emploee.rating;
			emploee.checked = true;
		}
		if (sumOfChild > unchecked.parent.rating) {
			unchecked.parent.checked = true;
			return sumOfChild;
		} else {
			// TODO:
			unchecked.parent.checked = true;
			return unchecked.parent.rating;
		}
	}

	private static class Emploee {
		int index = 0;
		int rating = 0;
		boolean checked = false;

		private Emploee parent;
		private List<Emploee> childs = new ArrayList<>();

		@Override
		public String toString() {
			return (index + 1) + " (" + rating + ")";
		}

		public void addChild(Emploee emploee) {
			this.childs.add(emploee);
			emploee.parent = this;
		}
	}
}
