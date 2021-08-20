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
@DiscriminatorValue("Pix")
public class Pix extends FormaPagamento{
	
	public Pix() {
		setNome("Pix"); //adicionando ao atributo nome de FormaPagamento, o seu respectivo nome
	}
	
	/**
	 * Método da superclasse, sobreescrito na subclasse.
	 */
	@Override
	public void realizarPagamento(BigDecimal valor) {
		System.out.println("Pagamento por Pix");
	}
}
