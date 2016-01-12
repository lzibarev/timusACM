package p1038;

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
		new Solution().solution(in, out);
		out.flush();
		String output = new String(os.toByteArray(), StandardCharsets.UTF_8);
		return output.trim();
	}

	@Test
	public void test0() {
		input = "This sentence iz correkt! -It Has,No mista;.Kes et oll. But there are two BIG mistakes in this one! and here is one more.";
		output = run(input);
		Assert.assertEquals("3", output);
	}

	@Test
	public void test1() {
		input = "This-is-that.";
		output = run(input);
		Assert.assertEquals("3", output);
	}

}
