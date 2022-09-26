package gq.glowman554.plugin;

import gq.glowman554.starlight.annotations.StarlightEntry;
import gq.glowman554.starlight.data.PluginData;
import gq.glowman554.test.Test;

public class Entry
{
	@StarlightEntry(data = true)
	public void entry(PluginData data)
	{
		System.out.println("I am " + data.toString());

		new Test().sayHello();
	}
}
