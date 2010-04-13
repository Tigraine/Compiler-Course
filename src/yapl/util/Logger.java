package yapl.util;

public interface Logger {
	public void log(String message);
	public void log(String format, Object...objects);
}
