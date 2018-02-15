package net.jeremycheng.Calculator.Operation;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOperation implements Operation
{
	List<Object> arguments = new ArrayList<>();

	public void setArguments(List<Object> arguments)
	{
		this.arguments.clear();
		this.arguments.addAll(arguments);
	}

	public List<Object> getArguments()
	{
		return arguments;
	}

}
