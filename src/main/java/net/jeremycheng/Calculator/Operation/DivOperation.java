package net.jeremycheng.Calculator.Operation;

public class DivOperation extends AbstractBinaryOperation {

	@Override
	public int calculate(int left, int right) {
		return left/right;
	}

}
