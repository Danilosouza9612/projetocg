package model;

public enum LineAlgorithmEnum implements Comparable<LineAlgorithmEnum>{
	DDA(1),
	PONTO_MEDIO(2),
	EQUACAO_EXPLICITA(3);

	private int order;
	
	LineAlgorithmEnum(int i) {
		this.order = i;
	}
	
	public int getOrder() {
		return order;
	}
}
