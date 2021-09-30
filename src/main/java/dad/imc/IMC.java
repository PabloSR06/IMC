package dad.imc;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IMC extends Application {
	
	private TextField pesoText;
	private TextField alturaText;
	
	private Label pesoLabel;
	private Label alturaLabel;
	
	
	private Label imcLabel;
	private Label textLabel;
	
	private Label kgLabel;
	private Label cmLabel;

	@Override
	public void start(Stage primaryStage) throws Exception {

		pesoText = new TextField();
		alturaText = new TextField();
		
		pesoLabel = new Label("Peso: ");
		alturaLabel = new Label("Altura :");
		
		kgLabel = new Label("kg");
		cmLabel = new Label("cm");
		
		imcLabel = new Label("IMC: ");
		textLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");
		
		HBox h1 = new HBox(5, pesoLabel, pesoText, kgLabel);
		HBox h2 = new HBox(5, alturaLabel ,alturaText, cmLabel);
		HBox h3 = new HBox(5, imcLabel);
		
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		//root.getChildren().addAll(h1 , h2); //, h3, textLabel);
		root.getChildren().addAll(h1, h2, h3, textLabel);
		
		
		Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
