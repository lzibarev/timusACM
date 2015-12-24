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
