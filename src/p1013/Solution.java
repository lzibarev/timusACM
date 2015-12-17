package p1013;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}

	private static BigInteger m;

	protected static void solution(Scanner in, PrintWriter out) {
		long n = in.nextLong();
		long k0 = in.nextLong();
		long m0 = in.nextLong();

		m = BigInteger.valueOf(m0);
		Calc c1 = new Calc();
		c1.size = 1;
		c1.x1 = BigInteger.ONE;
		c1.x2 = BigInteger.ZERO;
		c1.x3 = BigInteger.ZERO;
		c1.x4 = BigInteger.valueOf(k0 - 1);

		String n2 = Long.toBinaryString(n);

		Calc result = c1;
		for (int p = 0; p < n2.length() + 1; p++) {
			map.put(p, result);
			result = result.add(result);
		}

		result = null;
		for (int i = 0; i < n2.length(); i++) {
			int p = n2.length() - 1 - i;
			char c = n2.charAt(i);
			if (c == '1') {
				if (result == null) {
					result = map.get(p);
				} else {
					result = result.add(map.get(p));
				}
			}
		}

		out.print(result.x3.add(result.x4).mod(m).toString());
	}

	private static Map<Integer, Calc> map = new HashMap<>();

	private static class Calc {
		long size;
		BigInteger x1, x2, x3, x4;

		private Calc add(Calc c) {
			Calc result = new Calc();
			result.size = this.size + c.size;

			result.x1 = this.x1.multiply(c.x3).add(this.x2.multiply(c.x1)).add(this.x2.multiply(c.x3));
			result.x2 = this.x1.multiply(c.x4).add(this.x2.multiply(c.x2)).add(this.x2.multiply(c.x4));
			result.x3 = this.x3.multiply(c.x3).add(this.x4.multiply(c.x1)).add(this.x4.multiply(c.x2));
			result.x4 = this.x3.multiply(c.x4).add(this.x4.multiply(c.x2)).add(this.x4.multiply(c.x4));

			result.x1 = result.x1.mod(m);
			result.x2 = result.x2.mod(m);
			result.x3 = result.x3.mod(m);
			result.x4 = result.x4.mod(m);

			return result;
		}
	}
}
