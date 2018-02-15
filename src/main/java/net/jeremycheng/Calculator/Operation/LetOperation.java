package net.jeremycheng.Calculator.Operation;

import net.jeremycheng.Calculator.Memory;

public class LetOperation extends AbstractOperation
{

	private static final int NUM_PARAMETERS = 3;

	private static final String VALID_VARIABLE_REGEX = "^([a-zA-Z_][a-zA-Z0-9_]*)$";

	@Override
	public int getNumParameters()
	{
		// TODO Auto-generated method stub
		return NUM_PARAMETERS;
	}

	@Override
	public int evaluate()
	{
		if (arguments.size() != NUM_PARAMETERS)
		{
			throw new IllegalArgumentException("Invalid number of arguments for operation");
		}
		String first = ((String) arguments.get(0)).trim();
		if (!first.matches(VALID_VARIABLE_REGEX))
		{
			throw new IllegalArgumentException("Invalid variable name: " + first + " for LET operation");
		}

		Memory.getInstance().put(first, getArgumentValue(1));

		return getArgumentValue(2);

	}

}
