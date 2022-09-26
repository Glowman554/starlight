package gq.glowman554.starlight.data;

import gq.glowman554.reflex.ReflexField;

public class PluginData
{
	@ReflexField
	public String main;
	@ReflexField
	public String name;
	@ReflexField(optional = true)
	public String version;

	@Override
	public String toString()
	{
		return String.format("%s@%s", name, version != null ? version : "unknown");
	}
}
