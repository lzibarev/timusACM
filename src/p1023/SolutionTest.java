package p1023;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

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
	public void test0() {
		input = "3";
		output = run(input);
		Assert.assertEquals("2", output);
	}

	@Test
	public void test1() {
		input = "4";
		output = run(input);
		Assert.assertEquals("3", output);
	}

	@Test
	public void test5() {
		input = "5";
		output = run(input);
		Assert.assertEquals("4", output);
	}

	@Test
	public void test6() {
		input = "6";
		output = run(input);
		Assert.assertEquals("2", output);
	}

	@Test
	public void test7() {
		input = "7";
		output = run(input);
		Assert.assertEquals("6", output);
	}

	@Test
	public void test8() {
		input = "8";
		output = run(input);
		Assert.assertEquals("3", output);
	}

	@Test
	public void test11() {
		input = "11";
		output = run(input);
		Assert.assertEquals("10", output);
	}

	@Test
	public void test10() {
		input = "10";
		output = run(input);
		Assert.assertEquals("4", output);
	}

	@Test
	public void test26() {
		input = "26";
		output = run(input);
		Assert.assertEquals("12", output);
	}

	@Test
	public void test99999999() {
		input = "99999999";
		output = run(input);
		Assert.assertEquals("2", output);
	}
}
