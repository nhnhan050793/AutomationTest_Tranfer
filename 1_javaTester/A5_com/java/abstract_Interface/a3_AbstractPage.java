package A5_com.java.abstract_Interface;

public abstract class a3_AbstractPage implements a1_IPage {

	public abstract boolean isPageLoaded();
	//public abstract
	
	public abstract void clickToElement(String locator);
	//default abstract
	
	public void sendkeyToElement(String locator, String value) {
	}
	//default default
	
	protected void selectItemDrodown() {
		
	}
	
//	private void hoverMouse() {
//		
//	}
}
