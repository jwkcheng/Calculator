package net.jeremycheng.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.primitives.Ints;

import net.jeremycheng.Calculator.Operation.Operation;
import net.jeremycheng.Calculator.Operation.OperationFactory;

public class Calculator
{

	private static final Pattern EXPRESSION_PATTERN = Pattern
			.compile("^([+-]*[0-9]+)$|^([a-zA-Z_][a-zA-Z0-9_]*)$|^\\s*(?<oper>[a-zA-Z]+)\\s*\\((?<args>.*)\\)\\s*$");

	private static final Pattern OPERATION_PATTERN = Pattern
			.compile("^\\s*(?<oper>[a-zA-Z]+)\\s*\\((?<args>.*)\\)\\s*$");

	/**
	 * 
	 * @param inputExpression
	 * @return given an inputExpression, evaluate and return the calculated integer
	 *         value;
	 * @throws InvalidInputException
	 */
	public int evaluate(String inputExpression) throws InvalidInputException
	{
		Integer value;
		Object result = evaluateExpression(inputExpression);
		if (result instanceof Operation)
		{
			value = ((Operation) result).evaluate();
		} else
		{
			String resultString = (String) result;
			value = Ints.tryParse(resultString);
			if (value == null && Memory.getInstance().contains(resultString))
			{
				value = Memory.getInstance().get(resultString);
			}
		}
		return value;
	}

	/**
	 * 
	 * @param input
	 * @return Return the variable value if it currently exists in memory, the
	 *         expression evaluated to an Operation object, else the input as is.
	 * @throws InvalidInputException
	 */
	private Object evaluateExpression(String input) throws InvalidInputException
	{
		String sanitized = input.trim();
		verifyValidExpression(sanitized);

		Matcher matcher = OPERATION_PATTERN.matcher(sanitized);
		if (matcher.matches())
		{
			return parseOperation(matcher.group("oper"), matcher.group("args"));
		} else if (Memory.getInstance().contains(sanitized))
		{
			// If we have the variable in memory at this point we can just return it.
			return String.valueOf(Memory.getInstance().get(sanitized));
		} else
		{
			return sanitized;
		}
	}

	/**
	 * 
	 * @param expressionString
	 * @throws InvalidInputException
	 *             if expressionString is not a valid expression string.
	 */
	private void verifyValidExpression(String expressionString) throws InvalidInputException
	{
		Matcher matcher = EXPRESSION_PATTERN.matcher(expressionString);
		if (!matcher.matches())
		{
			throw new InvalidInputException("Invalid expression found: '" + expressionString + "'");
		}

	}

	/**
	 * 
	 * @param operationString
	 *            the operation portion of a valid operation expression
	 * @param argsString
	 *            the comma separated string of arguments in a valid operation
	 *            expression
	 * @return an Operation object representation of the operation expression
	 * @throws InvalidInputException
	 */
	private Operation parseOperation(String operationString, String argsString) throws InvalidInputException
	{
		Operation currOperation = OperationFactory.getOperation(operationString);

		if (currOperation != null)
		{
			currOperation.setArguments(evaluateNestedOperations(parseArguments(argsString)));
			return currOperation;
		} else
			throw new InvalidInputException("Input appears to have an operation format, but invalid operation found.");
	}

	/**
	 * 
	 * @param argsString
	 * @return A list of strings representing the arguments of the current (top)
	 *         level Operation.
	 */
	private List<String> parseArguments(String argsString)
	{
		List<String> argList = new ArrayList<>();
		StringBuilder currToken = new StringBuilder("");

		int parenthesesCounter = 0;
		for (int i = 0; i < argsString.length(); i++)
		{
			char currChar = argsString.charAt(i);
			switch (currChar)
			{
			case '(':
				parenthesesCounter++;
				currToken.append(currChar);
				break;
			case ')':
				parenthesesCounter--;
				currToken.append(currChar);
				break;
			case ',':
				if (parenthesesCounter == 0)
				{
					argList.add(currToken.toString());
					currToken.setLength(0);
				} else
				{
					currToken.append(currChar);
				}
				break;
			default:
				currToken.append(currChar);

			}
		}
		argList.add(currToken.toString());

		return argList;
	}

	/**
	 * Recursive Method to look for nested Operations in the list of string
	 * parameters.
	 * 
	 * @param parameters
	 * @return An object list representation of String parameters (values and
	 *         variables) and nested Operation objects.
	 * @throws InvalidInputException
	 */
	private List<Object> evaluateNestedOperations(List<String> parameters) throws InvalidInputException
	{
		List<Object> arguments = new ArrayList<>();
		for (String currParam : parameters)
		{
			arguments.add(evaluateExpression(currParam));
		}

		return arguments;
	}

}
