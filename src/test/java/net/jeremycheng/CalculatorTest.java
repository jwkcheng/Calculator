package net.jeremycheng;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.jeremycheng.Calculator.Calculator;

public class CalculatorTest {

	private Calculator calculator;
	@Before
	public void setUp() throws Exception {
		calculator = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluateExpressionAdd() {
		assertEquals(7, calculator.evaluate("add(4,3)"));
	}

	@Test
	public void testEvaluateExpressionAddNegative() {
		assertEquals(-7, calculator.evaluate("add(-4,-3)"));
	}

	@Test
	public void testEvaluateExpressionSub() {
		assertEquals(1, calculator.evaluate("sub(4,3)"));
	}

	@Test
	public void testEvaluateExpressionSubNegative() {
		assertEquals(30111, calculator.evaluate("sub(111,-30000)"));
	}
	
	@Test
	public void testEvaluateExpressionMul() {
		assertEquals(12, calculator.evaluate("mul(4,3)"));
	}

	@Test
	public void testEvaluateExpressionDivWithRounding() {
		assertEquals(1, calculator.evaluate("div(4,3)"));
	}
	@Test
	public void testEvaluateExpressionDivNoRounding() {
		assertEquals(4, calculator.evaluate("div(12,3)"));
	}
	
	@Test
	public void testEvaluateExpressionChain1() {
		assertEquals(7, calculator.evaluate("add(1,mul(2,3))"));
	}
	
	@Test
	public void testEvaluateExpressionChain2() {
		assertEquals(12, calculator.evaluate("mul(add(2, 2), div(9, 3))"));
	}
	
	@Test
	public void testEvaluateExpressionChain3() {
		assertEquals(13, calculator.evaluate("add(1, mul(2, mul(2, 3)))"));
	}

	@Test
	public void testEvaluateExpressionChain4() {
		assertEquals(13, calculator.evaluate("add(sub(47, 46), mul(2, mul(2, 3)))"));
	}
	@Test
	public void testEvaluateExpressionRandomValidWhitespace() {
		assertEquals(13, calculator.evaluate("  add (sub(  47, 46  ), mul(2, mul(2, 3) ) )   "));
	}

	@Test
	public void testLetSimple() {
		assertEquals(10, calculator.evaluate("let(a,5,add(a,a))"));
	}

	@Test
	public void testLetChained1() {
		assertEquals(55, calculator.evaluate("let(a, 5, let(b, mul(a, 10), add(b, a))) "));
	}

	@Test
	public void testLetChained2() {
		assertEquals(40, calculator.evaluate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))"));
	}
	
}
