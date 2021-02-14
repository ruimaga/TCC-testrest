package br.com.tcc.testrest.model;

/**
 * 
 * @author Rui
 * object 
	{"chave": "valor", "chave": "valor"}
	
	list 
	"lista" : [
		{"ATT1":"VALOR1", "AT2":"VALOR2"}
	]
	
	array
		["1", "2", "3"]
	
	number
		1, -1, 2.4
	
	string
		"CARACTERES"
	
	boolean
		true, false
	
	null

 *
 **/
public enum TipoAtributo {

//	LIST("LIST"),
//	ARRAY("ARRAY"),
//	OBJECT("OBJECT"), 
	NUMBER("NUMBER"),
	STRING("STRING"),
	NULL("NULL"),
	BOOLEAN("BOOLEAN");
	
	private String descricao;
	
	TipoAtributo(String descricao){
	
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
