package net.jeremycheng.Calculator.Operation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;

public class DivOperationTest
{
	@Test
	public void testCalculate() throws InvalidInputException
	{
		DivOperation divOperation = new DivOperation();
		divOperation.setArguments(Arrays.asList("4", "2"));
		assertEquals(2, divOperation.evaluate());
	}

	@Test
	public void testCalculateWithRounding() throws InvalidInputException
	{
		DivOperation divOperation = new DivOperation();
		divOperation.setArguments(Arrays.asList("7", "3"));
		assertEquals(2, divOperation.evaluate());
	}

	@Test(expected = InvalidInputException.class)
	public void testCalculateDivideByZero() throws InvalidInputException
	{
		DivOperation divOperation = new DivOperation();
		divOperation.setArguments(Arrays.asList("4", "0"));
		divOperation.evaluate();
	}

}
