package p1030;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import p1030.Solution.point;

public class SolutionTest {
	String input;
	String output;

	public String run(String input) {
		InputStream strStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		Scanner in = new Scanner(strStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(os);
		Solution.solution(in, out);
		out.flush();
		String output = new String(os.toByteArray(), StandardCharsets.UTF_8);
		return output.trim();
	}

	@Test
	public void pritnTest() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(os);
		output = Solution.print(out, 100);
		Assert.assertEquals("The distance to the iceberg: 100.00 miles.", output);

		output = Solution.print(out, 100.001);
		Assert.assertEquals("The distance to the iceberg: 100.00 miles.", output);

		output = Solution.print(out, 100.005);
		Assert.assertEquals("The distance to the iceberg: 100.01 miles.", output);

		output = Solution.print(out, 100.01);
		Assert.assertEquals("The distance to the iceberg: 100.01 miles.", output);
	}

	private String str(String x, String y, String a, String b) {
		return "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n " + x + " \r\nand " + y
				+ ".\r\nAn iceberg was noticed at\r\n" + a + " \r\nand " + b + ".\r\n=== \r\n";
	}

	@Test
	public void test0() {
		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'00\" NL \r\nand 50^14'00\" WL.\r\nAn iceberg was noticed at\r\n41^14'11\" NL \r\nand 51^09'00\" WL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.04 miles.\r\nDANGER!", output);
	}

	@Test
	public void testZero() {
		input = str("00^00'00\" NL", "00^00'00\" WL", "00^00'00\" NL", "00^00'00\" WL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.00 miles.\r\nDANGER!", output);

		input = str("00^00'01\" NL", "00^00'00\" WL", "00^00'00\" NL", "00^00'00\" WL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.02 miles.\r\nDANGER!", output);

		input = str("00^00'00\" NL", "00^00'00\" WL", "00^00'01\" NL", "00^00'00\" WL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.02 miles.\r\nDANGER!", output);

		input = str("00^00'00\" NL", "00^00'00\" WL", "00^00'02\" NL", "00^00'00\" WL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.03 miles.\r\nDANGER!", output);

		input = str("00^00'01\" SL", "00^00'00\" WL", "00^00'01\" NL", "00^00'00\" WL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.03 miles.\r\nDANGER!", output);

		input = str("00^00'00\" SL", "00^00'01\" WL", "00^00'00\" NL", "00^00'01\" EL");
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 0.03 miles.\r\nDANGER!", output);
	}

	@Test
	public void test0_round() {
		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'10\" NL \r\nand 50^14'00\" WL.\r\nAn iceberg was noticed at\r\n41^14'11\" NL \r\nand 51^09'00\" WL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.15 miles.\r\nDANGER!", output);

		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'11\" NL \r\nand 50^14'00\" WL.\r\nAn iceberg was noticed at\r\n41^14'11\" NL \r\nand 51^09'00\" WL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.16 miles.\r\nDANGER!", output);

		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'12\" NL \r\nand 50^14'00\" WL.\r\nAn iceberg was noticed at\r\n41^14'11\" NL \r\nand 51^09'00\" WL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.17 miles.\r\nDANGER!", output);
	}

	@Test
	public void test0_1() {
		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'00\" NL \r\nand 50^14'00\" EL.\r\nAn iceberg was noticed at\r\n41^14'11\" NL \r\nand 51^09'00\" EL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.04 miles.\r\nDANGER!", output);
	}

	@Test
	public void test0_2() {
		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'00\" SL \r\nand 50^14'00\" EL.\r\nAn iceberg was noticed at\r\n41^14'11\" SL \r\nand 51^09'00\" EL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 52.04 miles.\r\nDANGER!", output);
	}

	@Test
	public void test0_3() {
		input = "Message #513.\r\nReceived at 22:30:11. \r\nCurrent ship’s coordinates are\r\n 41^46'00\" NL \r\nand 50^14'00\" EL.\r\nAn iceberg was noticed at\r\n41^14'11\" SL \r\nand 51^09'00\" EL.\r\n=== \r\n";
		output = run(input);
		Assert.assertEquals("The distance to the iceberg: 4980.07 miles.", output);
	}

	@Test
	public void test() {
		point p1 = new point();
		point p2 = new point();
		p1.x1 = 41;
		p1.x2 = 46;
		p1.x3 = 00;

		p1.y1 = 50;
		p1.y2 = 14;
		p1.y3 = 00;

		p2.x1 = 41;
		p2.x2 = 14;
		p2.x3 = 11;

		p2.y1 = 51;
		p2.y2 = 9;
		p2.y3 = 00;

		Assert.assertEquals("52.04", Solution.calculate(p1, p2));
	}

	@Test
	public void tes1() {
		point p1 = new point();
		point p2 = new point();
		p1.x1 = 40;
		p1.x2 = 00;
		p1.x3 = 00;

		p1.y1 = 00;
		p1.y2 = 00;
		p1.y3 = 00;

		p2.x1 = 40;
		p2.x2 = 00;
		p2.x3 = 00;

		p2.y1 = 00;
		p2.y2 = 00;
		p2.y3 = 00;

		Assert.assertEquals("0.00", Solution.calculate(p1, p2));
	}

	@Test
	public void tes2() {
		point p1 = new point();
		point p2 = new point();
		p1.x1 = 100;
		p1.x2 = 00;
		p1.x3 = 00;

		p1.y1 = 00;
		p1.y2 = 00;
		p1.y3 = 00;

		p2.x1 = 10;
		p2.x2 = 00;
		p2.x3 = 00;

		p2.y1 = 00;
		p2.y2 = 00;
		p2.y3 = 00;

		Assert.assertEquals("5399.61", Solution.calculate(p1, p2));
	}

	@Test
	public void tes3() {
		point p1 = new point();
		point p2 = new point();
		p1.x1 = 00;
		p1.x2 = 00;
		p1.x3 = 00;

		p1.y1 = 100;
		p1.y2 = 00;
		p1.y3 = 00;

		p2.x1 = 00;
		p2.x2 = 00;
		p2.x3 = 00;

		p2.y1 = 10;
		p2.y2 = 00;
		p2.y3 = 00;

		Assert.assertEquals("5399.61", Solution.calculate(p1, p2));
	}
}
