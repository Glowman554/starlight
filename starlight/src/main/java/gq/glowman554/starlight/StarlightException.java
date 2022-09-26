package gq.glowman554.starlight;

import java.io.IOException;

public class StarlightException extends IOException
{
	private static final long serialVersionUID = -3194010576955959611L;

	public StarlightException(String error)
	{
		super(error);
	}

	public StarlightException(Exception e)
	{
		super("Could not load plugin because of a " + e.getClass().getSimpleName() + " exception! Message: " + e.getMessage());
	}
}
