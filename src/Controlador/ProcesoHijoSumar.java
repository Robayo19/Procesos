package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProcesoHijoSumar {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String jTextField = reader.readLine();
		try {
			String[] numeros = jTextField.split(",");
			int n1 = Integer.parseInt(numeros[0].trim());
			int n2 = Integer.parseInt(numeros[1].trim());
			
			int suma = n1 + n2;
			
			System.out.println(Integer.toString(suma));

            System.out.println(Integer.toString(suma));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
