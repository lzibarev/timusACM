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
			Node node = inputData.getStartNode();
			maxThread = new HashSet<>();
			find(true, node, new HashSet<>());
			find(false, node, new HashSet<>(maxThread));
			inputData.remove(maxThread);
			ans++;
		}
		out.println(ans);
	}

	void addLeftToRight(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 1, (i + 1) * (m) + 1 + j + 1, front);
	}

	void addRightToLeft(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 2, (i + 1) * (m) + j + 1, front);
	}

	private Set<Edge> maxThread;

	private void find(boolean front, Node n, Set<Edge> e) {
		if (e.size() > maxThread.size()) {
			maxThread = new HashSet<>(e);
		}
		findAndRemoveCercle(front, n, n);
		for (Edge edge : n.edges) {
			if (edge.front == front && !e.contains(edge)) {
				e.add(edge);
				Node nextNode = edge.next(n);
				find(!front, nextNode, e);
				e.remove(edge);
			}
		}
	}

	int count = 0;

	private boolean findAndRemoveCercle(boolean front, Node root, Node n) {
		List<Edge> list = findCercle(front, root);
		if (list != null) {
			inputData.remove(list);
			for (Edge edge : list) {
				findAndRemoveCercle(!edge.front, edge.n2, edge.n2);
			}
		}
		return false;
	}

	private static class Ans {
		Node n;
		Edge e;
	}

	private List<Edge> findCercle(boolean front, Node root) {
		LinkedList<Ans> nextNodes = new LinkedList<>();
		Ans a = new Ans();
		a.n = root;
		a.e = null;
		nextNodes.add(a);
		while (!nextNodes.isEmpty()) {
			a = nextNodes.getFirst();
			Node n = a.n;
			for (Edge edge : n.edges) {
				Node nextNode = edge.next(n);
				if (edge.n2 == root) {
					List<Edge> list = new ArrayList<>();
					while (a.e != null) {

					}
					return list;
				}
				a = new Ans();
				a.n = nextNode;
				a.e = edge;
				nextNodes.addLast(a);
			}

			// if (edge.front == front && !list.contains(edge)) {
			// list.add(edge);
			// Node nextNode = edge.next(n);
			// if (edge.n2 == root) {
			// return true;
			// }
			// if (findCercle(!front, root, nextNode, list)) {
			// return true;
			// }
			// list.remove(list.size() - 1);
			// }
		}
		return null;
	}

	private boolean findCercleDeep(boolean front, Node root, Node n, Set<Edge> list) {
		for (Edge edge : n.edges) {
			if (edge.front == front && !list.contains(edge)) {
				list.add(edge);
				Node nextNode = edge.next(n);
				if (edge.n2 == root) {
					return true;
				}
				if (findCercleDeep(!front, root, nextNode, list)) {
					return true;
				}
				list.remove(list.size() - 1);
			}
		}
		return false;
	}

	private static class Graph {
		List<Edge> edges = new ArrayList<>();
		Map<Integer, Node> nodes = new HashMap<>();

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

		Node getStartNode() {
			for (Node n : nodes.values()) {
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
			return edges.get(0).n1;
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
