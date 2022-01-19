package dad.imc;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

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
	
	private Label imcText;


	@Override
	public void start(Stage primaryStage) throws Exception {

		pesoText = new TextField();
		pesoText.setPrefWidth(60);
		
		alturaText = new TextField();
		alturaText.setPrefWidth(60);
		
		
		pesoLabel = new Label("Peso: ");
		alturaLabel = new Label("Altura :");
		
		kgLabel = new Label("kg");
		cmLabel = new Label("cm");
		
		imcLabel = new Label("IMC: ");
		textLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");
		imcText = new Label();
		
		HBox h1 = new HBox(5, pesoLabel, pesoText, kgLabel);
		HBox h2 = new HBox(5, alturaLabel ,alturaText, cmLabel);
		HBox h3 = new HBox(5, imcLabel);
		
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		root.getChildren().addAll(h1, h2, h3, textLabel);
		
		
		Scene scene = new Scene(root, 300, 200);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		

		ValidationSupport support = new ValidationSupport();
	    support.registerValidator(pesoText, true, new Validador(600, "El peso no puede ser superior a 600 kg ni 0"));
	    support.registerValidator(alturaText, true, new Validador(290, "La altura no puede ser superior a 290 cm ni 0"));
	    support.invalidProperty().addListener((o, ov, nv) -> System.out.println(nv ? "is invalid" : "is valid"));
		
		
		
		
		Bindings.bindBidirectional(pesoText.textProperty(), peso, new NumberStringConverter());
		Bindings.bindBidirectional(alturaText.textProperty(), altura, new NumberStringConverter());
		
		salida.bind(peso.divide((altura.divide(100).multiply(altura.divide(100)))));
		
		StringExpression imcExpression = 
				Bindings.concat("IMC: ")
				.concat(Bindings.concat(Bindings.when(altura.isEqualTo(0)).then("(peso*altura^2)").otherwise(salida.asString("%.2f"))));
		
		imcLabel.textProperty().bind(imcExpression);
		
		
		StringExpression imc2 = 
				Bindings.concat(Bindings.when(altura.isEqualTo(0))
						.then("Bajo peso | Normal | Sobrepeso | Obeso")
						.otherwise(imcText.textProperty()));
		textLabel.textProperty().bind(imc2);
		
		
		salida.addListener((o, ov, nv) ->  { 
			double salidavalue = salida.doubleValue();
			if(salidavalue < 18.5) {
				imcText.textProperty().set("Bajo Peso");
			}else if(salidavalue >= 18.5 && salidavalue < 25){
				imcText.textProperty().set("Normal");
			}else if(salidavalue >= 25 && salidavalue < 30){
				imcText.textProperty().set("Sobrepeso");
			}else if(salidavalue >= 30) {
				imcText.textProperty().set("Obeso");
			}
		});
		
	}

	
	public static void main(String[] args) {
		launch(args);
	}

}
