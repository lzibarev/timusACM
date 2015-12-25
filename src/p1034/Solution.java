package p1034;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static List<Point> points = new ArrayList<>();
	protected static int n;

	protected static void solution(Scanner in, PrintWriter out) {
		n = in.nextInt();
		for (int i = 0; i < n; i++) {
			Point p = new Point();
			p.x = in.nextInt() - 1;
			p.y = in.nextInt() - 1;
			points.add(p);
		}
		int ans = brudeforse();
		out.println(ans);
	}

	private static int brudeforse() {
		int ans = 0;
		for (int i1 = 0; i1 < points.size() - 2; i1++) {
			for (int i2 = i1 + 1; i2 < points.size() - 1; i2++) {
				for (int i3 = i2 + 1; i3 < points.size(); i3++) {
					Point p1 = points.get(i1);
					Point p2 = points.get(i2);
					Point p3 = points.get(i3);
					ans += check(p1, p2, p3);
				}
			}
		}
		return ans;
	}

	private static int check(Point p1, Point p2, Point p3) {
		int ans = 0;
		Map map = new Map();
		// System.out.println("****");

		for (Point p : points) {
			if (p == p1 || p == p2 || p == p3) {
				continue;
			}
			if (!map.addPoint(p)) {
				return 0;
			}
			// System.out.println(p);
			// System.out.println(map);
			// System.out.println();
		}
		// System.out.println(map);
		Point checkP1 = null;
		while ((checkP1 = map.nextEmptyPoint(checkP1)) != null) {
			if (p1.equals(checkP1)) {
				continue;
			}
			if (!map.addPoint1(checkP1)) {
				continue;
			}
			Point checkP2 = null;
			while ((checkP2 = map.nextEmptyPoint(checkP2)) != null) {
				if (p2.equals(checkP2)) {
					continue;
				}
				if (map.map1[checkP2.x][checkP2.y]) {
					continue;// пересеклось с первой 2->1
				}
				if (!map.addPoint2(checkP2)) {
					continue;
				}
				if (map.map2[checkP1.x][checkP1.y]) {
					continue;// пересеклось со второй 1->2
				}
				Point checkP3 = null;
				while ((checkP3 = map.nextEmptyPoint(checkP3)) != null) {
					if (p3.equals(checkP3)) {
						continue;
					}
					if (map.map1[checkP3.x][checkP3.y]) {
						continue;// пересеклось с первой 3->1
					}
					if (map.map2[checkP3.x][checkP3.y]) {
						continue;// пересеклось со второй 3->2
					}
					if (!map.addPoint3(checkP3)) {
						continue;
					}
					if (map.map3[checkP1.x][checkP1.y]) {
						continue;// 1->3
					}
					if (map.map3[checkP2.x][checkP2.y]) {
						continue;// 1->2
					}
					if (points.contains(checkP1) || points.contains(checkP3) || points.contains(checkP3)) {
						continue;
					}
					ans++;
					System.out.println(points);
					System.out.println("p1     =" + p1 + " p2     =" + p2 + " p3     =" + p3);
					System.out.println("checkP1=" + checkP1 + " checkP2=" + checkP2 + " checkP3=" + checkP3);
				}
			}
		}
		//
		return ans;
	}

	protected static class Map {
		boolean[][] map = new boolean[n][n];
		boolean[][] points = new boolean[n][n];
		boolean[][] map1;
		boolean[][] map2;
		boolean[][] map3;

		protected boolean addPointCommon(Point p, boolean[][] m) {
			for (int i = -n + 1; i < n; i++) {
				if (!markMap(p.x, p.y + i, m)) {
					return false;
				}
				if (!markMap(p.x + i, p.y, m)) {
					return false;
				}
				if (!markMap(p.x + i, p.y + i, m)) {
					return false;
				}
				if (!markMap(p.x + i, p.y - i, m)) {
					return false;
				}
			}
			return true;
		}

		protected boolean addPoint(Point p) {
			if (addPointCommon(p, map)) {
				points[p.x][p.y] = true;
				return true;
			}
			return false;
		}

		private boolean addPoint1(Point p) {
			map1 = new boolean[n][n];
			map2 = new boolean[n][n];
			map3 = new boolean[n][n];
			if (addPointCommon(p, map1)) {
				return true;
			}
			return false;
		}

		private boolean addPoint2(Point p) {
			map2 = new boolean[n][n];
			map3 = new boolean[n][n];
			if (addPointCommon(p, map2)) {
				return true;
			}
			return false;
		}

		private boolean addPoint3(Point p) {
			map3 = new boolean[n][n];
			if (addPointCommon(p, map3)) {
				return true;
			}
			return false;
		}

		private boolean markMap(int x, int y, boolean[][] m) {
			if (x < 0 || y < 0 || x >= n || y >= n) {
				return true;
			}
			if (points[x][y]) {
				return false;
			}
			m[x][y] = true;
			return true;
		}

		Point nextEmptyPoint(Point prevPoint) {
			if (prevPoint == null) {
				prevPoint = new Point();
				prevPoint.y -= 1;
			}
			int startY = prevPoint.y + 1;
			for (int indexX = prevPoint.x; indexX < n; indexX++) {
				for (int indexY = startY; indexY < n; indexY++) {
					if (!map[indexX][indexY]) {
						Point p = new Point();
						p.x = indexX;
						p.y = indexY;
						return p;
					}
				}
				startY = 0;
			}
			return null;
		}

		private String printMap(boolean[][] b) {
			if (b == null) {
				return "";
			}
			String ans = "";
			for (boolean[] cs : b) {
				for (boolean c : cs) {
					ans += c ? "0" : "1";
				}
				ans += "\r\n";
			}
			return ans;
		}

		@Override
		public String toString() {
			String ans = printMap(map);
			ans += "symm\r\n";
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					ans += map[i][j] || (map1 != null && map1[i][j]) || (map2 != null && map2[i][j])
							|| (map3 != null && map3[i][j]) ? "0" : "1";
				}
				ans += "\r\n";
			}
			return ans;
		}
	}

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
	}
}
