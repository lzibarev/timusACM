package p1037;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

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
	private TreeSet<Integer> freeList = new TreeSet<>();

	private int initMemory(String timeStr) {
		int time = Integer.parseInt(timeStr);
		int timeLimit = time - 600;

		Data currentD = null;
		while (!memInTime.isEmpty()) {
			Data d = memInTime.getFirst();
			if (d.time == time) {
				currentD = d;
				break;
			}
			if (d.time > timeLimit) {
				break;
			}
			memInTime.removeFirst();
			for (int m : d.list) {
				if (memoty.get(m) == d.time) {
					freeList.add(m);
				}
			}
		}
		int minMem;
		if (freeList.isEmpty()) {
			minMem = memoty.size();
			memoty.add(time);
		} else {
			minMem = freeList.pollFirst();
			memoty.set(minMem, time);
		}
		if (currentD == null) {
			currentD = new Data();
			currentD.time = time;
			memInTime.add(currentD);
		}
		currentD.list.add(minMem);
		return minMem;
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

		Data currentD = null;
		for (Data d : memInTime) {
			if (d.time == time) {
				currentD = d;
				break;
			}
			if (d.time > time) {
				break;
			}
		}
		if (currentD == null) {
			currentD = new Data();
			currentD.time = time;
			memInTime.add(currentD);
		}
		currentD.list.add(index);
		memoty.set(index, time);
		return "+";
	}

	private LinkedList<Data> memInTime = new LinkedList<Data>();

	private static class Data {
		int time;
		private ArrayList<Integer> list = new ArrayList<>();
	}

}
