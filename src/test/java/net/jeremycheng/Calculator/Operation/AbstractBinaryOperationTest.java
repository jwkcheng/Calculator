package net.jeremycheng.Calculator.Operation;

import java.util.Arrays;

import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;

public class AbstractBinaryOperationTest
{

	@Test
	public void testEvaluateCorrectNumberParameters() throws InvalidInputException
	{
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("4", "4"));
		addOperation.evaluate();
	}

	@Test(expected = InvalidInputException.class)
	public void testEvaluateInvalidNumberParameters() throws InvalidInputException
	{
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("4", "4", "4"));
		addOperation.evaluate();
	}

}
