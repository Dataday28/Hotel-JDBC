package com.alura.hotel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.hotel.controller.ClientesController;
import com.alura.hotel.controller.ReservaController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane, header, btnAtras, btnexit, btnbuscar, btnEditar, btnEliminar;
	private JTextField txtBuscar;
	private JTable tbHuespedes, tbReservas;
	private DefaultTableModel modelo, modeloHuesped;
	private JLabel labelAtras, labelExit, lblNewLabel_4,lblNewLabel_2, lblBuscar, lblEditar, lblEliminar;
	private JTabbedPane panel;
	private JScrollPane scroll_table, scroll_tableHuespedes;
	private JSeparator separator_1_2;
	int xMouse, yMouse;
	private ReservaController reservaContoller;
	private ClientesController clientesController;
	
	
	public Busqueda() {
		
		this.reservaContoller = new ReservaController();
		this.clientesController = new ClientesController();
		
		iniciarComponentes();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		

	}
	
	private void iniciarComponentes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/com/alura/hotel/images/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(282, 64, 295, 42);
		contentPane.add(lblNewLabel_4);
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("Habitacion");
		
		cargarTablaReserva();
		
		scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/images/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		
		cargarTablaHuesped();
		
		scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/images/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/alura/hotel/images/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		header = new JPanel();
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
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Salir exit = new Salir();
				exit.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				limpiarTabla();
				
				if(txtBuscar.getText().equals("")) {
					cargarTablaReserva();
					cargarTablaHuesped();
				}else {
					buscar();
				}
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					
					updateReserva();
					limpiarTabla();
					cargarTablaReserva();
					cargarTablaHuesped();
					
				}
				else if(filaHuespedes >= 0) {
					
					updateCliente();
					limpiarTabla();
					cargarTablaHuesped();
					cargarTablaReserva();
					
				}else {
					JOptionPane.showMessageDialog(null, "Error fila no selecionada");
				}
				
				
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int filaReservas = tbReservas.getSelectedRow();
				int filaHuespedes = tbHuespedes.getSelectedRow();
				
				if(filaReservas >= 0) {
					
					int confirmar = JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar los datos?");
					
					if(confirmar == JOptionPane.YES_OPTION) {
						
						deleteReserva();
						limpiarTabla();
						cargarTablaReserva();
						cargarTablaHuesped();
						
					}
					
				}
				else if(filaHuespedes >= 0) {
					
					int confirmarh = JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar los datos?");
					
					if(confirmarh == JOptionPane.YES_OPTION) {
					
						deleteCliente();
						limpiarTabla();
						cargarTablaHuesped();
						cargarTablaReserva();
						
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Error fila no selecionada");
				}
				
				
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
	}
	

	
	private void limpiarTabla() {
		
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
	}
	
	private void cargarTablaReserva() {
		
		try {
			
			var reservas = this.reservaContoller.listar();
			
				
				reservas.forEach(reserva -> modelo.addRow(new Object[] {reserva.getIdHxComplete(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getTipoPago(), reserva.getTipoHabitacion()}));
				
		}catch(Exception e) {
			throw e;
		}
			
		
	}
	
	private void cargarTablaHuesped() {
		
		try {
			
			var clientes = this.clientesController.listar();
			
				
				clientes.forEach(cliente -> modeloHuesped.addRow(new Object[] {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getFechaNacimiento(), cliente.getPais(), cliente.getTelefono(), cliente.getIdHxComplete()}));
				
		}catch(Exception e) {
			throw e;
		}
			
	}
	
	private void buscar() {
		
		try {
			
			var ids = this.reservaContoller.buscar(txtBuscar.getText(), txtBuscar.getText());
			
			try {
				
				modelo.setRowCount(0);
				modeloHuesped.setRowCount(0);
				
				ids.forEach(id -> {
					modelo.addRow(new Object[] {id.getIdHxComplete(), id.getFechaEntrada(), id.getFechaSalida(), id.getValor(), id.getTipoPago(), id.getTipoHabitacion()});
						
					var clientes = id.getClientes();
					
					clientes.forEach(cliente -> modeloHuesped.addRow(new Object[] {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getFechaNacimiento(), cliente.getPais(), cliente.getTelefono(), cliente.getIdHxComplete()}));
						
				});
				
	
			}catch(Exception e) {
				throw e;
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "No se encrontraron Resultados");
		}
		
	}
	
	private void updateReserva() {
		
			
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
			.ifPresentOrElse(filaReservas -> {
				
				String idR = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
				LocalDate fechaEntrada = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
				LocalDate fechaSalida = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
				Double valor = Double.parseDouble(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
				String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
				String tipoHabitacion = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 5);
				
				var filasModificadas = this.reservaContoller.update(idR, fechaEntrada, fechaSalida, valor, formaPago, tipoHabitacion);
				
				JOptionPane.showMessageDialog(this, String.format("%d Reserva actualizado con exito", filasModificadas));
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, Seleccione un valor"));
			
			
		
	}
	
	private void updateCliente() {
		
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(folaHuesped -> {
						
						Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
						LocalDate fechaNacimento = LocalDate.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
						String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
						String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
						String idReserva = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);
						
						var filasModificadaClientes = this.clientesController.update(id, nombre, apellido, fechaNacimento, nacionalidad, telefono, idReserva);
						
						JOptionPane.showMessageDialog(this, String.format("%d Cliente actualizado con exito", filasModificadaClientes));
						
					}, () -> JOptionPane.showMessageDialog(this, "Por favor Selecione un cliente"));

	}
	
	private void deleteReserva() {
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(filaReserva -> {
					
					String id = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
					
					var filaEliminar = this.reservaContoller.delete(id);
					
					modelo.removeRow(tbReservas.getSelectedRow());
					
					JOptionPane.showMessageDialog(this, String.format("Eliminado con exito", filaEliminar));
					
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un valor"));
		
	}
	
	private void deleteCliente() {
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(filaCliente -> {
			
			String idReserva = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);
			
			var filaEliminarC = this.clientesController.delete(idReserva);
			
			JOptionPane.showMessageDialog(this, String.format("Eliminado con exito", filaEliminarC));
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor Selecione un cliente"));
		
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
