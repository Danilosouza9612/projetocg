package model;

public enum LineAlgorithm implements Comparable<LineAlgorithm>{
	DDA(1),
	PONTO_MEDIO(2),
	EQUACAO_EXPLICITA(3);

	private int order;
	
	LineAlgorithm(int i) {
		this.order = i;
	}
	
	public int getOrder() {
		return order;
	}
}
