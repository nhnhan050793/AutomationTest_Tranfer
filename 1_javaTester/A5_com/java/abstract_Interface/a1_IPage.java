package A5_com.java.abstract_Interface;

public interface a1_IPage {
	int Number = 100;
	// static final ( constant = hằng số)
	
	public static final String ADDRESS = "";
	// static final ( tự hiểu public static final)
	
	static final String NAME = "";
	// static final ( tự hiểu public static final)
	
	final String PHONE = "";
	// static final ( tự hiểu public static final)
	
	
	
	public abstract boolean isPageLoaded();
	//public abstract
	
	abstract void clickToElement(String locator);
	//public abstract
	
	void sendkeyToElement(String locator, String value);
	//public abstract
}
