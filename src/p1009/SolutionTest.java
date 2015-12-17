package p1009;

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
	public void test1() {
		input = "2\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("90", output);
	}

	@Test
	public void test3() {
		input = "3\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("891", output);
	}

	@Test
	public void test4() {
		input = "4\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("8829", output);
	}

	@Test
	public void test5() {
		input = "5\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("87480", output);
	}

	@Test
	public void testMax1() {
		input = "2\r\n16\r\n";
		output = run(input);
		Assert.assertEquals("240", output);
	}

	@Test
	public void testMax2() {
		input = "8\r\n8\r\n";
		output = run(input);
		Assert.assertEquals("13464808", output);
	}

	@Test
	public void testMax3() {
		input = "16\r\n2\r\n";
		output = run(input);
		Assert.assertEquals("1597", output);
	}

	@Test
	public void testMax4() {
		input = "15\r\n3\r\n";
		output = run(input);
		Assert.assertEquals("2781184", output);
	}
}
