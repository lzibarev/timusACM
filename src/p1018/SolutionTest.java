package p1018;

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

}
