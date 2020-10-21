package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import config.AppConfig;
import model.LineAlgorithmEnum;
import pattern.observer.Observer;
import renderer.DrawEventQueue;
import renderer.Renderer;

import javax.swing.JButton;
import java.awt.event.ActionEvent;

import service.AppService;
import service.LineService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JSpinner;

public class Window extends JFrame implements Observer{
	private JPanel panel;
	private JPanel contentPane;
	private JPanel GLPanel;
	private JCheckBox showMeshCheckbox;
	private GLCanvas glCanvas;
	private JTextField meshLengthTextBox;
	private JCheckBox ddaCheckBox;
	private JCheckBox pontoMedioCheckBox;
	private JCheckBox equacaoExplicitaCheckBox;
	private JCheckBox originalLineCheckBox;
	private JSpinner x0Field;
	private JSpinner x1Field;
	private JSpinner y0Field;
	private JSpinner y1Field;
	private JButton changeMeshButton;

	public Window() {
		setResizable(false);
		setTitle("Rasterizador de Reta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawEventQueue.getInstance().setObserver(this);
		//setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(850, 600));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		this.pack();
		
		this.buildPanel();
		this.buildLabels();
		this.buildSpinners();
		this.buildMeshCheckBox();
		this.buildMeshLengthCheckBox();
		this.buildAlgorithmsCheckBoxes();
		this.buildOriginalLineCheckBox();
		this.buildChangeMeshButton();
		this.buildGLanvas();
		GLPanel.setLayout(new BorderLayout(0, 0));
		
		this.glCanvas = this.buildGLCanvas();
		GLPanel.add(glCanvas);
	}
	
	public void buildPanel() {
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setPreferredSize(new Dimension(250, contentPane.getPreferredSize().height));
		panel.setLayout(null);
	}
	
	public void buildLabels() {
		JLabel lblNewLabel = new JLabel("X0:");
		lblNewLabel.setBounds(10, 63, 29, 14);
		panel.add(lblNewLabel);
		
		JLabel lblY = new JLabel("Y0:");
		lblY.setBounds(112, 63, 29, 14);
		panel.add(lblY);
		
		JLabel lblY_2 = new JLabel("Y1:");
		lblY_2.setBounds(114, 135, 27, 14);
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
		
		JLabel lblNewLabel_3 = new JLabel("Tamanho da Malha");
		lblNewLabel_3.setBounds(10, 386, 113, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Algoritmos Escolhidos:");
		lblNewLabel_4.setBounds(10, 215, 208, 14);
		panel.add(lblNewLabel_4);
	}
	
	public void buildSpinners() {
		x0Field = new JSpinner();
		x0Field.setBounds(36, 60, 67, 20);
		x0Field.addChangeListener((ChangeEvent e)->{
			LineService.getInstance().setX1(Integer.parseInt(x0Field.getValue().toString()));
		});
		panel.add(x0Field);
		
		y0Field = new JSpinner();
		y0Field.setBounds(149, 60, 69, 20);
		y0Field.addChangeListener((ChangeEvent e)->{
			LineService.getInstance().setY1(Integer.parseInt(y0Field.getValue().toString()));
		});
		panel.add(y0Field);
		
		x1Field = new JSpinner();
		x1Field.setBounds(36, 132, 66, 20);
		x1Field.addChangeListener((ChangeEvent e)->{
			LineService.getInstance().setX2(Integer.parseInt(x1Field.getValue().toString()));
		});
		panel.add(x1Field);
		
		y1Field = new JSpinner();
		y1Field.setBounds(149, 132, 69, 20);
		y1Field.addChangeListener((ChangeEvent e)->{
			LineService.getInstance().setY2(Integer.parseInt(y1Field.getValue().toString()));
		});
		panel.add(y1Field);
	}
	
	public void buildMeshCheckBox() {
		showMeshCheckbox = new JCheckBox("Mostrar malha de pixels");
		showMeshCheckbox.setSelected(true);
		showMeshCheckbox.setBounds(10, 356, 211, 23);
		showMeshCheckbox.addActionListener((ActionEvent e)->{
			AppService.getInstance().setShowMess(showMeshCheckbox.isSelected());
		});
		panel.add(showMeshCheckbox);
	}
	
	public void buildMeshLengthCheckBox() {
		meshLengthTextBox = new JTextField();
		meshLengthTextBox.setBounds(10, 411, 96, 20);
		panel.add(meshLengthTextBox);
		meshLengthTextBox.setColumns(10);
		meshLengthTextBox.setText(Integer.toString(AppConfig.getInstance().getMessLength()));
	}
	
	public void buildChangeMeshButton() {
		changeMeshButton = new JButton("Alterar");
		changeMeshButton.addActionListener((ActionEvent e)->{
			try {
				AppService.getInstance().setMessLength(Integer.parseInt(meshLengthTextBox.getText()));
				rebuildGLCanvas();
			}catch(java.lang.NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
			}catch(IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		changeMeshButton.setBounds(117, 410, 101, 23);
		panel.add(changeMeshButton);
	}
	
	public void buildAlgorithmsCheckBoxes() {
		ddaCheckBox = new JCheckBox("DDA");
		ddaCheckBox.setBounds(10, 240, 206, 23);
		ddaCheckBox.addActionListener((ActionEvent e)->{
			if(ddaCheckBox.isSelected()) {
				LineService.getInstance().addLineAlgorithm(LineAlgorithmEnum.DDA);
			}else {
				LineService.getInstance().removeLineAlgorithm(LineAlgorithmEnum.DDA);
			}
		});
		panel.add(ddaCheckBox);
		
		pontoMedioCheckBox = new JCheckBox("Ponto M\u00E9dio");
		pontoMedioCheckBox.setBounds(10, 266, 206, 23);
		pontoMedioCheckBox.addActionListener((ActionEvent e)->{
			if(pontoMedioCheckBox.isSelected()) {
				LineService.getInstance().addLineAlgorithm(LineAlgorithmEnum.PONTO_MEDIO);
			}else {
				LineService.getInstance().removeLineAlgorithm(LineAlgorithmEnum.PONTO_MEDIO);
			}
		});
		panel.add(pontoMedioCheckBox);
		
		equacaoExplicitaCheckBox = new JCheckBox("Equa\u00E7\u00E3o Expl\u00EDcita");
		equacaoExplicitaCheckBox.setBounds(10, 292, 206, 23);
		equacaoExplicitaCheckBox.addActionListener((ActionEvent e)->{
			if(equacaoExplicitaCheckBox.isSelected()) {
				LineService.getInstance().addLineAlgorithm(LineAlgorithmEnum.EQUACAO_EXPLICITA);
			}else {
				LineService.getInstance().removeLineAlgorithm(LineAlgorithmEnum.EQUACAO_EXPLICITA);
			}
		});
		panel.add(equacaoExplicitaCheckBox);
	}
	
	public void buildOriginalLineCheckBox() {
		originalLineCheckBox = new JCheckBox("Mostrar Linha Original");
		originalLineCheckBox.setBounds(10, 174, 174, 23);
		originalLineCheckBox.addActionListener((ActionEvent e)->{
			AppService.getInstance().setOriginalLine(originalLineCheckBox.isSelected());
		});
		panel.add(originalLineCheckBox);
	}
	
	public void buildGLanvas() {
		GLPanel = new JPanel();
		GLPanel.setBackground(Color.GRAY);
		contentPane.add(GLPanel, BorderLayout.CENTER);
	}
	
	public final GLCanvas buildGLCanvas() {
		GLProfile.initSingleton();
	    GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	    
	    GLCanvas glCanvas = new GLCanvas(capabilities);
	    Renderer renderer = new Renderer();
	    glCanvas.addGLEventListener(renderer);
	    glCanvas.setSize(new Dimension(600,600)); //Quando forem mexer no design, comente essa linha pois o Design não pega com o GLCanvas com size definido
	    
	    return glCanvas;
	    //return null;
	}
	
	public void rebuildGLCanvas() {
		GLPanel.remove(glCanvas);
		glCanvas.destroy();
		glCanvas = this.buildGLCanvas();
		GLPanel.add(glCanvas);
	}

	@Override
	public void atualizar() {
		glCanvas.display();
	}
}
