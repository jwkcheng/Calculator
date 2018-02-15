package net.jeremycheng.Calculator.Operation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;

public class MulOperationTest
{

	@Test
	public void testEvaluate() throws InvalidInputException
	{
		MulOperation mulOperation = new MulOperation();
		mulOperation.setArguments(Arrays.asList("4", "3"));
		assertEquals(12, mulOperation.evaluate());
	}

}
