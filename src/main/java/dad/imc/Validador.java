package dad.imc;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;

import javafx.scene.control.Control;

public class Validador implements Validator<String> {
	
	
	private int max; 
	private String mensaje; 
	
	public Validador(int max, String mensaje){
		setMax(max);
		setMensaje(mensaje);
		
	}
	
	@Override
	public ValidationResult apply(Control control, String value) {
   		boolean condition = false;
		try {
			int val = Integer.parseInt(value);
			
			if (val > getMax() || val == 0) {
				condition = true;
			}else {
				condition = false;
			}
		} catch (NumberFormatException e) {
			condition = true;
		}
        return ValidationResult.fromMessageIf(control, getMensaje(), Severity.ERROR, condition);
	}




	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
