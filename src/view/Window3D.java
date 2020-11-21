package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import renderer.Renderer;

public class Window3D extends JFrame{
	private JPanel contentPane;
	
	public Window3D() {
		setResizable(false);
		setTitle("Rasterizador de Reta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(850, 600));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(this.buildGLCanvas());
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
	}
}
