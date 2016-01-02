package p1035;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("ISO-8859-1")));
		new Solution().solution(in, out);

		out.flush();
	}

	private Graph inputData;
	int m, max;

	protected void solution(Scanner in, PrintWriter out) {
		long start0 = System.currentTimeMillis();
		int intputN = in.nextInt();
		int intputM = Integer.parseInt(in.nextLine().trim());
		m = intputM + 1;
		max = (intputM + 1) * (intputN + 1) + 1;
		inputData = new Graph();
		inputData.nodes = new Node[(intputN + 2) * (m + 1)];
		for (int i = 0; i < intputN; i++) {
			String line = in.nextLine();
			for (int j = 0; j < intputM; j++) {
				char c = line.charAt(j);
				if (c == '\\' || c == 'X') {
					addLeftToRight(i, j, true);
				}
				if (c == '/' || c == 'X') {
					addRightToLeft(i, j, true);
				}
			}
		}

		for (int i = 0; i < intputN; i++) {
			String line = in.nextLine();
			for (int j = 0; j < intputM; j++) {
				char c = line.charAt(j);
				if (c == '\\' || c == 'X') {
					addLeftToRight(i, j, false);
				}
				if (c == '/' || c == 'X') {
					addRightToLeft(i, j, false);
				}
			}
		}
		// System.out.println("prepare " + (System.currentTimeMillis() - start0));
		int ans = 0;
		long start1 = System.currentTimeMillis();

		// Удалить все циклы и выставить циклас номера
//		for (Node n : inputData.nodes) {
//			if (n == null) {
//				continue;
//			}
//			findAndRemoveCercle(n);
//		}

		// Пройтись и удалить любые - не обязательно самые быстрые маршруты при этом учесть какие циклы были достигнуты.

		// Прибавить все не достигнутые циклы
		for (int i = 0; i < max; i++) {
			Node start = inputData.nodes[i];
			if (start == null) {
				continue;
			}
			i--;
			long start2 = System.currentTimeMillis();
			List<Edge> graph = find(start);
			// System.out.println("find " + (System.currentTimeMillis() - start2));
			ans += getCount(graph);
			// System.out.println("count " + (System.currentTimeMillis() - start2));
			inputData.remove(graph);
			// System.out.println("remove " + (System.currentTimeMillis() - start2));
			// start = inputData.getStartNode();
			// System.out.println("iteration " + (System.currentTimeMillis() - start2));
		}
		// System.out.println("total " + (System.currentTimeMillis() - start1));
		out.println(ans);
		// System.out.println("test " + (System.currentTimeMillis() - start0));
	}

	private int getCount(List<Edge> graph) {
		int[] front = new int[max];
		int[] back = new int[max];
		for (Edge edge : graph) {
			if (edge.front) {
				front[edge.n1.index] += 1;
				front[edge.n2.index] += 1;
			} else {
				back[edge.n1.index] += 1;
				back[edge.n2.index] += 1;
			}
		}

		int cFront = 0;
		int cBack = 0;
		for (int i = 0; i < max; i++) {
			int countFront = front[i];
			int countBack = back[i];
			if (countFront == countBack) {
				continue;
			}
			if (countFront > countBack) {
				cFront += countFront - countBack;
			} else {
				cBack += countBack - countFront;
			}
		}
		// System.out.println(cFront + " " + cBack);
		int ans = (cFront + cBack) / 2;
		if (ans == 0) {
			ans = 1;
		}
		return ans;
	}

	void addLeftToRight(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 1, (i + 1) * (m) + 1 + j + 1, front);
	}

	void addRightToLeft(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 2, (i + 1) * (m) + j + 1, front);
	}

	private List<Edge> find(Node n) {
		LinkedList<Edge> queue = new LinkedList<>();
		List<Edge> list = new ArrayList<>();

		for (Edge edge : n.edges) {
			edge.setStart(n);
			queue.addLast(edge);
		}

		while (!queue.isEmpty()) {
			Edge e = queue.pollLast();
			if (e.processed) {
				continue;
			}
			e.processed = true;
			list.add(e);
			for (Edge edge : e.n2.edges) {
				if (!edge.processed) {
					edge.setStart(e.n2);
					queue.addLast(edge);
				}
			}
		}
		return list;
	}

	private static class Graph {
		Node[] nodes;

		void addNode(int x, int y, boolean front) {
			Edge e = new Edge();
			e.front = front;
			if (nodes[x] == null) {
				nodes[x] = new Node(x);
			}
			if (nodes[y] == null) {
				nodes[y] = new Node(y);
			}
			e.n1 = nodes[x];
			e.n2 = nodes[y];
			e.n1.edges.add(e);
			e.n2.edges.add(e);
		}

		private Node getStartNode() {
			Node any = null;
			for (Node n : nodes) {
				if (n == null) {
					continue;
				}
				any = n;
				int front = 0;
				int notFront = 0;
				for (Edge edge : n.edges) {
					front += edge.front ? 1 : 0;
					notFront += edge.front ? 0 : 1;
				}
				if (front != notFront) {
					return n;
				}
			}
			return any;
		}

		public void remove(Collection<Edge> thread) {
			for (Edge edge : thread) {
//				edge.n1.edges.remove(edge);
//				edge.n2.edges.remove(edge);
//				if (edge.n1.edges.isEmpty()) {
				nodes[edge.n1.index] = null;
//				}
//				if (edge.n2.edges.isEmpty()) {
				nodes[edge.n2.index] = null;
//				}
			}
		}

	}

	private static class Node {
		final int index;

		Node(int index) {
			this.index = index;
		}

		List<Edge> edges = new ArrayList<>();

		@Override
		public String toString() {
			return index + "";
		}
	}

	private static class Edge {
		Node n1, n2;
		boolean front;
		boolean processed;

		public void setStart(Node n) {
			if (n1 != n) {// поменяем ориентацию для удобства
				n2 = n1;
				n1 = n;
			}
		}

		@Override
		public String toString() {
			return n1 + " " + n2 + " " + front;
		}
	}

}
