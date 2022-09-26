package gq.glowman554.starlight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Starlight
{
	private static boolean debug = false;

	public static boolean getDebug()
	{
		return Starlight.debug;
	}

	public static void setDebug(boolean debug)
	{
		Starlight.debug = debug;
	}

	public static void log(String msg)
	{
		if (debug)
		{
			System.out.println("[STARLIGHT] " + msg);
		}
	}

	private final String plugin_dir;
	ArrayList<Object> plugins = new ArrayList<Object>();

	public Starlight(String plugin_dir)
	{
		this.plugin_dir = plugin_dir;

		if (!new File(this.plugin_dir).isDirectory())
		{
			new File(this.plugin_dir).mkdir();
		}
	}

	public void load() throws StarlightException
	{
		log("Loading plugins...");
		try
		{
			Files.walk(new File(plugin_dir).toPath()).forEach(this::loader);
		}
		catch (IOException e)
		{
			throw new StarlightException(e);
		}
	}

	/*
	 * public void load_from_url_or_path(String url_or_path) StarlightException
	 * { String regex =
	 * "^(http|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$";
	 * if (url_or_path.matches(regex)) { // URL String download_path =
	 * this.plugin_dir + "/" +
	 * url_or_path.substring(url_or_path.lastIndexOf("/") + 1);
	 * System.out.println("Downloading " + url_or_path + " to " +
	 * download_path);
	 * 
	 * HttpClient.download(download_path, url_or_path);
	 * 
	 * loader(new File(download_path).toPath()); } else { // Path loader(new
	 * File(url_or_path).toPath()); } }
	 */

	private void loader(Path file)
	{
		if (file.equals(new File(plugin_dir).toPath()))
		{
			return;
		}

		File f = file.toFile();
		if (!f.isFile())
		{
			log(String.format("[%s] Not a file!", f.getName()));
		}
		else
			if (f.getName().endsWith(".jar"))
			{
				log(String.format("[%s] Loading plugin...", f.getName()));
				StarlightJar pl = new StarlightJar(f.getAbsolutePath());
				try
				{
					pl.init();
					log(String.format("[%s] Loading %s...", f.getName(), pl.toString()));
					plugins.add(pl.instantiate());
					pl.load();

					log(String.format("[%s] Loaded %s!", f.getName(), pl.toString()));
				}
				catch (StarlightException e)
				{
					log(String.format("[%s] Failed to load plugin: %s", f.getName(), e.getMessage()));
				}
			}
			else
			{
				log("Unsupported plugin format for file: " + f.getName());
			}
	}
}
