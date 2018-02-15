package net.jeremycheng.Calculator.Operation;

import com.google.common.base.Enums;
import com.google.common.base.Optional;

import net.jeremycheng.Calculator.InvalidInputException;

public class OperationFactory
{

	public enum OperationType
	{
		ADD, SUB, DIV, MUL, LET
	}

	public static Operation getOperation(String operationStr) throws InvalidInputException
	{
		Optional<OperationType> operationType = Enums.getIfPresent(OperationType.class, operationStr.toUpperCase());
		if (operationType.isPresent())
		{
			switch (operationType.get())
			{
			case ADD:
				return new AddOperation();
			case SUB:
				return new SubOperation();
			case MUL:
				return new MulOperation();
			case DIV:
				return new DivOperation();
			case LET:
				return new LetOperation();
			default:
				return null;
			}
		}
		throw new InvalidInputException("Invalid Operation found: " + operationStr);
	}

}
