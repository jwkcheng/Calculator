package net.jeremycheng.Calculator.Operation;

import java.util.List;

import com.google.common.primitives.Ints;

import net.jeremycheng.Calculator.Memory;

public interface Operation
{

	public abstract int getNumParameters();

	public abstract int evaluate();

	public abstract void setArguments(List<Object> arguments);

	public abstract List<Object> getArguments();

	public default int getArgumentValue(int index)
	{
		Object argument = getArguments().get(index);

		if (argument instanceof Operation)
		{
			return ((Operation) argument).evaluate();
		} else
		{
			String argString = (String) argument;
			Integer intValue = Ints.tryParse(argString);
			if (intValue != null)
			{
				return intValue;
			} else if (Memory.getInstance().contains(argString))
			{
				return Memory.getInstance().get(argString);
			} else
			{
				throw new IllegalArgumentException("Invalid argument in Operation");
			}
		}
	}
	// public static Integer getIntegerValue(String argument)
	// {
	// Integers
	// try
	// {
	// return new Integer(argument);
	// } catch (NumberFormatException ex)
	// {
	// // We only care if it is an integer at this point.
	// return null;
	// }
	// }
}
