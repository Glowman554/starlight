package gq.glowman554.starlight.data;

import java.lang.reflect.Method;

import gq.glowman554.starlight.StarlightAutoToString;

public class EventData extends StarlightAutoToString
{
	public final Object source;
	public final Method target;
	public final byte priority;

	public EventData(Object source, Method target, byte priority)
	{
		this.source = source;
		this.target = target;
		this.priority = priority;
	}
}