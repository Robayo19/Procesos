package Vistas;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Controlador.ProcesoEjercicio1;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VistaEjercicio1 {

	private JFrame frame;
	private JTextField txtEntrada;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEjercicio1 window = new VistaEjercicio1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaEjercicio1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 493, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnTerminal = new JButton("TERMINAL");
		btnTerminal.setBounds(81, 32, 130, 60);
		frame.getContentPane().add(btnTerminal);
		
		JButton btnSumar = new JButton("SUMAR");
		btnSumar.setBounds(262, 32, 130, 60);
		frame.getContentPane().add(btnSumar);
		
		txtEntrada = new JTextField();
		txtEntrada.setBounds(114, 129, 251, 54);
		frame.getContentPane().add(txtEntrada);
		txtEntrada.setColumns(10);
		
		JTextArea txtCorrecto = new JTextArea();
		txtCorrecto.setBounds(25, 235, 186, 197);
		frame.getContentPane().add(txtCorrecto);
		
		JTextArea txtError = new JTextArea();
		txtError.setBounds(262, 235, 186, 197);
		frame.getContentPane().add(txtError);
		
		JLabel lblNewLabel = new JLabel("Correcto:");
		lblNewLabel.setBounds(84, 212, 49, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Error:");
		lblNewLabel_1.setBounds(332, 210, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnTerminal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcesoEjercicio1.procesoTerminal(txtCorrecto, txtError, txtEntrada);
					JOptionPane.showMessageDialog(null, "PROCESO TERMINADO");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String entrada = txtEntrada.getText();
		        try {
			        String suma= txtEntrada.getText();			          
			        if(entrada.length() == 3 || !contieneLetras(entrada)) {
		            Process process =new ProcessBuilder("cmd","/c","java","-cp","src","Controlador.ProcesoHijoSumar").start();
		            OutputStream mensaje = process.getOutputStream();
		            mensaje.write((suma +"\n").getBytes());
		            mensaje.flush();
		  
		            InputStream respuesta= process.getInputStream();
		            
		            StringBuilder resultado = new StringBuilder();
		            int c;
		            while ((c = respuesta.read()) != -1) {
		                resultado.append((char) c);
		            }
		            
		            	txtCorrecto.setText(resultado.toString());
		            	txtError.setText("");
			        } else {
			        	txtError.setText("ERROR (Maximo 3 caracteres y no pueden haber letras).");
			        	txtCorrecto.setText("");
			        }
			        
			        JOptionPane.showMessageDialog(null, "PROCESO TERMINADO");
		            
	        	} catch (Exception e1) {
	        		e1.printStackTrace();
				}
			}

		});
	}
	
	public static boolean contieneLetras(String string) {
        Pattern letras = Pattern.compile("[a-zA-Z]");
        Matcher matcher = letras.matcher(string);
      
        return !matcher.find();
    }

}
