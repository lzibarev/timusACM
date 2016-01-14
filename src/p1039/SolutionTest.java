package p1039;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	String input = "";
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
	public void test0() throws Exception {
		Files.readAllLines(new File("src/p1039/0.txt").toPath()).stream()
				.forEach(s -> input += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals("5", output);
	}
}
