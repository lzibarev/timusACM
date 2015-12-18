package p1016;

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
		input = "e2 e3 1 1 1 1 1 1\r\n";
		output = run(input);
		Assert.assertEquals("1 e2 e3", output);
	}

	@Test
	public void test1() {
		input = "e2 e3 0 8 1 2 1 1\r\n";
		output = run(input);
		Assert.assertEquals("5 e2 d2 d1 e1 e2 e3", output);
	}
}
