package main;

public class MainAnalise {
	public static void main(String[] args) {
		Integer[] vetor = {1, 3, 7, 11, 14, 17};
		removerElemento(vetor, 6, 3);
		for(int i=0; i<vetor.length; i++) {
			System.out.println(vetor[i] + " ");
		}
	}
	public static void removerElemento(Integer[] vetor, int n, int k) {
		boolean removido=false;
		for(int i=0; i<n; i++) {
			if(vetor[i]==k) {
				removido=true;
				n--;
			}
			if(removido && i<n) {
				vetor[i]=vetor[i+1];
			}
		}
		if(removido) {
			vetor[n]=null;
		}
	}
}
