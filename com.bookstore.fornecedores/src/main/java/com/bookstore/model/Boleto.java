package com.bookstore.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author NPG (nome dado a equipe que esta desenvolvendo esse sistema)
 * Essa classe faz parte da implementação do padrão de projeto Strategy, no qual ela representa
 * uma classe concreta Strategy, ou seja, que herda da super classe FormaPagamento (Interface Strategy)
 *
 */
@Entity
@DiscriminatorValue("Boleto")
public class Boleto extends FormaPagamento{

	public Boleto() {
		setNome("Boleto"); //adicionando ao atributo nome de FormaPagamento, o seu respectivo nome
	}
	
	/**
	 * Método da superclasse, sobreescrito na subclasse.
	 */
	@Override
	public void realizarPagamento(BigDecimal valor) {
		System.out.println("pagamento por Boleto");
	}
	
}
