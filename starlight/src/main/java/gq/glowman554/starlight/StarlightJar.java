package gq.glowman554.starlight;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.loaders.ReflexJsonLoader;
import gq.glowman554.starlight.annotations.StarlightEntry;
import gq.glowman554.starlight.data.PluginData;
import gq.glowman554.starlight.utils.StreamReader;

public class StarlightJar
{
	private final String jar_file;

	private URLClassLoader child;
	private Class<?> plugin_class;

	private Object plugin_instance;
	private PluginData plugin_data;

	public StarlightJar(String jar_file)
	{
		this.jar_file = jar_file;
	}

	public void init() throws StarlightException
	{
		try
		{
			this.child = new URLClassLoader(new URL[] {new URL("jar:file:" + jar_file + "!/")}, this.getClass().getClassLoader());

			InputStream plugin_info = child.getResourceAsStream("plugin.json");
			String plugin_json = StreamReader.readFile(plugin_info);

			plugin_data = (PluginData) new Reflex(new ReflexJsonLoader(plugin_json)).load(new PluginData());

			plugin_class = (Class<?>) child.loadClass(plugin_data.main);
		}
		catch (Exception e)
		{
			throw new StarlightException(e);
		}
	}

	@SuppressWarnings("deprecation")
	public Object instantiate() throws StarlightException
	{
		try
		{
			plugin_instance = plugin_class.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new StarlightException(e);
		}

		return plugin_instance;
	}

	public void load() throws StarlightException
	{
		try
		{
			Method entry = null;
			StarlightEntry annot = null;
			for (Method m : plugin_class.getMethods())
			{
				if (m.isAnnotationPresent(StarlightEntry.class))
				{
					entry = m;
					annot = m.getAnnotation(StarlightEntry.class);
				}
			}

			if (entry == null || annot == null)
			{
				throw new IllegalStateException("Pleae provide a @StarlightEntry function");
			}

			if (annot.data())
			{
				entry.invoke(plugin_instance, plugin_data);
			}
			else
			{
				entry.invoke(plugin_instance);
			}
		}
		catch (Exception e)
		{
			throw new StarlightException(e);
		}
	}

	@Override
	public String toString()
	{
		return plugin_data.toString();
	}
}
