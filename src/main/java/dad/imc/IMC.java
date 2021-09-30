package dad.imc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IMC extends Application {
	
	private TextField pesoText;
	private TextField alturaText;
	
	private Label pesoLabel;
	private Label alturaLabel;
	
	
	private Label imcLabel;
	private Label textLabel;

	@Override
	public void start(Stage primaryStage) throws Exception {

		pesoText = new TextField();
		alturaText = new TextField();
		pesoLabel = new Label("x");
		alturaLabel = new Label("x");
		
		HBox root = new HBox(5, pesoLabel ,pesoText);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
