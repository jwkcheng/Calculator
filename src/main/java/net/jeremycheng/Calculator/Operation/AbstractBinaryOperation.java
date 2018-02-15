package net.jeremycheng.Calculator.Operation;

public abstract class AbstractBinaryOperation extends AbstractOperation
{

	private static final int NUM_PARAMETERS = 2;

	public int getNumParameters()
	{
		return NUM_PARAMETERS;
	}

	protected abstract int calculate(int left, int right);

	@Override
	public int evaluate()
	{
		if (arguments.size() != 2)
		{
			throw new IllegalArgumentException("Invalid number of arguments for operation");
		}
		return calculate(getArgumentValue(0), getArgumentValue(1));
	}
}
