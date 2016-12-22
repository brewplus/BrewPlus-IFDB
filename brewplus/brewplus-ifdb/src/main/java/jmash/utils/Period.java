package jmash.utils;

public enum Period {
	
	NORMAL(""), CARNIVAL("carnival"), CARNIVAL_WEEK("carnival_week"), EASTER("easter"), EASTER_WEEK("easter_week"), CHRISTMAS("christmas");

	private final String code;

	private Period(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
