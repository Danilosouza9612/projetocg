package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import model.TransformCallBackFactory;
import pattern.observer.Observer;
import renderer.Renderer;
import service.AppService;
import javax.swing.JButton;
import javax.swing.JTextField;
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
	private JTextField rotationField;
	private JTextField scaleXField;
	private JTextField scaleYField;
	private JTextField scaleZField;
	private JTextField shearFieldA;
	private JTextField shearFieldB;
	
	public Window() {
		setResizable(false);
		setTitle("Paint 3D");
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
				
				float x = transXField.getText().isEmpty() ? 0 : Float.parseFloat((String)transXField.getText());
				float y = transYField.getText().isEmpty() ? 0 : Float.parseFloat((String)transYField.getText());
				float z = transZField.getText().isEmpty() ? 0 : Float.parseFloat((String)transZField.getText());
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
		transXField.setText("0");
		transXField.setBounds(12, 47, 48, 20);
		panel.add(transXField);
		transXField.setColumns(10);
		
		transYField = new JTextField();
		transYField.setText("0");
		transYField.setColumns(10);
		transYField.setBounds(72, 47, 48, 20);
		panel.add(transYField);
		
		transZField = new JTextField();
		transZField.setText("0");
		transZField.setColumns(10);
		transZField.setBounds(130, 47, 48, 20);
		panel.add(transZField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Aplicar rota\u00E7\u00E3o de");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(12, 93, 108, 14);
		panel.add(lblNewLabel_1_1);
		
		rotationField = new JTextField();
		rotationField.setText("0");
		rotationField.setColumns(10);
		rotationField.setBounds(130, 90, 48, 20);
		panel.add(rotationField);
		
		JButton applyRotation_X = new JButton("Em X");
		applyRotation_X.setBounds(12, 118, 69, 23);
		
		applyRotation_X.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float x = rotationField.getText().isEmpty() ? 0 : Float.parseFloat((String)rotationField.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createRotateX(x));
			}
		});
		
		panel.add(applyRotation_X);
		
		JButton applyRotation_Y = new JButton("Em Y");
		applyRotation_Y.setBounds(109, 118, 69, 23);
		applyRotation_Y.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float y = rotationField.getText().isEmpty() ? 0 : Float.parseFloat((String)rotationField.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createRotateY(y));
			}
		});
		panel.add(applyRotation_Y);
		
		JButton applyRotation_Z = new JButton("Em Z");
		applyRotation_Z.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				float z = rotationField.getText().isEmpty() ? 0 : Float.parseFloat((String)rotationField.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createRotateZ(z));
			}
		});
		applyRotation_Z.setBounds(207, 118, 69, 23);
		panel.add(applyRotation_Z);
		
		JLabel lblNewLabel_1_2 = new JLabel("Escala");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(12, 152, 110, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.setBounds(14, 173, 48, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblY_1 = new JLabel("Y");
		lblY_1.setBounds(74, 173, 48, 14);
		panel.add(lblY_1);
		
		JLabel lblZ_1 = new JLabel("Z");
		lblZ_1.setBounds(132, 173, 48, 14);
		panel.add(lblZ_1);
		
		scaleXField = new JTextField();
		scaleXField.setText("0");
		scaleXField.setColumns(10);
		scaleXField.setBounds(14, 188, 48, 20);
		panel.add(scaleXField);
		
		scaleYField = new JTextField();
		scaleYField.setText("0");
		scaleYField.setColumns(10);
		scaleYField.setBounds(74, 188, 48, 20);
		panel.add(scaleYField);
		
		scaleZField = new JTextField();
		scaleZField.setText("0");
		scaleZField.setColumns(10);
		scaleZField.setBounds(132, 188, 48, 20);
		panel.add(scaleZField);
		
		JButton applyScale = new JButton("Aplicar");
		applyScale.setBounds(189, 187, 89, 23);
		applyScale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float x = scaleXField.getText().isEmpty() || scaleXField.getText().equals("0") ? 1 : Float.parseFloat((String)scaleXField.getText());
				float y = scaleYField.getText().isEmpty() || scaleYField.getText().equals("0") ? 1 : Float.parseFloat((String)scaleYField.getText());
				float z = scaleZField.getText().isEmpty() || scaleZField.getText().equals("0") ? 1 : Float.parseFloat((String)scaleZField.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createScale(x, y, z));
			}
		});
		panel.add(applyScale);
		
		JLabel Cisalhamento = new JLabel("Cisalhamento");
		Cisalhamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		Cisalhamento.setBounds(10, 219, 89, 14);
		panel.add(Cisalhamento);
		
		JButton applyShear_Z = new JButton("Em Z");
		applyShear_Z.setBounds(207, 284, 69, 23);
		applyShear_Z.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float a = shearFieldA.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldA.getText());
				float b = shearFieldB.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldB.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createShearZ(a,b));
			}
		});
		panel.add(applyShear_Z);
		
		JButton applyShear_Y = new JButton("Em Y");
		applyShear_Y.setBounds(109, 284, 69, 23);
		applyShear_Y.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float a = shearFieldA.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldA.getText());
				float b = shearFieldB.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldB.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createShearY(a,b));
			}
		});
		panel.add(applyShear_Y);
		
		JButton applyShear_X = new JButton("Em X");
		applyShear_X.setBounds(12, 284, 69, 23);
		applyShear_X.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				float a = shearFieldA.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldA.getText());
				float b = shearFieldB.getText().isEmpty() ? 0 : Float.parseFloat((String)shearFieldB.getText());
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createShearX(a,b));
			}
		});
		panel.add(applyShear_X);
		
		shearFieldA = new JTextField();
		shearFieldA.setText("0");
		shearFieldA.setBounds(12, 253, 48, 20);
		panel.add(shearFieldA);
		shearFieldA.setColumns(10);
		
		shearFieldB = new JTextField();
		shearFieldB.setText("0");
		shearFieldB.setBounds(74, 253, 46, 20);
		panel.add(shearFieldB);
		shearFieldB.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("A");
		lblNewLabel_3.setBounds(12, 240, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("B");
		lblNewLabel_4.setBounds(74, 240, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel ReflexaoLabel = new JLabel("Reflex\u00E3o");
		ReflexaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		ReflexaoLabel.setBounds(12, 318, 89, 14);
		panel.add(ReflexaoLabel);
		
		JButton yzReflexionButton = new JButton("Em YZ");
		yzReflexionButton.setBounds(109, 343, 88, 23);
		yzReflexionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createReflexionYZ());
			}
			
		});
		panel.add(yzReflexionButton);
		
		JButton xzReflexionButton = new JButton("Em XZ");
		xzReflexionButton.setBounds(12, 343, 87, 23);
		xzReflexionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createReflexionXZ());
			}
			
		});
		panel.add(xzReflexionButton);
		
		JButton xyReflexionButton = new JButton("Em XY");
		xyReflexionButton.setBounds(207, 343, 83, 23);
		xyReflexionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransformCallBackFactory factory = new TransformCallBackFactory();
				AppService appService = AppService.getInstance();
				appService.addTransformation(factory.createReflexionXY());
			}
			
		});
		panel.add(xyReflexionButton);
		
		
		
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
