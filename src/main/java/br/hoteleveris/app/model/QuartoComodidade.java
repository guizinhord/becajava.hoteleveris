package br.hoteleveris.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuartoComodidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "quartoId")
	private Quarto quartoComodidade;

	@ManyToOne
	@JoinColumn(name = "comodidadeId")
	private Comodidade comodidade;

	public Quarto getQuartoComodidade() {
		return quartoComodidade;
	}

	public void setQuartoComodidade(Quarto quartoComodidade) {
		this.quartoComodidade = quartoComodidade;
	}

	public Comodidade getComodidade() {
		return comodidade;
	}

	public void setComodidade(Comodidade comodidade) {
		this.comodidade = comodidade;
	}

}
