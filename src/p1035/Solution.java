package p1035;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("ISO-8859-1")));
		new Solution().solution(in, out);

		out.flush();
	}

	private Graph inputData;
	int m;

	protected void solution(Scanner in, PrintWriter out) {
		int intputN = in.nextInt();
		int intputM = Integer.parseInt(in.nextLine().trim());
		m = intputM + 1;

		inputData = new Graph();
		for (int i = 0; i < intputN; i++) {
			String line = in.nextLine();
			for (int j = 0; j < intputM; j++) {
				char c = line.charAt(j);
				if (c == '.') {
					//
				}
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
				if (c == '.') {
					//
				}

				if (c == '\\' || c == 'X') {
					addLeftToRight(i, j, false);
				}
				if (c == '/' || c == 'X') {
					addRightToLeft(i, j, false);
				}
			}
		}

		int ans = 0;
		while (inputData.size() != 0) {
			long start1 = System.currentTimeMillis();
			List<Edge> graph = getGraph();
			// System.out.println(System.currentTimeMillis() - start1 + " :" + graph.size());
			int countOfOdd = 0;
			Map<Integer, Integer> count = new HashMap<>();
			for (Edge edge : graph) {
				count.putIfAbsent(edge.n1.index, 0);
				count.put(edge.n1.index, count.get(edge.n1.index) + 1);

				count.putIfAbsent(edge.n2.index, 0);
				count.put(edge.n2.index, count.get(edge.n2.index) + 1);
			}
			for (Integer i : count.values()) {
				if (i % 2 == 1) {
					countOfOdd++;
				}
			}
			int ansNew = countOfOdd / 2;
			if (ansNew == 0) {
				ansNew = 1;
			}
			ans += ansNew;
			long start2 = System.currentTimeMillis();
			inputData.remove(graph);
			// System.out.println(System.currentTimeMillis() - start2 + " : remove");
		}
		out.println(ans);
	}

	private List<Edge> getGraph() {
		List<Edge> list = new ArrayList<>();
		Edge edge = inputData.edges.iterator().next();
		list.add(edge);
		edge.checked = true;
		Try t1 = new Try();
		t1.e = edge;
		t1.n = edge.n1;

		Try t2 = new Try();
		t2.e = edge;
		t2.n = edge.n2;

		LinkedList<Try> queue = new LinkedList<>();
		queue.add(t1);
		queue.add(t2);
		while (!queue.isEmpty()) {
			Try current = queue.pollFirst();
			for (Edge e : current.n.edges) {
				if (e.checked) {
					continue;
				}
				if (e.front == current.e.front) {
					continue;
				}
				Try t = new Try();
				t.e = e;
				t.n = e.next(current.n);
				e.checked = true;
				list.add(e);
				queue.addLast(t);
			}

		}

		return list;
	}

	private static class Try {
		Node n;
		Edge e;
	}

	void addLeftToRight(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 1, (i + 1) * (m) + 1 + j + 1, front);
	}

	void addRightToLeft(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 2, (i + 1) * (m) + j + 1, front);
	}

	private static class Graph {
		static Map<Integer, Node> nodes = new HashMap<>();
		Set<Edge> edges = new HashSet<>();

		void addNode(int x, int y, boolean front) {
			Edge e = new Edge();
			e.front = front;
			nodes.putIfAbsent(x, new Node(x));
			nodes.putIfAbsent(y, new Node(y));
			e.n1 = nodes.get(x);
			e.n2 = nodes.get(y);
			e.n1.edges.add(e);
			e.n2.edges.add(e);
			edges.add(e);
		}

		public void remove(Collection<Edge> thread) {
			for (Edge edge : thread) {
				edges.remove(edge);
				edge.n1.edges.remove(edge);
				edge.n2.edges.remove(edge);
			}
		}

		int size() {
			return edges.size();
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
		boolean checked;

		@Override
		public int hashCode() {
			return n1.index + n2.index;
		}

		public Node next(Node n) {
			if (n1 != n) {// за одно поменяем ориентацию для удобства
				n2 = n1;
				n1 = n;
			}
			return n2;
		}

		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge) obj;
			if (e.front != front) {
				return false;
			}
			if (n1.index == e.n1.index && n2.index == e.n2.index) {
				return true;
			}
			if (n2.index == e.n1.index && n1.index == e.n2.index) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return n1 + " " + n2 + " " + front;
		}
	}

}
