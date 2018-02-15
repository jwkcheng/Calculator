package net.jeremycheng.Calculator.Operation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.jeremycheng.Calculator.InvalidInputException;
import net.jeremycheng.Calculator.Memory;

public class LetOperationTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
		Memory.getInstance().clear();
	}

	@Test
	public void testEvaluateSimple() throws InvalidInputException
	{
		LetOperation letOperation = new LetOperation();
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("a", "a"));
		letOperation.setArguments(Arrays.asList("a", "5", addOperation));
		assertEquals(10, letOperation.evaluate());
	}

	@Test
	/**
	 * "let(a, 5, let(b, mul(a, 10), add(b,a)))"
	 * 
	 * @throws InvalidInputException
	 */
	public void testLetChained1() throws InvalidInputException
	{

		MulOperation mulOperation = new MulOperation();
		mulOperation.setArguments(Arrays.asList("a", "10"));

		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("b", "a"));

		LetOperation letOperation2 = new LetOperation();
		letOperation2.setArguments(Arrays.asList("b", mulOperation, addOperation));

		LetOperation letOperation1 = new LetOperation();
		letOperation1.setArguments(Arrays.asList("a", "5", letOperation2));

		assertEquals(55, letOperation1.evaluate());
	}

	@Test
	/**
	 * "let(a, let(b, 10, add(b, b)), let(b,20, add(a, b)))"
	 * 
	 * @throws InvalidInputException
	 */
	public void testLetChained2() throws InvalidInputException
	{
		AddOperation addOperation1 = new AddOperation();
		addOperation1.setArguments(Arrays.asList("b", "b"));

		LetOperation letOperation3 = new LetOperation();
		letOperation3.setArguments(Arrays.asList("b", "10", addOperation1));

		AddOperation addOperation2 = new AddOperation();
		addOperation2.setArguments(Arrays.asList("a", "b"));

		LetOperation letOperation2 = new LetOperation();
		letOperation2.setArguments(Arrays.asList("b", "20", addOperation2));

		LetOperation letOperation1 = new LetOperation();
		letOperation1.setArguments(Arrays.asList("a", letOperation3, letOperation2));

		assertEquals(40, letOperation1.evaluate());
	}

	@Test(expected = InvalidInputException.class)
	/**
	 * "let(a, add(a,a),5)"
	 * 
	 * @throws InvalidInputException
	 */
	public void testInvalidLet1() throws InvalidInputException
	{
		LetOperation letOperation = new LetOperation();
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("a", "a"));
		letOperation.setArguments(Arrays.asList("a", addOperation, "5"));
		assertEquals(10, letOperation.evaluate());
	}

	@Test(expected = InvalidInputException.class)
	/**
	 * "let(a, 5, let(a, 7, add(a,a)))"
	 * 
	 * @throws InvalidInputException
	 */
	public void testInvalidLet2() throws InvalidInputException
	{
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("a", "a"));

		LetOperation letOperation2 = new LetOperation();
		letOperation2.setArguments(Arrays.asList("a", "7", addOperation));

		LetOperation letOperation1 = new LetOperation();
		letOperation1.setArguments(Arrays.asList("a", "5", letOperation2));

		letOperation1.evaluate();
	}

	@Test(expected = InvalidInputException.class)
	public void testInvalidLetInvalidVariableName() throws InvalidInputException
	{
		LetOperation letOperation = new LetOperation();
		AddOperation addOperation = new AddOperation();
		addOperation.setArguments(Arrays.asList("5a", "5a"));
		letOperation.setArguments(Arrays.asList("5a", "5", addOperation));
		assertEquals(10, letOperation.evaluate());
	}

}
