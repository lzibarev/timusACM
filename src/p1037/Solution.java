package p1037;

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

	protected void solution(Scanner in, PrintWriter out) {
		memoty.add(null);
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] values = line.split(" ");
			if (values.length == 2) {
				out.println(initMemory(values[0]));
			} else {
				out.println(accessMemory(values[0], values[2]));
			}
		}
	}

	private List<Integer> memoty = new ArrayList<>();

	private int initMemory(String timeStr) {
		int time = Integer.parseInt(timeStr);
		int timeLimit = time - 600;
		int index = 0;
		for (int i = 1; i < memoty.size(); i++) {
			if (memoty.get(i) != null) {
				if (memoty.get(i) <= timeLimit) {
					index = i;
					break;
				}
			}
		}
		if (index == 0) {
			index = memoty.size();
			memoty.add(time);
		} else {
			memoty.set(index, time);
		}
		return index;
	}

	private String accessMemory(String timeStr, String blockStr) {
		int time = Integer.parseInt(timeStr);
		int timeLimit = time - 600;
		int index = Integer.parseInt(blockStr);
		if (index >= memoty.size()) {
			return "-";
		}
		Integer value = memoty.get(index);
		if (value == null) {
			return "-";
		}
		if (value <= timeLimit) {
			return "-";
		}
		memoty.set(index, time);
		return "+";
	}

}
