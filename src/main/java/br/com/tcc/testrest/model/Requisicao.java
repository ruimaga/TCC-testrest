package br.com.tcc.testrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Requisicao  implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long			id;
	
	@NotBlank(message = "Campo URL é obrigatório")
	private String 	    	url;
	
	@NotNull(message = "Campo Tipo de método é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoMetodo 		metodo;
	
	@NotNull(message = "Campo atributo é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoAtributo 	atributo;
	
	@NotNull(message = "Campo atributo é obrigatório")
	@Column(nullable = false, length = 80)
	private String 	nomeAtributo;
	
	@NotNull(message = "Campo valor é obrigatório")
	private String 	    	valorValidado;
	
	private String 	    	valorRetorno;

	private String		 	corpoMsg;
	
	private Integer	    	statusCode;

	private String          valorCerto;
	
	private String			atributoCorreto;
	
	private String			msg;
	
	private String			nodes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoMetodo getMetodo() {
		return metodo;
	}

	public void setMetodo(TipoMetodo metodo) {
		this.metodo = metodo;
	}

	public TipoAtributo getAtributo() {
		return atributo;
	}

	public void setAtributo(TipoAtributo atributo) {
		this.atributo = atributo;
	}

	public String getNomeAtributo() {
		return nomeAtributo;
	}

	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}

	public String getValorValidado() {
		return valorValidado;
	}

	public void setValorValidado(String valorValidado) {
		this.valorValidado = valorValidado;
	}

	public String getValorRetorno() {
		return valorRetorno;
	}

	public void setValorRetorno(String valorRetorno) {
		this.valorRetorno = valorRetorno;
	}

	public String getCorpoMsg() {
		return corpoMsg;
	}

	public void setCorpoMsg(String corpoMsg) {
		this.corpoMsg = corpoMsg;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getValorCerto() {
		return valorCerto;
	}

	public void setValorCerto(String valorCerto) {
		this.valorCerto = valorCerto;
	}

	public String getAtributoCorreto() {
		return atributoCorreto;
	}

	public void setAtributoCorreto(String atributoCorreto) {
		this.atributoCorreto = atributoCorreto;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public Requisicao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requisicao(Long id, String url, TipoMetodo metodo, TipoAtributo atributo, String nomeAtributo,
			String valorValidado, String valorRetorno, String corpoMsg, Integer statusCode, String valorCerto,
			String atributoCorreto, String msg,String nodes) {
		super();
		this.id = id;
		this.url = url;
		this.metodo = metodo;
		this.atributo = atributo;
		this.nomeAtributo = nomeAtributo;
		this.valorValidado = valorValidado;
		this.valorRetorno = valorRetorno;
		this.corpoMsg = corpoMsg;
		this.statusCode = statusCode;
		this.valorCerto = valorCerto;
		this.atributoCorreto = atributoCorreto;
		this.msg = msg;
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "Requisicao [id=" + id + ", url=" + url + ", metodo=" + metodo + ", atributo=" + atributo
				+ ", nomeAtributo=" + nomeAtributo + ", valorValidado=" + valorValidado + ", valorRetorno="
				+ valorRetorno + ", corpoMsg=" + corpoMsg + ", statusCode=" + statusCode + ", valorCerto=" + valorCerto
				+ ", atributoCorreto=" + atributoCorreto + ", msg=" + msg + ", nodes=" + nodes + "]";
	}

	
	
	
}
