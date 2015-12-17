package p1012;

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
		input = "2\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("90", output);
	}

	@Test
	public void test3() {
		input = "3\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("891", output);
	}

	@Test
	public void test4() {
		input = "4\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("8829", output);
	}

	@Test
	public void test5() {
		input = "5\r\n10\r\n";
		output = run(input);
		Assert.assertEquals("87480", output);
	}

	@Test
	public void testMax1() {
		input = "2\r\n16\r\n";
		output = run(input);
		Assert.assertEquals("240", output);
	}

	@Test
	public void testMax2() {
		input = "8\r\n8\r\n";
		output = run(input);
		Assert.assertEquals("13464808", output);
	}

	@Test
	public void testMax3() {
		input = "16\r\n2\r\n";
		output = run(input);
		Assert.assertEquals("1597", output);
	}

	@Test
	public void testMax4() {
		input = "15\r\n3\r\n";
		output = run(input);
		Assert.assertEquals("2781184", output);
	}

	@Test
	public void testMax5() {
		input = "900\r\n900\r\n";
		output = run(input);
		Assert.assertEquals(
				"6565907097285356103688989652156407731984934759880984947112204858512583872310470000850899354871043933603963026185777703706211380040876641388057809119663140026020229871843803415225319256799928187928746274721520229250375934992390938253054167702781705956693432809201262737320424056497842216080058391927993305418630835759601443857036130135315824144650459048340751146452438768025684139086095600449346098101974629582766963138092668775942336817302746209464460447885284696523208840629674241810312419150140914422711590914574522303319862201162707396838854294883896110451764169403404661314926478226473911736428938927322995389894332589851447638712570427670030993647693646728894665814593408626143023345907727923413550554664570740311792002638148274973094948931072389511958636735360667847442059590298088269325024038413350329487429646546250866687811752511633117139419992499265250250561685191157154121775795610870825212868892087213327209912445691927173780738573769405230487914761074857806417166719398801870895885075056812930898490887646396235091695174299157005322820163649471989656893829618292824281162300450394437324956509000540128915138671850586434174643871782567696238448947338984094625395419383009320831377501723353952698970125894080959743699450503809983215257985231421293066423225049382413743979406764423988641823379323758976750565058380096774561192117265750886912266230627533407545006110425854869790198941826179440141510727258598875215512027161429660972223788747298742288165803840711667105046281386718987281140992499741030983760288767762790526791687102110938504923493670496429166754920291515816513809093938779393599979723692522274810086659863549926037436953637295899431493124660211514953703065615138818012853768719988688096552699207182278623820609847585257134286502684148254333362402402133515594232106632427493540619912608273892068462081492670828330172390135738222596133727362737143932847197747349215495163720682385348404744001932641188612226526681350904743506245623185307859968071814315805787163320812017264655929302015861517092379553852631288370057707654319100132052335082567487427635899945388895706950987744478169987797855817391670456857287074728980690355423819197341138481872605948720426953722021148090564753949257608768214514006666521131032083284924335505345737443091428013146625500725316596104343882452230686516142312954675570803486818531517828149213294945004610207878693208825686593918230780972005025085803518531018304461158608873900759793606012573287464096451700936423624482186281465415704787676006481320256685910172353882063777093230547579632105095917153471713823258397061436527906642640463509741983103993010766697589982840138772589458505306055388978812906730001",
				output);
	}
}
