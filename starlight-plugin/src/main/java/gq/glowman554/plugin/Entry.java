package gq.glowman554.plugin;

import gq.glowman554.starlight.StarlightEventManager;
import gq.glowman554.starlight.annotations.StarlightEntry;
import gq.glowman554.starlight.annotations.StarlightEventTarget;
import gq.glowman554.starlight.data.PluginData;
import gq.glowman554.test.Test;
import gq.glowman554.test.TestEvent;

public class Entry
{
	@StarlightEntry(data = true)
	public void entry(PluginData data)
	{
		StarlightEventManager.register(this);
		System.out.println("I am " + data.toString());

		new Test().sayHello();
	}
	
	@StarlightEventTarget
	public void onTest(TestEvent e)
	{
		System.out.println("Test event called!");
	}
}
