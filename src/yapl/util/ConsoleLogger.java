package yapl.util;

public class ConsoleLogger implements Logger {

	@Override
	public void log(String message) {
		System.out.println(message);
	}

	@Override
	public void log(String format, Object... objects) {
		log(String.format(format, objects));
	}

}
