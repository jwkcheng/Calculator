package net.jeremycheng.Calculator.Operation;

import java.util.List;

import com.google.common.primitives.Ints;

import net.jeremycheng.Calculator.InvalidInputException;
import net.jeremycheng.Calculator.Memory;

public interface Operation
{

	/**
	 * 
	 * @return number of valid parameters in the implemented operation
	 */
	public abstract int getNumParameters();

	/**
	 * 
	 * @return given an initialized Operation Implementation, evaluate the operation
	 *         against the arguments (getArguments())
	 * @throws InvalidInputException
	 */
	public abstract int evaluate() throws InvalidInputException;

	public abstract void setArguments(List<Object> arguments);

	public abstract List<Object> getArguments();

	/**
	 * 
	 * @param index
	 * @return evaluate and return the argument at index.
	 * @throws InvalidInputException
	 */
	public default int getArgumentValue(int index) throws InvalidInputException
	{
		Object argument = getArguments().get(index);
		if (argument == null)
		{
			throw new InvalidInputException("Error evaluating Operation");
		}

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
				throw new InvalidInputException("Invalid argument in Operation");
			}
		}
	}
}
