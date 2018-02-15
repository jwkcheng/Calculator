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

	// private static final Pattern EXPRESSION_PATTERN = Pattern
	// .compile("^([0-9]+)$|^*([a-zA-Z_][a-zA-Z0-9_]+)$|^\\s*(?<oper>[a-zA-Z]+)\\s*\\((?<args>.*)\\)\\s*$");

	private static final Pattern OPERATION_PATTERN = Pattern
			.compile("^\\s*(?<oper>[a-zA-Z]+)\\s*\\((?<args>.*)\\)\\s*$");

	public int evaluate(String input)
	{
		Integer value;
		Object result = evaluateExpression(input);
		// Integer value = Ints.tryParse(string)(result);
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
		Memory.getInstance().clear();
		return value;
	}

	/**
	 * 
	 * @param input
	 * @return the input if it is an integer, else return the expression evaluated
	 */
	private Object evaluateExpression(String input)
	{
		String sanitized = input.trim();
		// Optional<Integer> result = isInteger(sanitized);
		// if (result.isPresent())
		// {
		// return result.get();
		// }

		Matcher matcher = OPERATION_PATTERN.matcher(sanitized);
		if (matcher.matches())
		{

			String argsString = matcher.group("args");
			String operationString = matcher.group("oper");
			return parseOperation(argsString, operationString);
		} else if (Memory.getInstance().contains(sanitized))
		{
			return String.valueOf(Memory.getInstance().get(sanitized));
		} else
		{
			return sanitized;
		}
		// throw new IllegalArgumentException("Error, shouldn't reach this");
	}

	private Operation parseOperation(String argsString, String operationString)
	{
		Operation currOperation = OperationFactory.getOperation(operationString);

		if (currOperation != null)
		{
			currOperation.setArguments(evaluateArguments(currOperation, parseArguments(argsString)));
			return currOperation;
		} else
			throw new IllegalArgumentException();
	}

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

	private List<Object> evaluateArguments(Operation operation, List<String> parameters)
	{
		if (parameters.size() != operation.getNumParameters())
		{
			throw new IllegalArgumentException("Invalid number of arguments for operation");
		}
		List<Object> arguments = new ArrayList<>();
		for (String currParam : parameters)
		{
			arguments.add(evaluateExpression(currParam));
		}

		return arguments;
		// return operation.calculate(arguments.toArray(new Object[arguments.size()]));
	}

	private static Integer isInteger(String s)
	{
		try
		{
			return new Integer(s);
		} catch (NumberFormatException ex)
		{
			// We only care if it is an integer at this point.
			return null;
		}

	}

}
