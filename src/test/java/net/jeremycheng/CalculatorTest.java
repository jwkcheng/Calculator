package net.jeremycheng;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.jeremycheng.Calculator.Calculator;
import net.jeremycheng.Calculator.InvalidInputException;
import net.jeremycheng.Calculator.Memory;

public class CalculatorTest
{

	private Calculator calculator;

	@Before
	public void setUp() throws Exception
	{
		calculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception
	{
		Memory.getInstance().clear();
	}

	@Test
	public void testEvaluateExpressionSimple() throws InvalidInputException
	{
		assertEquals(7, calculator.evaluate("add(4,3)"));
	}

	@Test
	public void testEvaluateExpressionChain1() throws InvalidInputException
	{
		assertEquals(7, calculator.evaluate("add(1,mul(2,3))"));
	}

	@Test
	public void testEvaluateExpressionChain2() throws InvalidInputException
	{
		assertEquals(12, calculator.evaluate("mul(add(2, 2), div(9, 3))"));
	}

	@Test
	public void testEvaluateExpressionChain3() throws InvalidInputException
	{
		assertEquals(13, calculator.evaluate("add(1, mul(2, mul(2, 3)))"));
	}

	@Test
	public void testEvaluateExpressionChain4() throws InvalidInputException
	{
		assertEquals(13, calculator.evaluate("add(sub(47, 46), mul(2, mul(2, 3)))"));
	}

	@Test
	public void testEvaluateExpressionRandomValidWhitespace() throws InvalidInputException
	{
		assertEquals(13, calculator.evaluate("  add (sub(  47, 46  ), mul(2, mul(2, 3) ) )   "));
	}

	@Test(expected = InvalidInputException.class)
	public void testMissingParentheses() throws InvalidInputException
	{
		calculator.evaluate("add(5, 5");
	}

	@Test(expected = InvalidInputException.class)
	public void testInvalidOperation() throws InvalidInputException
	{
		calculator.evaluate("exponent(5, 5)");
	}

	@Test(expected = InvalidInputException.class)
	public void testInvalidVariableName() throws InvalidInputException
	{
		calculator.evaluate("let(5a, 5, add(5a,5a)");
	}
}
