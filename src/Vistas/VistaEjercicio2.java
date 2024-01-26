package Vistas;

import java.awt.EventQueue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Modelo.Cliente;

public class VistaEjercicio2 {

	private JFrame frame;
	int repeticiones1 = (int) (Math.random() * 50) + 10;
	int repeticiones2 = (int) (Math.random() * 50) + 10;
	int repeticiones3 = (int) (Math.random() * 50) + 10;
	
	private static ReentrantLock lockA = new ReentrantLock();
	private static ReentrantLock lockB = new ReentrantLock();
	private static ReentrantLock lockC = new ReentrantLock();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaEjercicio2 window = new VistaEjercicio2();
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
	public VistaEjercicio2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 856, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel boxGeneral = new JPanel();
		boxGeneral.setBounds(27, 158, 102, 99);
		frame.getContentPane().add(boxGeneral);

		JPanel boxVIP = new JPanel();
		boxVIP.setBounds(364, 158, 102, 99);
		frame.getContentPane().add(boxVIP);

		JPanel boxSuperVIP = new JPanel();
		boxSuperVIP.setBounds(649, 158, 102, 99);
		frame.getContentPane().add(boxSuperVIP);

		JLabel lblNewLabel = new JLabel("Entrada General");
		lblNewLabel.setBounds(40, 133, 133, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEntradaVip = new JLabel("Entrada VIP");
		lblEntradaVip.setBounds(381, 133, 151, 14);
		frame.getContentPane().add(lblEntradaVip);

		JLabel lblAbonoVip = new JLabel("Abono VIP");
		lblAbonoVip.setBounds(669, 133, 120, 14);
		frame.getContentPane().add(lblAbonoVip);

		JLabel lblNewLabel_1 = new JLabel("Facturación:");
		lblNewLabel_1.setBounds(297, 51, 80, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblFacturacion = new JLabel("0");
		lblFacturacion.setBounds(459, 51, 37, 14);
		frame.getContentPane().add(lblFacturacion);
		
		JLabel lblNewLabel_2 = new JLabel("€");
		lblNewLabel_2.setBounds(497, 51, 18, 14);
		frame.getContentPane().add(lblNewLabel_2);

		frame.setVisible(true);
		


		for (int i = 1; i <= repeticiones1; i++) {
			final int numero = i;
			Thread hiloA = new Thread(() -> {
				Cliente clienteGeneralCliente = new Cliente("S", boxSuperVIP);
				try {
					clienteGeneralCliente.pagar(boxGeneral, numero, lockA, Color.YELLOW);
					sumarPrecio(lblFacturacion, 60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			hiloA.start();
		}
		
		for (int i = 1; i <= repeticiones2; i++) {
			final int numero = i;
			Thread hiloB = new Thread(() -> {
				Cliente clienteVIPCliente = new Cliente("S", boxVIP);
				try {
					clienteVIPCliente.pagar(boxVIP, numero, lockB, Color.BLUE);
					sumarPrecio(lblFacturacion, 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			hiloB.start();
		}
		
		for (int i = 1; i <= repeticiones3; i++) {
			final int numero = i;
			Thread hiloC = new Thread(() -> {
				Cliente clienteSuperVIPCliente = new Cliente("S", boxSuperVIP);
				try {
					clienteSuperVIPCliente.pagar(boxSuperVIP, numero, lockC, Color.RED);
					sumarPrecio(lblFacturacion, 150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			hiloC.start();
		}

	}
	public static void pagar(int numeroCaja,JPanel panel) {
		switch (numeroCaja) {
		case 1: {
			lockA.lock();
			try {
				panel.setBackground(Color.black);
				System.out.println("gola");
				Thread.sleep(3000);

			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
				try {
					panel.setBackground(Color.white);
					System.out.println("golas");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					lockA.unlock();

				}

				


			}
			
			break;
		}
		}
	}
	
	public static void sumarPrecio(JLabel jLabel, int precio) {
		SwingUtilities.invokeLater(new Runnable() {
			 
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int suma = Integer.parseInt(jLabel.getText().trim());
				int resultado = suma + precio;
				jLabel.setText(String.valueOf(resultado));
			}
		});
	}

}


