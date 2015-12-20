package p1018;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

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
		input = "5 2\r\n1 3 1\r\n1 4 10\r\n2 3 20\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("21", output);
	}

	@Test
	public void test1() {
		input = "5 2\r\n3 1 1\r\n4 1 10\r\n2 3 20\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("21", output);
	}

	@Test
	public void test2() {
		input = "5 2\r\n2 3 20\r\n1 3 1\r\n1 4 10\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("21", output);
	}

	@Test
	public void test3() {
		input = "5 2\r\n1 4 10\r\n2 3 20\r\n1 3 1\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("21", output);
	}

	@Test
	public void test4() {
		input = "7 3\r\n1 2 12\r\n1 3 1\r\n2 4 30\r\n2 5 30\r\n3 6 30\r\n3 7 30\r\n";
		output = run(input);
		Assert.assertEquals("72", output);
	}

	@Test
	public void test5() {
		input = "7 3\r\n1 2 1\r\n1 3 12\r\n2 4 30\r\n2 5 30\r\n3 6 30\r\n3 7 30\r\n";
		output = run(input);
		Assert.assertEquals("72", output);
	}

	@Test
	public void test6() {
		input = "5 4\r\n3 1 1\r\n4 1 10\r\n2 3 20\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("51", output);
	}

	@Test
	public void test7() {
		input = "5 0\r\n3 1 1\r\n4 1 10\r\n2 3 20\r\n3 5 20\r\n";
		output = run(input);
		Assert.assertEquals("0", output);
	}

	@Test
	public void test8() {
		input = "7 4\r\n1 2 10\r\n1 3 20\r\n2 4 30\r\n2 5 0\r\n3 6 0\r\n3 7 40\r\n";
		output = run(input);
		Assert.assertEquals("100", output);
	}

	@Test
	@Ignore
	public void test9() {
		input = "6 4\r\n1 2 20\r\n2 5 100\r\n2 3 20\r\n6 3 70\r\n4 1 10\r\n";
		output = run(input);
		Assert.assertEquals("60", output);
	}

	@Test
	public void test10() {
		input = "4 1\r\n1 2 1\r\n1 3 2\r\n2 4 3\r\n";
		output = run(input);
		Assert.assertEquals("60", output);
	}

}
