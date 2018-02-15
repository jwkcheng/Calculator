package net.jeremycheng.Calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidInputException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(InvalidInputException.class);

	public InvalidInputException(String message)
	{
		super(message);
		// logger.info(message, this);
	}

}
