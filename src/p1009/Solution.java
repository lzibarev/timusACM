package p1009;

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
		long n = in.nextInt();
		long k = in.nextInt();

		// ���� ��������
		long y = k * (k - 1); // ����� ��� � ���� ���� �� ������ �����
		long x = (k - 1) * (k - 1); // ��� ���� �� �����

		for (int i = 2; i < n; i++) {
			long x1 = y * (k - 1);
			long y1 = y * (k - 1) + x;
			x = x1;
			y = y1;
		}
		out.print(y);
	}

}
