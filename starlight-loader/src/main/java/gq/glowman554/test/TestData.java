package gq.glowman554.test;

import gq.glowman554.starlight.Starlight;
import gq.glowman554.starlight.StarlightAutoToString;
import gq.glowman554.starlight.annotations.StarlightHidden;

public class TestData extends StarlightAutoToString
{
	public String test1 = "hello";
	public int test2 = 10;
	private String test3 = "hello from a private field";
	private int test4 = 20;
	
	@StarlightHidden
	public String test5 = "idk";
	@StarlightHidden
	private String test6 = "idk2";
}
