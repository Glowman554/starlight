package gq.glowman554.starlight;

import java.util.ArrayList;

import gq.glowman554.starlight.data.EventData;

public class StarlightEvent
{
	final ArrayList<EventData> dataList = StarlightEventManager.get(this.getClass());

	public StarlightEvent call()
	{
		if (dataList != null)
		{
			for (EventData data : dataList)
			{
				try
				{
					data.target.invoke(data.source, this);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return this;
	}
}