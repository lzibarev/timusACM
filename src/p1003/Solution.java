package p1003;

import java.io.PrintWriter;
import java.util.ArrayList;
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

	private static Map<Long, InputData> calcList;

	protected static void solution(Scanner in, PrintWriter out) {
		while (in.hasNext()) {
			process(in, out);
		}
	}

	private static void process(Scanner in, PrintWriter out) {
		long size = in.nextLong();
		if (size == -1) {
			return;
		}
		int questionCount = in.nextInt();
		ArrayList<InputData> dataList = new ArrayList<>(questionCount);
		for (int i = 0; i < questionCount; i++) {
			InputData data = new InputData();
			data.a = in.nextLong();
			data.b = in.nextLong();
			String even = in.next();
			data.even = even.equals("even");
			dataList.add(data);
		}
		// calc
		calcList = new HashMap<>();
		int ans = 0;
		for (; ans < dataList.size(); ans++) {
			boolean isOk = checkWithCalc(dataList.get(ans));
			if (!isOk) {
				out.println(ans);
				return;
			}
		}
		out.println(ans);
	}

	private static boolean checkWithCalc(InputData inputData) {
		InputData calcData = calcList.get(inputData.a);
		if (calcData != null) {
			if (calcData.a == inputData.a && calcData.b == inputData.b) {
				return calcData.even == inputData.even;
			}
			if (calcData.a == inputData.a) {
				if (calcData.b < inputData.b) {
					InputData newData = new InputData();
					newData.a = calcData.b + 1;
					newData.b = inputData.b;
					newData.even = sub(calcData.even, inputData.even);
					return checkWithCalc(newData);
				}
				if (calcData.b > inputData.b) {
					InputData newData = new InputData();
					newData.a = inputData.b + 1;
					newData.b = calcData.b;
					newData.even = sub(calcData.even, inputData.even);
					return checkWithCalc(newData);
				}
			}
		}
		calcList.put(inputData.a, inputData);
		return true;
	}

	private static boolean sub(boolean even1, boolean even2) {
		if (even1 && even2)
			return true;
		if (!even1 && !even2)
			return true;
		return false;
	}

	private static class InputData {
		private long a, b;
		boolean even;
	}

}
