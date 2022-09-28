package gq.glowman554.starlight;

public class StarlightEventCancelable extends StarlightEvent
{
	private boolean canceled = false;

	public boolean isCanceled()
	{
		return canceled;
	}

	public void setCanceled(boolean canceled)
	{
		this.canceled = canceled;
	}
}
