package net.jeremycheng.Calculator.Operation;

import net.jeremycheng.Calculator.InvalidInputException;

public class DivOperation extends AbstractBinaryOperation
{

	@Override
	public int calculate(int left, int right) throws InvalidInputException
	{
		if (right == 0)
			throw new InvalidInputException("Cannot divide by 0");
		return left / right;
	}

}
