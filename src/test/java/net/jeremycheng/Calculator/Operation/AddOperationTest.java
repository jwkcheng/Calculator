package net.jeremycheng.Calculator.Operation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;

public class AddOperationTest
{
	@Test
	public void testEvaluate() throws InvalidInputException
	{
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("4", "3"));
		assertEquals(7, addOperation.evaluate());
	}

	@Test
	public void testEvaluateAddNegative() throws InvalidInputException
	{
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("-4", "-3"));
		assertEquals(-7, addOperation.evaluate());
	}

}
