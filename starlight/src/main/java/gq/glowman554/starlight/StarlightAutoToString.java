package gq.glowman554.starlight;

import java.lang.reflect.Field;

import gq.glowman554.starlight.annotations.StarlightHidden;
import gq.glowman554.starlight.utils.TableBuilder;

public class StarlightAutoToString
{

	public static String autoToString(Object o)
	{
		Class<?> clazz = o.getClass();
		Starlight.log("Generating toString string for " + clazz.getSimpleName());
		
		TableBuilder tb = new TableBuilder();
		tb.initCollums(new String[] { "Filed name", "Field value" });

		for (Field f : clazz.getDeclaredFields())
		{
			if (f.isAnnotationPresent(StarlightHidden.class))
			{
				tb.pushRow(new String[] { f.getName(), "<hidden>" });
			}
			else
			{
				try
				{
					boolean access = f.canAccess(o);
					if (!access)
					{
						Starlight.log("Trying to set accessible for " + f.getName());
						f.setAccessible(!access);
					}
					
					tb.pushRow(new String[] { f.getName(), f.get(o).toString() });
				}
				catch (IllegalArgumentException | IllegalAccessException e)
				{
					tb.pushRow(new String[] { f.getName(), "<error>: " + e.getMessage() });
				}
			}
		}
		
		return tb.toString();
	}

	@Override
	public String toString()
	{
		return autoToString(this);
	}
}
