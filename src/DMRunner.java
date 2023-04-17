import controller.DMController;
import model.DMModel;
import view.DMView;

/**
 * The runner class contains the main method and it runs the program.
 * Inside this class are the MVC elements which utilize the necessary
 * functionalities by applying the standard MVC architecture and enhance the
 * performance of the program.
 */
public class DMRunner {
    public static void main(String[] args) {

        // initialize the Model, View and Controller
        DMModel model = new DMModel();
        DMView view = new DMView();
        new DMController(model, view);
    }
}
