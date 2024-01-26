package Modelo;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

public class Cliente {

	private String nombre;
	private JPanel panel;
	private static Random random = new Random();

	public Cliente(String nombre, JPanel panel) {
		super();
		this.nombre = nombre;
		this.panel = panel;
	}

	public static void pagar(JPanel panel,int i, ReentrantLock lock, Color color) throws InterruptedException {

		lock.lock();
		try {
			panel.setBackground(color);
			Thread.sleep((int) (Math.random() * 3000) + 1000);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				panel.setBackground(Color.white);
				Thread.sleep((int) (Math.random() * 2000) + 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();

			}

	}

}
}