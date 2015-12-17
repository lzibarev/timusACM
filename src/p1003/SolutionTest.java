package p1003;

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
		input = "10\r\n2\r\n1 2 even\r\n1 2 odd\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("1", output);
		input = "10\r\n2\r\n1 1 even\r\n1 1 odd\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("1", output);
		input = "10\r\n2\r\n1 1 even\r\n1 1 even\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("2", output);
		input = "1\r\n0\r\n-1";
		output = run(input);
		Assert.assertEquals("0", output);
		input = "2\r\n1\r\n1 2 even\r\n-1";
		output = run(input);
		Assert.assertEquals("1", output);
	}

	@Test
	public void test2() {
		input = "10\r\n2\r\n1 2 even\r\n1 3 odd\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("2", output);
		input = "10\r\n2\r\n1 2 even\r\n1 3 odd\r\n10\r\n2\r\n1 2 even\r\n1 3 odd\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("2\r\n2", output);
	}

	@Test
	public void test3() {
		input = "10\r\n3\r\n1 2 even\r\n1 3 odd\r\n2 3 odd\r\n-1\r\n";
		output = run(input);
		Assert.assertEquals("3", output);
	}

	@Test
	public void test_real() {
		input = "10\r\n5\r\n1 2 even\r\n3 4 odd\r\n5 6 even\r\n1 6 even\r\n7 10 odd\r\n-1";
		output = run(input);
		Assert.assertEquals("3", output);
	}

}
