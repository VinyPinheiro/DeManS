package exception;

import dao.Dao;

public class DaoException extends Exception {
	
	private String classException;

	public DaoException(String message, String classException) {
		super(message);
		setClassException(classException);
	}

	public String getClassException() {
		return classException;
	}

	public void setClassException(String classException) {
		this.classException = classException;
	}


}
