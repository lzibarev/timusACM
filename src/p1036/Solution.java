package p1036;

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

	protected static void solution(Scanner in, PrintWriter out) {
		int n = in.nextInt();
		int k = in.nextInt();
		BigInteger ans = BigInteger.ZERO;
		if (k % 2 == 0) {
			Result r = new Result();
			r.position = 0;
			r.summ = k / 2;
			ans = check(r, n);
		}
		BigInteger l = ans.multiply(ans);
		out.println(l);
	}

	private static BigInteger check(Result r, int max) {
		if (r.position == max) {
			return BigInteger.ZERO;
		}
		BigInteger ans = BigInteger.ZERO;
		if ((max - r.position) * 9 < r.summ) {
			return BigInteger.ZERO;
		}
		if (map.containsKey(r)) {
			return map.get(r);
		}
		for (int i = 0; i <= 9; i++) {
			int newSum = r.summ - i;
			if (newSum == 0) {
				ans = ans.add(BigInteger.ONE);
				break;
			}
			Result newR = new Result();
			newR.position = r.position + 1;
			newR.summ = newSum;
			BigInteger newAns = check(newR, max);
			ans = ans.add(newAns);
		}
		map.put(r, ans);
		return ans;
	}

	private static class Result {
		int position, summ;

		@Override
		public boolean equals(Object obj) {
			Result r = (Result) obj;
			return r.position == position && r.summ == r.summ;
		}

		@Override
		public int hashCode() {
			return position + summ;
		}
	}

	private static Map<Result, BigInteger> map = new HashMap<>();
}
