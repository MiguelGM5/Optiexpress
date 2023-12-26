package optiexpress;

//import Login.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import optiexpress.Login.LoginController;

/**
 *
 * @author Migue
 */
public class Optiexpress extends Application {
    
     @Override
     public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("LoginVista.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Optiexpress");
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
