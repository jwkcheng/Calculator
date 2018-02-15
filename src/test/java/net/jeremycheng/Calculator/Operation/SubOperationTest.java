package net.jeremycheng.Calculator.Operation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;

public class SubOperationTest
{

	private SubOperation addOperation;

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testEvaluate() throws InvalidInputException
	{
		SubOperation subOperation = new SubOperation();
		subOperation.setArguments(Arrays.asList("4", "3"));
		assertEquals(1, subOperation.evaluate());
	}

	@Test
	public void testEvaluateSubNegative() throws InvalidInputException
	{
		SubOperation subOperation = new SubOperation();
		subOperation.setArguments(Arrays.asList("111", "-30000"));
		assertEquals(30111, subOperation.evaluate());
	}

}
