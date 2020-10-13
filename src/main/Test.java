package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import config.AppConfig;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

import service.LineService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Test extends JFrame {

	private JPanel contentPane;
	private JPanel GLPanel;
	private JTextField x0Field;
	private JTextField y0Field;
	private JTextField x1Field;
	private JTextField y1Field;
	private JCheckBox showMeshCheckbox;
	private GLCanvas glCanvas;
	private JTextField meshLengthTextBox;
	private JCheckBox ddaCheckBox;
	private JCheckBox pontoMedioCheckBox;
	private JCheckBox equacaoExplicitaCheckBox;
	private JCheckBox originalLineCheckBox;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 248, 611);
		panel.setMinimumSize(new Dimension(300,611));
		contentPane.add(panel);
		panel.setLayout(null);
		
		x0Field = new JTextField();
		x0Field.setBounds(36, 60, 67, 20);
		panel.add(x0Field);
		x0Field.setColumns(10);
		
		y0Field = new JTextField();
		y0Field.setColumns(10);
		y0Field.setBounds(151, 60, 67, 20);
		panel.add(y0Field);
		
		JLabel lblNewLabel = new JLabel("X0:");
		lblNewLabel.setBounds(10, 63, 29, 14);
		panel.add(lblNewLabel);
		
		JLabel lblY = new JLabel("Y0:");
		lblY.setBounds(110, 63, 29, 14);
		panel.add(lblY);
		
		x1Field = new JTextField();
		x1Field.setColumns(10);
		x1Field.setBounds(36, 132, 67, 20);
		panel.add(x1Field);
		
		y1Field = new JTextField();
		y1Field.setColumns(10);
		y1Field.setBounds(151, 132, 67, 20);
		panel.add(y1Field);
		
		JLabel lblY_2 = new JLabel("Y1:");
		lblY_2.setBounds(112, 135, 27, 14);
		panel.add(lblY_2);
		
		JLabel lblX = new JLabel("X1:");
		lblX.setBounds(10, 135, 29, 14);
		panel.add(lblX);
		
		JLabel lblNewLabel_1 = new JLabel("Ponto Inicial");
		lblNewLabel_1.setBounds(10, 32, 99, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ponto Final");
		lblNewLabel_2.setBounds(10, 107, 90, 14);
		panel.add(lblNewLabel_2);
		
		JButton drawButton = new JButton("Desenhar");
		drawButton.setBounds(53, 326, 129, 23);
		panel.add(drawButton);
		
		showMeshCheckbox = new JCheckBox("Mostrar malha de pixels");
		showMeshCheckbox.setSelected(true);
		showMeshCheckbox.setBounds(10, 356, 211, 23);
		showMeshCheckbox.addActionListener((ActionEvent e)->{
			LineService.getInstance().setShowMess(showMeshCheckbox.isSelected());
			glCanvas.display();
		});

		
		panel.add(showMeshCheckbox);
		
		meshLengthTextBox = new JTextField();
		meshLengthTextBox.setBounds(10, 411, 96, 20);
		panel.add(meshLengthTextBox);
		meshLengthTextBox.setColumns(10);
		meshLengthTextBox.setText(Integer.toString(AppConfig.getInstance().getMessLength()));
		
		JLabel lblNewLabel_3 = new JLabel("Tamanho da Malha");
		lblNewLabel_3.setBounds(10, 386, 113, 14);
		panel.add(lblNewLabel_3);
		
		JButton changeMeshButton = new JButton("Alterar");
		changeMeshButton.addActionListener((ActionEvent e)->{
			AppConfig.getInstance().setMessLength(Integer.parseInt(meshLengthTextBox.getText()));
			rebuildGLCanvas();
		});
		changeMeshButton.setBounds(117, 410, 101, 23);
		panel.add(changeMeshButton);
		
		JLabel lblNewLabel_4 = new JLabel("Algoritmos Escolhidos:");
		lblNewLabel_4.setBounds(10, 215, 208, 14);
		panel.add(lblNewLabel_4);
		
		ddaCheckBox = new JCheckBox("DDA");
		ddaCheckBox.setBounds(10, 240, 206, 23);
		panel.add(ddaCheckBox);
		
		pontoMedioCheckBox = new JCheckBox("Ponto M\u00E9dio");
		pontoMedioCheckBox.setBounds(10, 266, 206, 23);
		panel.add(pontoMedioCheckBox);
		
		equacaoExplicitaCheckBox = new JCheckBox("Equa\u00E7\u00E3o Expl\u00EDcita");
		equacaoExplicitaCheckBox.setBounds(10, 292, 206, 23);
		panel.add(equacaoExplicitaCheckBox);
		
		originalLineCheckBox = new JCheckBox("Mostrar Linha Original");
		originalLineCheckBox.setBounds(10, 174, 174, 23);
		originalLineCheckBox.addActionListener((ActionEvent e)->{
			LineService.getInstance().setOriginalLine(originalLineCheckBox.isSelected());
			glCanvas.display();
		});
		panel.add(originalLineCheckBox);
		drawButton.addActionListener((ActionEvent e)->{
			try {
				LineService.getInstance().draw(
						Integer.parseInt(x0Field.getText()), 
						Integer.parseInt(y0Field.getText()),
						Integer.parseInt(x1Field.getText()), 
						Integer.parseInt(y1Field.getText()),
						ddaCheckBox.isSelected(),
						pontoMedioCheckBox.isSelected(),
						equacaoExplicitaCheckBox.isSelected()
				);
				glCanvas.repaint();
			}catch(IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(null, exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		GLPanel = new JPanel();
		GLPanel.setBounds(252, 5, 761, 611);
		GLPanel.setBackground(Color.GRAY);
		contentPane.add(GLPanel);
		
		this.glCanvas = this.buildGLCanvas();
		GLPanel.add(glCanvas);
	}
	
	public final GLCanvas buildGLCanvas() {
		GLProfile.initSingleton();
	    GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	    
	    GLCanvas glCanvas = new GLCanvas(capabilities);
	    Renderer renderer = new Renderer();
	    glCanvas.addGLEventListener(renderer);
	    glCanvas.setSize(AppConfig.getInstance().getViewPortWidth(), AppConfig.getInstance().getViewPortHeight()); //Quando forem mexer no design, comente essa linha pois o Design não pega com o GLCanvas com size definido
	    
	    return glCanvas;
	}
	
	public void rebuildGLCanvas() {
		GLPanel.remove(glCanvas);
		glCanvas.destroy();
		glCanvas = this.buildGLCanvas();
		GLPanel.add(glCanvas);
	}
}
