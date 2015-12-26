package p1036;

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
		input = "2 2";
		output = run(input);
		Assert.assertEquals("4", output);
	}

	@Test
	public void test1() {
		input = "2 36";
		output = run(input);
		Assert.assertEquals("1", output);
	}

	@Test
	public void test2() {
		input = "2 34";
		output = run(input);
		Assert.assertEquals("4", output);
	}

	@Test
	public void test3() {
		input = "2 3";
		output = run(input);
		Assert.assertEquals("0", output);
	}

	@Test
	public void test4() {
		input = "2 4";
		output = run(input);
		Assert.assertEquals("9", output);
	}

	@Test
	public void test50_900() {
		input = "50 900";
		output = run(input);
		Assert.assertEquals("1", output);
	}

	@Test
	public void test50_450() {
		input = "50 450";
		output = run(input);
		Assert.assertEquals("3834688188199309756675466732967588185862422492337212276545580039517258085033387305291001857619600", output);
	}

}
