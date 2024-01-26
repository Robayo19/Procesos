package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProcesoEjercicio1 {

	public static void procesoTerminal(JTextArea jTextArea1, JTextArea jTextArea2, JTextField jTextField) throws IOException {
		Process process = new ProcessBuilder("CMD", "/C", jTextField.getText()).start();
		int c;
		int valorExit;
		String string = "";
		try {
			valorExit = process.waitFor();
			if (valorExit == 0) {
				InputStream inputStream = process.getInputStream();
				while ((c = inputStream.read()) != -1) {
					string += (char) c;
				}
				jTextArea1.setText(string);
				jTextArea2.setText("");
			} else {
				InputStream inputStream = process.getErrorStream();
				while ((c = inputStream.read()) != -1) {
					string += (char) c;
				}
				jTextArea2.setText(string);
				jTextArea1.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
