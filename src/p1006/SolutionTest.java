package p1006;

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
	String expected = "";

	public String run(String input) {
		InputStream strStream = new ByteArrayInputStream(input.getBytes());
		Scanner in = new Scanner(strStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(os);
		Solution.solution(in, out);
		out.flush();
		String output = new String(os.toByteArray());
		return output;
	}

	@Test
	public void test1() throws Exception {
		Files.readAllLines(new File("src/p1006/input1.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output1.txt").toPath()).stream()
				.forEach(s -> expected += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test2() throws Exception {
		Files.readAllLines(new File("src/p1006/input2.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output2.txt").toPath()).stream()
				.forEach(s -> expected += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test3() throws Exception {
		Files.readAllLines(new File("src/p1006/input3.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output3.txt").toPath()).stream()
				.forEach(s -> expected += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test4() throws Exception {
		Files.readAllLines(new File("src/p1006/input4.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output4.txt").toPath()).stream()
				.forEach(s -> expected += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test5() throws Exception {
		Files.readAllLines(new File("src/p1006/input5.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output5.txt").toPath()).stream()
				.forEach(s -> expected += s + System.lineSeparator());
		output = run(input);
		Assert.assertEquals(expected, output);
	}
}
