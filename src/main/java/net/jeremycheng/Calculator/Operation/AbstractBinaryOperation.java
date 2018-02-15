package net.jeremycheng.Calculator.Operation;

import net.jeremycheng.Calculator.InvalidInputException;

public abstract class AbstractBinaryOperation extends AbstractOperation
{

	private static final int NUM_PARAMETERS = 2;

	public int getNumParameters()
	{
		return NUM_PARAMETERS;
	}

	protected abstract int calculate(int left, int right) throws InvalidInputException;

	@Override
	public int evaluate() throws InvalidInputException
	{
		if (arguments.size() != 2)
		{
			throw new InvalidInputException("Invalid number of arguments for operation");
		}
		return calculate(getArgumentValue(0), getArgumentValue(1));
	}
}
