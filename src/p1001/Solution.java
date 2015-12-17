package p1001;

import java.io.*;
import java.util.*;


public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		solution(in, out);

		out.flush();
	}
	
	protected static void solution(Scanner in, PrintWriter out){
		List<Double> list = new ArrayList<>();
		while(in.hasNext()){
			long l = in.nextLong();
			double d = Math.sqrt(l);
			list.add(d);
		}
		for(int i=list.size()-1; i>=0;i--){
			out.println(String.format(Locale.ENGLISH, "%.4f", list.get(i)));
		}
	}

}
