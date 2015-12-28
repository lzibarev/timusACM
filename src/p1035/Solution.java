package p1035;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

	protected void solution(Scanner in, PrintWriter out) {
		int intputN = in.nextInt();
		int intputM = Integer.parseInt(in.nextLine().trim());
		int n = intputN + 1;

		inputData = new Graph();
		for (int i = 0; i < intputN; i++) {
			String line = in.nextLine();
			for (int j = 0; j < intputM; j++) {
				char c = line.charAt(j);
				if (c == '.') {
					//
				}
				if (c == '\\' || c == 'X') {
					Edge e = new Edge();
					e.x = i * n + j;
					e.y = (i + 1) * n + j + 1;
					inputData.addEdge1(e);
				}
				if (c == '/' || c == 'X') {
					Edge e = new Edge();
					e.x = i * n + j + 1;
					e.y = (i + 1) * n + j;
					inputData.addEdge1(e);
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
					Edge e = new Edge();
					e.x = i * n + j;
					e.y = (i + 1) * n + j + 1;
					inputData.addEdge2(e);
				}
				if (c == '/' || c == 'X') {
					Edge e = new Edge();
					e.x = i * n + j + 1;
					e.y = (i + 1) * n + j;
					inputData.addEdge2(e);
				}
			}
		}

		int ans = 0;
		while (inputData.size() != 0) {
			Graph max = findMax();
			inputData.remove(max);
			ans++;
		}
		out.println(ans);
	}

	private Graph findMax() {
		Graph g = new Graph();
		if (!inputData.edges1.isEmpty()) {
			g.addEdge1(inputData.edges1.get(0));
		}
		if (!inputData.edges2.isEmpty()) {
			g.addEdge2(inputData.edges2.get(0));
		}
		return g;
	}

	private static class Graph {
		List<Edge> edges1 = new ArrayList<>();
		List<Edge> edges2 = new ArrayList<>();

		void addEdge1(Edge e) {
			edges1.add(e);
		}

		void addEdge2(Edge e) {
			edges2.add(e);
		}

		public void remove(Graph max) {
			for (Edge edge : max.edges1) {
				edges1.remove(edge);
			}
			for (Edge edge : max.edges2) {
				edges2.remove(edge);
			}
		}

		int size() {
			return edges1.size() + edges2.size();
		}
	}

	private static class Edge {
		int x, y;

		@Override
		public int hashCode() {
			return x + y;
		}

		@Override
		public boolean equals(Object obj) {
			Edge e = (Edge) obj;
			if (x == e.x && y == e.y) {
				return true;
			}
			if (y == e.x && x == e.y) {
				return true;
			}
			return false;
		}
	}

}
