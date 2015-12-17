package p1012;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	protected static void solution(Scanner in, PrintWriter out) {
		long n = in.nextInt();
		long k = in.nextInt();

		// База индукции
		// кроме тех у кого ноль на первом месте
		BigDecimal y = BigDecimal.valueOf(k).multiply(BigDecimal.valueOf((k - 1)));
		// без нуля на конце
		BigDecimal x = BigDecimal.valueOf(k - 1).multiply(BigDecimal.valueOf((k - 1)));
		for (int i = 2; i < n; i++) {
			BigDecimal x1 = y.multiply(BigDecimal.valueOf(k - 1));
			BigDecimal y1 = x1.add(x);
			x = x1;
			y = y1;
		}
		out.print(y.toString());
	}
}
