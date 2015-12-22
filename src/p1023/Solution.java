package p1023;

import java.io.PrintWriter;
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
}
