package dad.imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.StringExpression;
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
	private Label textLabel;
	
	private Label kgLabel;
	private Label cmLabel;
	
	private DoubleProperty peso = new SimpleDoubleProperty();
	private DoubleProperty altura = new SimpleDoubleProperty();
	private DoubleProperty salida = new SimpleDoubleProperty();
	
	private String imcText;


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
		root.getChildren().addAll(h1, h2, h3, textLabel);
		
		
		Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		StringExpression imcExpression = 
				Bindings.concat("IMC: ")
				.concat(Bindings.concat(Bindings.when(altura.isEqualTo(0)).then("(peso*altura^2)").otherwise(salida.asString())));
		
		imcLabel.textProperty().bind(imcExpression);
		
		Bindings.bindBidirectional(pesoText.textProperty(), peso, new NumberStringConverter());
		Bindings.bindBidirectional(alturaText.textProperty(), altura, new NumberStringConverter());
		
		salida.bind(peso.divide((altura.divide(100).multiply(altura.divide(100)))));
		
		StringExpression imc2 = 
				Bindings.concat(Bindings.when(altura.isEqualTo(0))
						.then("Bajo peso | Normal | Sobrepeso | Obeso")
						.otherwise(imcText + "2"));
		textLabel.textProperty().bind(imc2);
		
		
		salida.addListener((o, ov, nv) ->  { 
			double salidavalue = salida.doubleValue();
			if(salidavalue < 18.5) {
				//textLabel.textProperty().set("Bajo Peso");
				imcText = "Bajo Peso";
			}else if(salidavalue >= 18.5 && salidavalue < 25){
				//textLabel.textProperty().set("Normal");
				imcText = "Normal";
			}else if(salidavalue >= 25 && salidavalue < 30){
				//textLabel.textProperty().set("Sobrepeso");
				imcText = "Sobrepeso";
			}else if(salidavalue >= 30) {
				//textLabel.textProperty().set("Obeso");
				imcText = "Obeso";

			}
		});
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
