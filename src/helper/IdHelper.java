package helper;

public class IdHelper {
	private static int id;
	
	public static int buildId() {
		id++;
		return id;
	}
}
