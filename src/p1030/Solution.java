package p1030;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	protected static void solution(Scanner in, PrintWriter out) {
		int k = in.nextInt();
		int maxDevitor = k / 2 + 3;
		long ans = k - 1;
		for (int i = 3; i < maxDevitor; i++) {
			if (k % i == 0) {
				ans = i - 1;
				break;
			}
		}
		out.println(ans);
	}

	protected static String calculate(point p1, point p2) {
		double x1 = p1.getRadianX();
		double y1 = p1.getRadianY();

		double x2 = p2.getRadianX();
		double y2 = p2.getRadianY();

		// System.out.println(p1.degreeX() + " " + p1.degreeY());
		// System.out.println(x1 + " " + y1);
		// System.out.println(p2.degreeX() + " " + p2.degreeY());
		// System.out.println(x2 + " " + y2);

		double radLat1 = x1;
		double radLon1 = y1;
		double radLat2 = x2;
		double radLon2 = y2;

		double radLonDif = radLon2 - radLon1;

		double atan2top = Math.sqrt(Math.pow(Math.cos(radLat2) * Math.sin(radLonDif), 2) + Math.pow(
				Math.cos(radLat1) * Math.sin(radLat2) - Math.sin(radLat1) * Math.cos(radLat2) * Math.cos(radLonDif),
				2));
		double atan2bottom = Math.sin(radLat1) * Math.sin(radLat2)
				+ Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLonDif);
		double deltaAngle = Math.atan2(atan2top, atan2bottom);
		double d2 = r * deltaAngle;

		return String.format(Locale.ENGLISH, "%.2f", d2);
	}

	private static double d = 6875;
	private static double r = d / 2;

	protected static class point {
		double x1, x2, x3, y1, y2, y3;

		double degreeX() {
			return x1 + x2 / 60 + x3 / 3600;
		}

		double degreeY() {
			return y1 + y2 / 60 + y3 / 3600;
		}

		double getRadianX() {
			return Math.toRadians(degreeX());
		}

		double getRadianY() {
			return Math.toRadians(degreeY());
		}
	}
}
