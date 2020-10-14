package algorithms;

import com.jogamp.opengl.GL2;

public class PontoMedio extends OctantLineAlgorithm{	
	public PontoMedio(GL2 gl) {
		super(gl);
	}

	public float[] defineColor() {
		float[] color = { 0.0f,0.0f,0.0f };
		return color;
	}
	
	private void pontoMedioAsc(int x1, int y1, int x2, int y2) {
		int x, y, d, dx, dy;
		int incrNE, incrE;
		
		dx = x2-x1;
		dy = y2-y1;
		
		d = 2*dy - dx;
		incrE = 2*dy;
		incrNE = 2*(dy - dx);
		x=x1;
		y=y1;
		
		while(x<=x2) {
			this.drawCircle(x, y);
			if(d<0) {
				d = d + incrE;
				x++;
			}else {
				d = d + incrNE;
				x++;
				y++;
			}
		}

	}
	
	private void pontoMedioAscFunctionX(int x1, int y1, int x2, int y2) {
		int x, y, d, dx, dy;
		int incrNE, incrE;
		
		dx = x2-x1;
		dy = y2-y1;
		
		d = 2*dy - dx;
		incrE = 2*dy;
		incrNE = 2*(dy - dx);
		x=x1;
		y=y1;
		
		while(x<=x2) {
			this.drawCircle(y, x);
			if(d<0) {
				d = d + incrE;
				x++;
			}else {
				d = d + incrNE;
				x++;
				y++;
			}
		}
	}
	
	private void pontoMedioDescFunctionX(int x1, int y1, int x2, int y2) {
		int x, y, d, dx, dy;
		int incrNE, incrE;
		
		dx = x2-x1;
		dy = y2-y1;
		
		d = 2*dy+dx;
		incrNE = 2*dy;
		incrE = 2*(dy + dx);
		
		x=x1;
		y=y1;
		
		while(x<=x2) {
			this.drawCircle(y, x);
			if(d>=0) {
				d = d + incrNE;
				x++;
			}else {
				d = d + incrE;
				x++;
				y--;
			}
		}
	}
	
	private void pontoMedioDesc(int x1, int y1, int x2, int y2) {
		int x, y, d, dx, dy;
		int incrNE, incrE;
		
		dx = x2-x1;
		dy = y2-y1;
		
		d = 2*dy+dx;
		incrNE = 2*dy;
		incrE = 2*(dy + dx);
		
		x=x1;
		y=y1;
		
		while(x<=x2) {
			this.drawCircle(x, y);
			if(d>=0) {
				d = d + incrNE;
				x++;
			}else {
				d = d + incrE;
				x++;
				y--;
			}
		}
	}

	@Override
	public void drawFirstOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioAsc(x1, y1, x2, y2);
	}

	@Override
	public void drawSecondOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioAscFunctionX(y1, x1, y2, x2);
	}

	@Override
	public void drawThirdOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioDescFunctionX(y2, x2, y1, x1);
	}

	@Override
	public void drawFourthOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioDesc(x1, y1, x2, y2);
	}

	@Override
	public void drawFifthOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioAsc(x2, y2, x1, y1);
	}

	@Override
	public void drawSixthOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioAscFunctionX(y2, x2, y1, x1);
	}

	@Override
	public void drawSeventhOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioDescFunctionX(y1, x1, y2, x2);
	}

	@Override
	public void drawEighthOctant(int x1, int y1, int x2, int y2) {
		this.pontoMedioDesc(x2, y2, x1, y1);
	}
}