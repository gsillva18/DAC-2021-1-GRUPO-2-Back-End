package com.bookstore.model;
/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe corresponde a tipificação das formas de pagamento
 *
 */
public enum TipoFormaPagamento {

	CARTAO("Cartão"),
	BOLETO("Boleto"),
	PIX("Pix"),
	TRANFERENCIA_BANCARIA("Transferência Bancária");

	private final String tipo;
	
	TipoFormaPagamento(String tipo) {
		
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
//	public String toString() {
//		return tipo;
//	}
	

	
	
}
