package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import model.AppState;
import pattern.observer.Observer;
import renderer.DrawEventQueue;
import renderer.Renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class Window extends JFrame implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel contentPane;
	private JPanel GLPanel;
	private GLCanvas glCanvas;

	public Window() {
		AppState appState = AppState.getInstance();
		appState.setObserver(this);
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
		this.buildGLanvas();
		GLPanel.setLayout(new BorderLayout(0, 0));
		
		//this.glCanvas = this.buildGLCanvas();
		//GLPanel.add(glCanvas);
	}
	
	public void buildPanel() {
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setPreferredSize(new Dimension(250, contentPane.getPreferredSize().height));
		panel.setLayout(null);
		
		JList transformationList = new JList();
		transformationList.setModel(new AbstractListModel() {
			String[] values = new String[] {"fff", "sss", "aaa"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		transformationList.setBounds(10, 577, 230, -131);
		panel.add(transformationList);
		
		JLabel lblNewLabel = new JLabel("Escala");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel.add(lblNewLabel);
		
		JSpinner scaleX = new JSpinner();
		scaleX.setBounds(10, 64, 63, 20);
		panel.add(scaleX);
		
		JSpinner scaleY = new JSpinner();
		scaleY.setBounds(82, 64, 60, 20);
		panel.add(scaleY);
		
		JSpinner scaleZ = new JSpinner();
		scaleZ.setBounds(152, 64, 58, 20);
		panel.add(scaleZ);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(10, 39, 19, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Y");
		lblNewLabel_1_1.setBounds(82, 39, 19, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Z");
		lblNewLabel_1_1_1.setBounds(152, 39, 19, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Adicionar Escala");
		btnNewButton.setBounds(10, 95, 200, 23);
		panel.add(btnNewButton);
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
