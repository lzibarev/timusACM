package p1035;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("ISO-8859-1")));
		new Solution().solution(in, out);

		out.flush();
	}

	private static int m, max;
	private Graph inputData;

	protected void solution(Scanner in, PrintWriter out) {
		int intputN = in.nextInt();
		int intputM = Integer.parseInt(in.nextLine().trim());
		m = intputM + 1;

		max = (intputN + 1) * (intputM + 1) + 1;
		inputData = new Graph();
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
		int ans = 0;

		for (Node n : inputData.originalNodes) {
			if (inputData.nodes[n.index] != null) {
				findAndRemoveCycle(n);
			}
		}

		out.println(ans);
	}

	static int remove = 0;

	private void findAndRemoveCycle(Node n) {
		List<Edge> cycle = findCycle(n);
		if (cycle != null) {
			inputData.remove(cycle);
			for (Edge node : cycle) {
				findCycle(node.n1);
			}
		}
	}

	int count = 0;

	private List<Edge> findCycle(Node n) {
		boolean[] checked = new boolean[max];
		LinkedList<GraphPath> queue = new LinkedList<>();
		GraphPath path = new GraphPath();
		path.n = n;
		queue.addLast(path);
		GraphPath current = null;
		while (!queue.isEmpty()) {
			// System.out.println(count++ + " " + max);
			current = queue.pollFirst();
			if (current.n.index == n.index && current.prev != null) {
				List<Edge> list = new ArrayList<>();
				while (current.prev != null) {
					Edge e = new Edge();
					e.front = current.front;
					e.n1 = current.n;
					current = current.prev;
					e.n2 = current.n;
					list.add(e);
				}
				return list;
			}
			if (checked[current.n.index]) {// ранее эту точку получали - значит тут где-то есть цикл
				continue;
			}
			if (current.n.checkNoCycles) {// для этой точки уже точно нет цикла.
				continue;
			}
			checked[current.n.index] = true;
			for (Node next : current.n.getEdges(current.front)) {
				path = new GraphPath();
				path.front = !current.front;
				path.n = next;
				path.prev = current;
				queue.addLast(path);
			}
		}
		n.checkNoCycles = true;
		return null;
	}

	void addLeftToRight(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 1, (i + 1) * (m) + 1 + j + 1, front);
	}

	void addRightToLeft(int i, int j, boolean front) {
		inputData.addNode(i * m + j + 2, (i + 1) * (m) + j + 1, front);
	}

	private static class GraphPath {
		Node n;
		boolean front;
		GraphPath prev;
	}

	private static class Graph {
		private static Node[] allNodes;
		Node[] nodes;
		List<Node> originalNodes = new ArrayList<>();

		public Graph() {
			nodes = new Node[max];
			allNodes = new Node[max];
			for (int i = 0; i < max; i++) {
				allNodes[i] = new Node(i);
			}
		}

		public void addNode(int n1, int n2, boolean front) {
			if (nodes[n1] == null) {
				originalNodes.add(allNodes[n1]);
			}
			if (nodes[n2] == null) {
				originalNodes.add(allNodes[n2]);
			}
			nodes[n1] = allNodes[n1];
			nodes[n2] = allNodes[n2];
			nodes[n1].addEdge(allNodes[n2], front);
			nodes[n2].addEdge(allNodes[n1], front);
		}

		public void remove(Collection<Edge> edges) {
			// System.out.println("remove");
			for (Edge e : edges) {
				// System.out.println(remove++);

				this.nodes[e.n1.index].removeEdge(e.n2.index, e.front);
				this.nodes[e.n2.index].removeEdge(e.n1.index, e.front);
				if (this.nodes[e.n1.index].isEmpty()) {
					this.nodes[e.n1.index] = null;
				}
				if (this.nodes[e.n2.index].isEmpty()) {
					this.nodes[e.n2.index] = null;
				}
			}
		}
	}

	private static class Node {
		final int index;
		List<Node> edgesFront = new ArrayList<>(4);
		List<Node> edgesBack = new ArrayList<>(4);

		boolean checkNoCycles = false;

		public Node(int index) {
			this.index = index;
		}

		public void addEdge(Node node, boolean front) {
			if (front) {
				edgesFront.add(node);
			} else {
				edgesBack.add(node);
			}
		}

		public void removeEdge(int n2, boolean front) {
			if (front) {
				edgesFront.remove(Graph.allNodes[n2]);
			} else {
				edgesBack.remove(Graph.allNodes[n2]);
			}
		}

		public List<Node> getEdges(boolean front) {
			return front ? edgesFront : edgesBack;
		}

		public boolean isEmpty() {
			return edgesFront.isEmpty() && edgesBack.isEmpty();
		}
	}

	private static class Edge {
		Node n1;
		Node n2;
		boolean front;
	}
}
