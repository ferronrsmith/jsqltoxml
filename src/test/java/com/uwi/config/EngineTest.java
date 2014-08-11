package com.uwi.config;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.uwi.utils.Misc;

public class EngineTest {

	@Test
	public void test() throws IOException {
		String outputf = "target/result.xml";
		Engine.main(new String[] { "-x", "xml/countries.xml", "-s",
				"select * from country where name = 'India'", "-o", outputf });
		String result = Misc.readFilesToString(outputf);
		assertEquals("<country> \n" + "  <name>India</name>  \n"
				+ "  <food>rice</food>  \n" + "  <food>curry</food>  \n"
				+ "  <food>roti</food>  \n" + "  <food>tandoori</food>  \n"
				+ "  <drink>coconut water</drink>  \n"
				+ "  <drink>lemon water</drink>  \n"
				+ "  <food>pulao</food>  \n" + "  <food>barfi</food>  \n"
				+ "  <food>palak paneer</food>  \n"
				+ "  <food>sahi kabab</food> \n" + "</country>\r\n", result);
	}
}
