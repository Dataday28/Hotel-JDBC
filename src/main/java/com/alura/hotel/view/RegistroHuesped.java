package com.alura.hotel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;
import java.awt.Color;

import com.alura.hotel.controller.ClientesController;
import com.alura.hotel.controller.ReservaController;
import com.alura.hotel.model.Clientes;
import com.alura.hotel.model.Reserva;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane, header, btnAtras, btnguardar, panel, btnexit, btncancelar;
	private JTextField txtNombre, txtApellido, txtNreserva, txtTelefono;
	private JDateChooser txtFechaN;
	private JComboBox<String> txtNacionalidad;
	private JLabel labelExit, labelAtras, lblNombre, lblApellido, lblFechaN, lblNacionalidad, lblTelefono, lblTitulo, lblNumeroReserva, labelGuardar, imagenFondo, logo, lblCancelar;
	private JSeparator separator_1_2, separator_1_2_1, separator_1_2_2, separator_1_2_3, separator_1_2_4, separator_1_2_5;
	private String nacionalidad[] = new String[] {"Afganistán","Alemania","Arabia","Argentina","Australia","Bélgica","Bolivia","Brasil","Camboya","Canadá","Chile","China","Colombia","Corea","Costa Rica","Cuba","Dinamarca","Ecuador","Egipto","El Salvador","Escocia","España","Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Francia","Gales","Grecia","Guatemala","Haití","Holanda","Honduras","Indonesia","Inglaterra","Iraq","Irán","Irlanda","Israel","Italia","Japón","Jordania","Laos","Letonia","Lituania","Malasia","Marruecos","México","Nicaragua","Noruega","Nueva Zelanda","Panamá","Paraguay","Perú","Polonia","Portugal","Puerto Rico","República Dominicana","Rumania","Rusia","Suecia","Suiza","Tailandia","Taiwán","Turquía","Ucrania","Uruguay","Venezuela","Vietnam"};
	private Document doc;
	private int maxLength = 10;
	int xMouse, yMouse;
	private Reserva reserva;
	private ClientesController clientesController;
	private ReservaController reservaController;

	
	public RegistroHuesped(Reserva reserva) {
		
		this.reserva = reserva;
		this.clientesController = new ClientesController();
		this.reservaController = new ReservaController();

		iniciarComponentes();
		
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
		

	}
	
	private void iniciarComponentes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/com/alura/hotel/images/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservasView = new ReservasView();
				reservasView.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(new Color(12, 138, 199));
			     labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);
		
		lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);
		
		lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);
		
		lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);
		
		lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);
		
		lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setBounds(591, 56, 254, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);
		
		lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);
		
		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 285, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/com/alura/hotel/images/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);
		
		txtNacionalidad = new JComboBox<String>();
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel<String>(nacionalidad));
		contentPane.add(txtNacionalidad);
		
		doc = new PlainDocument() {
			@Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                if (str == null) {
                    return;
                }

                String currentText = getText(0, getLength());
                String newText = currentText.substring(0, offset) + str + currentText.substring(offset);
                
                if (newText.matches("\\d*") && newText.length() <= maxLength) {
                    super.insertString(offset, str, a);
                }
            }
        };
		
		txtTelefono = new JTextField(10);
		txtTelefono.setDocument(doc);
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		
		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setEditable(false);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNreserva.setText(reserva.getIdHx());
		contentPane.add(txtNreserva);
		
		separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);
		
		separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);
		
		separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);
		
		separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);
		
		separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);
		
		separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);
		
		btncancelar = new JPanel();
		btncancelar.setLayout(null);
		btncancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setVisible(true);
				dispose();
			}
		});
		btncancelar.setBackground(new Color(12, 138, 199));
		btncancelar.setBounds(562, 560, 122, 35);
		contentPane.add(btncancelar);
		btncancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		lblCancelar = new JLabel("CANCELAR");
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setForeground(Color.WHITE);
		lblCancelar.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblCancelar.setBounds(0, 0, 122, 35);
		btncancelar.add(lblCancelar);
		
		btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				guardar();
				Exito exito = new Exito();
				exito.setVisible(true);
				dispose();
			}
			
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);
		
		imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/com/alura/hotel/images/registro.png")));
		
		logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		
		btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Salir exit = new Salir();
				exit.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		
	}
	
	private void guardar() {
		try {
			var clientes = new Clientes(txtNombre.getText(), txtApellido.getText(), convertJDateChooserToLocalDate(txtFechaN.getDate()), txtNacionalidad.getSelectedItem().toString(), txtTelefono.getText());
			this.reservaController.save(reserva);
			this.clientesController.save(clientes, reserva.getId());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	public static LocalDate convertJDateChooserToLocalDate(java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
	
	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
