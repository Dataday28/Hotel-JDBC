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
public class Exito extends JDialog {

	private JPanel contentPanel, buttonPane;
	private JLabel lblNewLabel, lblNewLabel_1;
	private JButton okButton;


	public Exito() {
		
		iniciarComponentes();

		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
	}
	
	private void iniciarComponentes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/com/alura/hotel/images/aH-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblNewLabel.setBounds(145, 10, 100, 100);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
		lblNewLabel_1.setForeground(new Color (12, 138, 199));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(27, 122, 322, 21);
		contentPanel.add(lblNewLabel_1);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//sirve para cerrar la ventana actual
				MenuUsuario usuario = new MenuUsuario(); 
				usuario.setVisible(true);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

			
	}
		
}

