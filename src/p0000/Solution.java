package p0000;

import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
		in.close();
	}
	
	public static void solution(Scanner in, PrintWriter out){
		int a = in.nextInt();
		int b = in.nextInt();
		out.println(a + b);
	}

}
