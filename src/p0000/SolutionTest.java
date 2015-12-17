package p0000;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {

	public String run(String input){
		InputStream strStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		Scanner in = new Scanner(strStream);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintWriter out = new PrintWriter(os);
		Solution.solution(in, out);
		out.flush();
		String output = new String( os.toByteArray(), StandardCharsets.UTF_8 );
		return output.trim();
	}
	
	@Test
	public void test1(){
		String input = "1 2";
		String output = run(input);
		System.out.println(output);
		Assert.assertEquals("3", output);
	}
}
