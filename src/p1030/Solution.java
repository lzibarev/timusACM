package p1030;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
		while (in.hasNext()) {
			// Message #<n>.
			in.nextLine();
			// Received at <HH>:<MM>:<SS>.
			in.nextLine();
			// Current ship's coordinates are
			in.nextLine();
			// <X1>^<X2>'<X3>" <NL/SL>
			String p1X = in.nextLine();
			// and <Y1>^<Y2>'<Y3>" <EL/WL>.
			String p1Y = in.nextLine();
			// An iceberg was noticed at
			in.nextLine();
			// <A1>^<A2>'<A3>" <NL/SL>
			String p2X = in.nextLine();
			// and <B1>^<B2>'<B3>" <EL/WL>.
			String p2Y = in.nextLine();
			// ===
			in.nextLine();

			point p1 = new point(p1X, p1Y);
			point p2 = new point(p2X, p2Y);

			d = calculateD(p1, p2);
			print(out, d);
		}
	}

	protected static String print(PrintWriter out, double d) {
		DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		d = Math.round(d * 100) / 100d;
		// System.out.println(df.format(d));
		String ans = "The distance to the iceberg: " + df.format(d) + " miles.";
		// System.out.println(d);
		out.println(ans);
		if (d < 100) {
			out.println("DANGER!");
			ans += "DANGER!";
		}
		return ans;
	}

	protected static double calculateD(point p1, point p2) {
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
		return r * deltaAngle;
	}

	protected static String calculate(point p1, point p2) {
		double d2 = calculateD(p1, p2);
		return String.format(Locale.ENGLISH, "%.2f", d2);
	}

	private static double d = 6875;
	private static double r = d / 2;

	protected static class point {
		int sx = 1, sy = 1;
		double x1, x2, x3, y1, y2, y3;

		point() {
		}

		point(String xstr, String ystr) {
			// <X1>^<X2>'<X3>" <NL/SL>
			// and <Y1>^<Y2>'<Y3>" <EL/WL>.
			String[] x1Str = xstr.trim().split("\\^");
			x1 = Integer.parseInt(x1Str[0].trim());
			String[] x2Str = x1Str[1].split("\\'");
			x2 = Integer.parseInt(x2Str[0].trim());
			String[] x3Str = x2Str[1].split("\\\"");
			x3 = Integer.parseInt(x3Str[0].trim());
			sx = x3Str[1].trim().startsWith("NL") ? 1 : -1;

			String[] y1Str = ystr.trim().split("\\^");
			y1 = Integer.parseInt(y1Str[0].substring("and ".length()).trim());
			String[] y2Str = y1Str[1].split("\\'");
			y2 = Integer.parseInt(y2Str[0].trim());
			String[] y3Str = y2Str[1].split("\\\"");
			y3 = Integer.parseInt(y3Str[0].trim());
			sy = y3Str[1].trim().startsWith("EL") ? 1 : -1;

			// System.out.println(toString());

		}

		double degreeX() {
			return sx * (x1 + x2 / 60 + x3 / 3600);
		}

		double degreeY() {
			return sy * (y1 + y2 / 60 + y3 / 3600);
		}

		double getRadianX() {
			return Math.toRadians(degreeX());
		}

		double getRadianY() {
			return Math.toRadians(degreeY());
		}

		@Override
		public String toString() {
			return degreeX() + " " + degreeY();
		}
	}
}
