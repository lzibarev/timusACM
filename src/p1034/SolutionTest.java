package p1034;

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
		input = "4\r\n2 1\r\n1 3\r\n3 4\r\n4 2\r\n";
		output = run(input);
		Assert.assertEquals("0", output);
	}

	@Test
	public void test5() {
		input = "5\r\n2 1\r\n1 3\r\n3 4\r\n4 2\r\n5 5\r\n";
		output = run(input);
		Assert.assertEquals("0", output);
	}

	@Test
	public void test9() {
		input = "9\r\n		1 1\r\n		2 3\r\n		3 6\r\n		4 8\r\n		5 2\r\n		6 4\r\n		7 9\r\n		8 7\r\n9 5\r\n";
		output = run(input);
		Assert.assertEquals("2", output);
	}
}
