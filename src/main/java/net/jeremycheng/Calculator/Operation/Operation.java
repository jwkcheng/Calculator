package net.jeremycheng.Calculator.Operation;

import java.util.List;

import com.google.common.primitives.Ints;

import net.jeremycheng.Calculator.InvalidInputException;
import net.jeremycheng.Calculator.Memory;

public interface Operation
{

	public abstract int getNumParameters();

	public abstract int evaluate() throws InvalidInputException;

	public abstract void setArguments(List<Object> arguments);

	public abstract List<Object> getArguments();

	public default int getArgumentValue(int index) throws InvalidInputException
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
}
