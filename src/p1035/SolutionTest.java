package p1035;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
	String input = "";
	String output = "";

	public String run(String input) {
		InputStream strStream = new ByteArrayInputStream(input.getBytes());
		Scanner in = new Scanner(strStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(os);
		new Solution().solution(in, out);
		out.flush();
		output = new String(os.toByteArray()).trim();
		return output;
	}

	@Test
	public void test1() throws Exception {
		Files.readAllLines(new File("src/p1035/input1.txt").toPath()).stream()
				.forEach(s -> input += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals("4", output);
	}

	@Test
	public void test2() throws Exception {
		Files.readAllLines(new File("src/p1035/input2.txt").toPath()).stream()
				.forEach(s -> input += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals("1", output);
	}
}
