package gq.glowman554.test;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.starlight.Starlight;
import gq.glowman554.starlight.StarlightException;

public class Main
{
	public static void main(String[] args) throws StarlightException
	{
		Reflex.setDebug(true);
		Starlight.setDebug(true);

		Starlight s = new Starlight("plugins");
		s.load();
	}
}
