package br.com.tcc.testrest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atributo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer		id;
	private String 		valorRetorno;
	private String  	nomeAtributo;
	private String      validacao;
	
	@ManyToOne
    @JoinColumn(name="requisicao_id", nullable=false)
	private Requisicao 	request;

	public Atributo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValorRetorno() {
		return valorRetorno;
	}

	public void setValorRetorno(String valorRetorno) {
		this.valorRetorno = valorRetorno;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public String getValidacao() {
		return validacao;
	}

	public void setValidacao(String validacao) {
		this.validacao = validacao;
	}

	public Requisicao getRequest() {
		return request;
	}

	public void setRequest(Requisicao request) {
		this.request = request;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeAtributo == null) ? 0 : nomeAtributo.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result + ((validacao == null) ? 0 : validacao.hashCode());
		result = prime * result + ((valorRetorno == null) ? 0 : valorRetorno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atributo other = (Atributo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeAtributo == null) {
			if (other.nomeAtributo != null)
				return false;
		} else if (!nomeAtributo.equals(other.nomeAtributo))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (validacao == null) {
			if (other.validacao != null)
				return false;
		} else if (!validacao.equals(other.validacao))
			return false;
		if (valorRetorno == null) {
			if (other.valorRetorno != null)
				return false;
		} else if (!valorRetorno.equals(other.valorRetorno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atributo [id=" + id + ", valorRetorno=" + valorRetorno + ", nomeAtributo=" + nomeAtributo
				+ ", validacao=" + validacao + ", request=" + request + "]";
	}

}
