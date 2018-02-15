package net.jeremycheng;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.jeremycheng.Calculator.Calculator;
import net.jeremycheng.Calculator.InvalidInputException;

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
	}

	@Test
	public void testEvaluateExpressionAdd() throws InvalidInputException
	{
		assertEquals(7, calculator.evaluate("add(4,3)"));
	}

	@Test
	public void testEvaluateExpressionAddNegative() throws InvalidInputException
	{
		assertEquals(-7, calculator.evaluate("add(-4,-3)"));
	}

	@Test
	public void testEvaluateExpressionSub() throws InvalidInputException
	{
		assertEquals(1, calculator.evaluate("sub(4,3)"));
	}

	@Test
	public void testEvaluateExpressionSubNegative() throws InvalidInputException
	{
		assertEquals(30111, calculator.evaluate("sub(111,-30000)"));
	}

	@Test
	public void testEvaluateExpressionMul() throws InvalidInputException
	{
		assertEquals(12, calculator.evaluate("mul(4,3)"));
	}

	@Test
	public void testEvaluateExpressionDivWithRounding() throws InvalidInputException
	{
		assertEquals(1, calculator.evaluate("div(4,3)"));
	}

	@Test
	public void testEvaluateExpressionDivNoRounding() throws InvalidInputException
	{
		assertEquals(4, calculator.evaluate("div(12,3)"));
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

	@Test
	public void testLetSimple() throws InvalidInputException
	{
		assertEquals(10, calculator.evaluate("let(a,5,add(a,a))"));
	}

	@Test
	public void testLetChained1() throws InvalidInputException
	{
		assertEquals(55, calculator.evaluate("let(a, 5, let(b, mul(a, 10), add(b, a))) "));
	}

	@Test
	public void testLetChained2() throws InvalidInputException
	{
		assertEquals(40, calculator.evaluate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))"));
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
