package p1035;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in, "ISO-8859-1");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("ISO-8859-1")));
		new Solution().solution(in, out);

		out.flush();
	}

	private int n, m;
	private boolean[][] graph1, graph2;

	protected void solution(Scanner in, PrintWriter out) {
		int intputN = in.nextInt();
		int intputM = in.nextInt();
		n = intputN + 1;
		m = intputM + 1;
		graph1 = new boolean[n][m];
		for (int i = 0; i < intputN; i++) {
			String line = in.nextLine();
			for (int j = 0; j < intputM; j++) {
				char c = line.charAt(j);
				if (c == '.') {
					//
				} else if (c == '\\') {
					graph1[i * n + j][(i + 1) * n + j + 1] = true;
					graph1[(i + 1) * n + j + 1][i * n + j] = true;
				} else if (c == '/') {
					graph1[(i + 1) * n + j][(i) * n + j + 1] = true;
					graph1[(i) * n + j + 1][(i + 1) * n + j] = true;
				}
			}
		}
		graph2 = new boolean[n][m];
	}

}
