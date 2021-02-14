package br.com.tcc.testrest.model;


public enum TipoMetodo {
	
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE");
	
	private String descricao;
	
	TipoMetodo(String descricao){
	
		this.descricao = descricao;
		
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
