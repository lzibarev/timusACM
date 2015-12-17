package p1006_encoding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
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
		for (Charset charset : Charset.availableCharsets().values()) {
			System.out.println(charset);
			// Files.readAllLines(new File("src/p1006/input1.txt").toPath(),
			// charset).stream()
			// .forEach(s -> input += s + "\r\n");
			Charset cs = Charset.forName("ASCII");
			;
			System.out.println(Files.readAllLines(new File("src/p1006/input1.txt").toPath(), cs));
			return;
			// System.out.println(charset.decode(ByteBuffer.wrap(b)));
		}
		if (true) {
			return;
		}
		System.out.println();
		Files.readAllLines(new File("src/p1006/input1.txt").toPath(), Charset.forName("windows-1251")).stream()
				.forEach(s -> input += s + "\r\n");
		System.out.println(input);
		Files.readAllLines(new File("src/p1006/output1.txt").toPath()).stream().forEach(s -> expected += s + "\r\n");
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test2() throws Exception {
		Files.readAllLines(new File("src/p1006/input2.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output2.txt").toPath()).stream().forEach(s -> expected += s + "\r\n");
		output = run(input);
		Assert.assertEquals(expected, output);
	}

	@Test
	public void test3() throws Exception {
		Files.readAllLines(new File("src/p1006/input3.txt").toPath()).stream().forEach(s -> input += s + "\r\n");
		Files.readAllLines(new File("src/p1006/output3.txt").toPath()).stream().forEach(s -> expected += s + "\r\n");
		output = run(input);
		Assert.assertEquals(expected, output);
	}
}
