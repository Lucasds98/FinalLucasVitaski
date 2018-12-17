package EFAXPRODUTO;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


	public class Main extends Application{

		@Override
		public void start(Stage primaryStage) throws IOException {

			Pane root = FXMLLoader.load(getClass().getResource("FXMLProduto.fxml"));
			
			Scene scene = new Scene(root, 730, 547);
			primaryStage.setScene(scene);
			primaryStage.show(); 
			
		}
		
		public static void main(String[] args) {
			launch(args);
		}
}

