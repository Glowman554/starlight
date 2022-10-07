package gq.glowman554.starlight.utils;

import java.util.ArrayList;

public class TableBuilder
{
	private ArrayList<ArrayList<String>> table = new ArrayList<>();

	public String getCell(int row, int column)
	{
		return table.get(column).get(row);
	}

	public void setCell(int row, int column, String value)
	{
		table.get(column).set(row, value);
	}
	
	public void initCollums(String[] names)
	{
		if (table.size() != 0)
		{
			throw new IllegalStateException("Already inizialized!");
		}
		
		for (String c : names)
		{
			ArrayList<String> collum = new ArrayList<>();
			collum.add(c);
			table.add(collum);
		}
	}

	public void pushRow(String[] row)
	{
		if (table.size() == 0)
		{
			throw new IllegalStateException("Not inizialized!");
		}
		
		if (table.size() != row.length)
		{
			throw new IllegalStateException("row has more or less collums than expected!!");
		}
		
		for (int i = 0; i < row.length; i++)
		{
			table.get(i).add(row[i]);
		}
	}

	@Override
	public String toString()
	{
		String ret = "";

		int[] max_lengths = new int[table.size()];

		boolean clip_output = table.get(0).size() > 1000;
		int clip_length = 1000;

		for (int i = 0; i < table.size(); i++)
		{
			int max = 0;

			if (!clip_output)
			{
				for (String cell : table.get(i))
				{
					if (cell.length() > max)
					{
						max = cell.length();
					}
				}
			}
			else
			{
				for (int x = 0; x < clip_length; x++)
				{
					String cell = table.get(i).get(x);
					if (cell.length() > max)
					{
						max = cell.length();
					}
				}
			}

			max_lengths[i] = ++max;
		}

		ret += "|";

		for (int j = 0; j < table.size(); j++)
		{
			String header = "";

			while (header.length() != max_lengths[j])
			{
				header += "-";
			}

			ret += header + "|";
		}

		ret += "\n";

		for (int i = 0; i < (clip_output ? clip_length : table.get(0).size()); i++)
		{
			ret += "|";

			if (i == 1)
			{
				for (int j = 0; j < table.size(); j++)
				{
					String header = "";

					while (header.length() != max_lengths[j])
					{
						header += "-";
					}

					ret += header + "|";
				}

				ret += "\n|";
			}

			for (int j = 0; j < table.size(); j++)
			{
				String cell = table.get(j).get(i);

				while (cell.length() != max_lengths[j])
				{
					cell += " ";
				}

				ret += cell + "|";
			}

			ret += "\n";
		}

		ret += "|";

		for (int j = 0; j < table.size(); j++)
		{
			String header = "";

			while (header.length() != max_lengths[j])
			{
				header += "-";
			}

			ret += header + "|";
		}

		ret += "\n";

		if (clip_output)
		{
			ret += "\nClipped " + (table.get(0).size() - clip_length) + " rows!";
		}

		return ret;
	}
}
