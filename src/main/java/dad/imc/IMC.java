package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	private TextField pesoText;
	private TextField alturaText;
	
	private Label pesoLabel;
	private Label alturaLabel;
	
	private Label imcLabel;
	private Label imc2Label;
	private Label textLabel;
	
	private Label kgLabel;
	private Label cmLabel;
	
	private DoubleProperty peso = new SimpleDoubleProperty();
	private DoubleProperty altura = new SimpleDoubleProperty();
	private DoubleProperty salida = new SimpleDoubleProperty();


	@Override
	public void start(Stage primaryStage) throws Exception {

		pesoText = new TextField();
		alturaText = new TextField();
		
		pesoLabel = new Label("Peso: ");
		alturaLabel = new Label("Altura :");
		
		kgLabel = new Label("kg");
		cmLabel = new Label("cm");
		
		imcLabel = new Label("IMC: ");
		imc2Label = new Label("IMC: ");
		textLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");
		
		HBox h1 = new HBox(5, pesoLabel, pesoText, kgLabel);
		HBox h2 = new HBox(5, alturaLabel ,alturaText, cmLabel);
		HBox h3 = new HBox(5, imcLabel, imc2Label);
		
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		//root.getChildren().addAll(h1 , h2); //, h3, textLabel);
		root.getChildren().addAll(h1, h2, h3, textLabel);
		
		
		Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		 
		peso.addListener((o, ov, nv) -> System.out.println(nv));
		
		altura.addListener((o, ov, nv) -> System.out.println(nv));
		
		
		Bindings.bindBidirectional(pesoText.textProperty(),peso, new NumberStringConverter()
				);
		
		Bindings.bindBidirectional(alturaText.textProperty(),altura, new NumberStringConverter()
				);
		
	
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
