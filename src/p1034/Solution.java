package p1034;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<Point> points;
	protected static int n;
	private static List<Set<Point>> ansList;
	private static Field map;

	protected static void solution(Scanner in, PrintWriter out) {
		points = new ArrayList<>();
		ansList = new ArrayList<>();
		n = in.nextInt();
		map = new Field();
		for (int i = 0; i < n; i++) {
			Point p = new Point();
			p.x = in.nextInt() - 1;
			p.y = in.nextInt() - 1;
			points.add(p);
		}

		dest = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			List<Set<Point>> list = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				Point p = new Point(i, j);
				list.add(p.calcAllpositions());
			}
			dest.add(list);
		}

		for (Point p : points) {
			map.addPoint(p);
		}

		int ans = brudeforse();
		out.println(ans);
	}

	private static int brudeforse() {
		for (int i1 = 0; i1 < points.size() - 2; i1++) {
			Point p1 = points.get(i1);
			map.removePoint(p1);
			for (int i2 = i1 + 1; i2 < points.size() - 1; i2++) {
				Point p2 = points.get(i2);
				map.removePoint(p2);
				for (int i3 = i2 + 1; i3 < points.size(); i3++) {
					Point p3 = points.get(i3);
					map.removePoint(p3);
					check(p1, p2, p3);// на этот момент у нас высталвено значение для всего
					map.addPoint(p3);
				}
				map.addPoint(p2);
			}
			map.addPoint(p1);
		}
		int ans = 0;
		List<Set<Point>> onelist = new ArrayList<>();
		for (Set<Point> var : ansList) {
			if (!onelist.contains(var)) {
				onelist.add(var);
				ans++;
			}
		}
		return ans;
	}

	private static void check(Point p1, Point p2, Point p3) {
		for (Point checkP1 : p1.getAllpositions()) {
			if (p1.equals(checkP1)) {
				continue;
			}
			if (map.map[checkP1.x][checkP1.y] != 0) {
				continue;
			}
			if (map.addPoint(checkP1)) {
				for (Point checkP2 : p2.getAllpositions()) {
					if (p2.equals(checkP2)) {
						continue;
					}
					if (map.map[checkP2.x][checkP2.y] != 0) {
						continue;
					}
					if (map.addPoint(checkP2)) {
						for (Point checkP3 : p3.getAllpositions()) {
							if (p3.equals(checkP3)) {
								continue;
							}
							if (map.map[checkP3.x][checkP3.y] != 0) {
								continue;
							}
							if (map.addPoint(checkP3)) {
								Set<Point> var = new HashSet<>(points);
								var.remove(p1);
								var.remove(p2);
								var.remove(p3);
								var.add(checkP1);
								var.add(checkP2);
								var.add(checkP3);
								ansList.add(var);
							}
							map.removePoint(checkP3);
						}
					}
					map.removePoint(checkP2);
				}
			}
			map.removePoint(checkP1);
		}
	}

	protected static class Field {
		int[][] map = new int[n][n];
		boolean[][] points = new boolean[n][n];

		protected boolean addPoint(Point point) {
			boolean check = true;
			for (Point p : point.getAllpositions()) {
				map[p.x][p.y] += 1;
				if (points[p.x][p.y]) {
					check = false;
				}
			}
			points[point.x][point.y] = true;
			return check;
		}

		protected void removePoint(Point point) {
			for (Point p : point.getAllpositions()) {
				map[p.x][p.y] -= 1;
			}
			points[point.x][point.y] = false;
		}
	}

	private static List<List<Set<Point>>> dest;

	protected static class Point {
		int x, y;

		protected Point() {

		}

		protected Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return x + " " + y;
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return p.x == x && p.y == y;
		}

		private Set<Point> getAllpositions() {
			return dest.get(x).get(y);
		}

		private Set<Point> calcAllpositions() {
			Set<Point> points = new HashSet<>();

			for (int i = -n; i < n; i++) {
				addToList(points, x + i, y);
				addToList(points, x, y + i);

				addToList(points, x + i, y + i);
				addToList(points, x - i, y + i);
			}
			return points;
		}

		private void addToList(Set<Point> destPoints, int x, int y) {
			if (x < 0 || y < 0 || x >= n || y >= n) {
			} else {
				destPoints.add(new Point(x, y));
			}
		}

		@Override
		public int hashCode() {
			return x + y;
		}

	}

	static int counter = 0;
}
