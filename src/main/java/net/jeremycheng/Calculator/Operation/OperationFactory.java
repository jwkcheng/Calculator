package net.jeremycheng.Calculator.Operation;

public class OperationFactory {

	public enum OperationType {
		ADD, SUB, DIV, MUL, LET
	}

	public enum specialOperation {
		LET
	}

	public static Operation getOperation(String operationStr) {
		OperationType operationType = OperationType.valueOf(operationStr.toUpperCase());
		switch (operationType) {
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
	
	
}
