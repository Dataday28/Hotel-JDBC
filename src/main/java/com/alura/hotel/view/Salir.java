package com.alura.hotel.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Salir extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	
	public Salir() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Salir.class.getResource("/com/alura/hotel/images/aH-40px.png")));
		setBounds(100, 100, 490, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Salir.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
			lblNewLabel.setBounds(191, 12, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Esta seguro que desea salir de la aplicacion?");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(35, 122, 414, 21);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton exitButton = new JButton("Salir");
				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();//sirve para cerrar la ventana actual
						System.exit(0);
					}
				});
				exitButton.setActionCommand("Salir");
				buttonPane.add(exitButton);
				getRootPane().setDefaultButton(exitButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();//sirve para cerrar la ventana actual
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
