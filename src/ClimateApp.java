/** The ClimateApp class serves as the entry point for the climate application.
 *  It initializes the MenuOperations class and displays the main menu.
 */
public class ClimateApp {

   /** The main method of the ClimateApp class.
     * It initializes a MenuOperations object and calls the displayMenu method.
     *
     * @param args Command line arguments (not used in this application).
     */
	public static void main(String[] args) {
		MenuOperations menu = new MenuOperations();
		menu.displayMenu();
	}
}
