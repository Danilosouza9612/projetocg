package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import model.TransformCallBackFactory;
import pattern.observer.Observer;
import renderer.Renderer;
import service.AppService;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GLCanvas glCanvas;
	private JTextField transXField;
	private JTextField transYField;
	private JTextField transZField;
	
	public Window() {
		setResizable(false);
		setTitle("Rasterizador de Reta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(900, 600));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 400));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JButton applyTranslate = new JButton("Aplicar");
		applyTranslate.setBounds(187, 46, 89, 23);
		applyTranslate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float x = Float.parseFloat((String)transXField.getText());
				float y = Float.parseFloat((String)transYField.getText());
				float z = Float.parseFloat((String)transZField.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createTranslate(x, y, z));
			}
		});
		panel.add(applyTranslate);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setBounds(12, 32, 48, 14);
		panel.add(lblNewLabel);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(72, 32, 48, 14);
		panel.add(lblY);
		
		JLabel lblZ = new JLabel("Z");
		lblZ.setBounds(130, 32, 48, 14);
		panel.add(lblZ);
		
		JLabel lblNewLabel_1 = new JLabel("Transla\u00E7\u00E3o");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 11, 110, 14);
		panel.add(lblNewLabel_1);
		
		transXField = new JTextField();
		transXField.setBounds(12, 47, 48, 20);
		panel.add(transXField);
		transXField.setColumns(10);
		
		transYField = new JTextField();
		transYField.setColumns(10);
		transYField.setBounds(72, 47, 48, 20);
		panel.add(transYField);
		
		transZField = new JTextField();
		transZField.setColumns(10);
		transZField.setBounds(130, 47, 48, 20);
		panel.add(transZField);
		
		JPanel panel_1 = new JPanel();
		this.glCanvas = this.buildGLCanvas();
		panel_1.add(this.glCanvas); //Quando forem pro Design, comentem essa linha
		contentPane.add(panel_1, BorderLayout.CENTER);
		AppService.getInstance().setObserver(this);
	}
	
	public final GLCanvas buildGLCanvas() {
		GLProfile.initSingleton();
	    GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	    
	    GLCanvas glCanvas = new GLCanvas(capabilities);
	    Renderer renderer = new Renderer();
	    glCanvas.addMouseListener(new GLCanvasMouseEvent());
	    glCanvas.addMouseWheelListener(new MouseWheelEvent());
	    glCanvas.addGLEventListener(renderer);
	    glCanvas.setSize(new Dimension(600,600));
	    
	    return glCanvas;
	}

	@Override
	public void atualizar() {
		this.glCanvas.display();
	}
}
