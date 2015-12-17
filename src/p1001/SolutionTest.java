package p1001;

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
		String input = "1";
		String output = run(input);
		Assert.assertEquals("1.0000", output);
	}
	
	@Test
	public void test2(){
		String input = "1 0";
		String output = run(input);
		Assert.assertEquals("0.0000\r\n1.0000", output);
	}
	@Test
	public void test0(){
		String input = " 1427  0   \r\n\r\n   876652098643267843 \r\n5276538\r\n";
		String output = run(input);
		Assert.assertEquals("2297.0716\r\n936297014.1164\r\n0.0000\r\n37.7757", output);
	}
}
