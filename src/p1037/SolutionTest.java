package p1037;

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
		input = "1 +\r\n1 +\r\n1 +\r\n2 . 2\r\n2 . 3\r\n3 . 30000\r\n601 . 1\r\n601 . 2\r\n602 . 3\r\n602 +\r\n602 +\r\n1202 . 2";
		output = run(input);
		Assert.assertEquals("1\r\n2\r\n3\r\n+\r\n+\r\n-\r\n-\r\n+\r\n-\r\n1\r\n3\r\n-", output);
	}

}
