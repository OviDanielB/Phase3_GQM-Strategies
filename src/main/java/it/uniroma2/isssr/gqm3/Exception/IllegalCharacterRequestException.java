package it.uniroma2.isssr.gqm3.Exception;

@SuppressWarnings("serial")
public class IllegalCharacterRequestException extends Exception {

	private static String messagePrefix = "Invalid character in name ";

	private static String buildMessage(String name, char character) {

		return messagePrefix + name + " : " + character;
	}

	private static String buildMessage(String name) {

		return messagePrefix + name;
	}

	public IllegalCharacterRequestException() {
	}

	public IllegalCharacterRequestException(String name) {
		super(buildMessage(name));
	}

	public IllegalCharacterRequestException(String name, char character) {
		super(buildMessage(name, character));
	}

	public IllegalCharacterRequestException(Throwable cause) {
		super(cause);
	}

	public IllegalCharacterRequestException(String name, char character, Throwable cause) {
		super(buildMessage(name, character), cause);
	}

	public IllegalCharacterRequestException(String name, char character, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(buildMessage(name, character), cause, enableSuppression, writableStackTrace);
	}

}
