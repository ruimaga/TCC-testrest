/*
 * www.paulocollares.com.br
 */
package br.com.tcc.testrest.test;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tcc.testrest.model.Requisicao;

public class TesteRequisicaoRest {

	private static final Logger log = LoggerFactory.getLogger(TesteRequisicaoRest.class);
	private Requisicao requisicao;
	private ObjectMapper objectMapper;
	private RestTemplate restTemplate;
	private ResponseEntity<String> result;
	private HttpHeaders headers;
	private HttpEntity<String> request;
	private String valorRetorno   		= null;
	private String valorValidar   		= null;
	private JsonNode node               =  null;
	
	public TesteRequisicaoRest() {
		this.requisicao 	= new Requisicao();
		this.objectMapper 	= new ObjectMapper();	 
		this.restTemplate 	= new RestTemplate();
		this.headers 		= new HttpHeaders();
		this.headers.setContentType(MediaType.APPLICATION_JSON); 
	}

	//Testar metodo GET - getForEntity
	public Requisicao testarMetodoGet(Requisicao requisicao) {
		
		setRequisicao(requisicao);
		
		this.objectMapper 	= new ObjectMapper();	 
		this.restTemplate 	= new RestTemplate();
		
		try {
			
			
//			Object[] objects = restTemplate.getForObject(requisicao.getUrl(), Object[].class);
//			Object[] objects = responseEntity.getBody();
			result = restTemplate.getForEntity(requisicao.getUrl(), String.class);
			this.requisicao.setStatusCode(result.getStatusCodeValue());
				
			if (result.getStatusCodeValue() == 200) {
			    JsonNode root = objectMapper.readTree(result.getBody());
				
				switch(requisicao.getAtributo()) {
				  case NUMBER:
					  this.node = root;
					  if (requisicao.getNodes() != null && requisicao.getNodes().trim().length() > 0) {
						  String[] nodes = requisicao.getNodes().trim().split(";");
						  for (int i = 0; i < nodes.length; i++) {
							   this.node = this.node.path(nodes[i]);
						  }
					  }
					  if (this.node.isArray()) {
						  for (final JsonNode objNode : this.node) {
							  this.valorRetorno   = objNode.path(requisicao.getNomeAtributo().trim().toString()).toString();
							  this.valorValidar   = requisicao.getValorValidado().trim();
							  this.requisicao.setValorRetorno(valorRetorno);
							  
							  if (Float.parseFloat(valorRetorno) == Float.parseFloat(valorValidar)) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
						  }	  
					  } else {
						  this.valorRetorno   = this.node.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (Float.parseFloat(valorRetorno) == Float.parseFloat(valorValidar)) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
					  }
					  break;
				  case STRING:
					  this.node = root;
					  if (requisicao.getNodes() != null && requisicao.getNodes().trim().length() > 0) {
						  String[] nodes = requisicao.getNodes().trim().split(";");
						  for (int i = 0; i < nodes.length; i++) {
							   this.node = this.node.path(nodes[i]);
						  }
					  }
					  
					  if (this.node.isArray()) {
						  for (final JsonNode objNode : this.node) {
							  this.valorRetorno   = objNode.path(requisicao.getNomeAtributo().trim().toString()).toString();
							  this.valorValidar   = requisicao.getValorValidado().trim();
							  this.requisicao.setValorRetorno(valorRetorno);
							  
							  if (this.valorRetorno.equals(this.valorValidar)) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
								  break;
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
						}
						  
					  } else {
						  this.valorRetorno   = this.node.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);

							 if (this.valorRetorno.equals(this.valorValidar)) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
								  break;
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
					  }
					  
				    break;
				  case BOOLEAN:
					  this.node = root;
					  if (requisicao.getNodes() != null && requisicao.getNodes().trim().length() > 0) {
						  String[] nodes = requisicao.getNodes().trim().split(";");
						  for (int i = 0; i < nodes.length; i++) {
							   this.node = this.node.path(nodes[i]);
						  }
					  }
					  
					  if (this.node.isArray()) {
						  for (final JsonNode objNode : this.node) {
							  this.valorRetorno  = objNode.path(requisicao.getNomeAtributo().trim().toString()).toString();
							  this.valorValidar  = requisicao.getValorValidado().trim();
							  this.requisicao.setValorRetorno(valorRetorno);
							  
							  if (Boolean.compare(Boolean.getBoolean(this.valorRetorno), Boolean.getBoolean(this.valorValidar)) == 0 ) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
								  break;
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
						}
						  
					  } else {
						  this.valorRetorno   = this.node.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (Boolean.compare(Boolean.getBoolean(this.valorRetorno), Boolean.getBoolean(this.valorValidar)) == 0 ) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
							  break;
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
					  }
					  
				    break;   
				  case NULL:
					  this.node = root;
					  if (requisicao.getNodes() != null && requisicao.getNodes().trim().length() > 0) {
						  String[] nodes = requisicao.getNodes().trim().split(";");
						  for (int i = 0; i < nodes.length; i++) {
							   this.node = this.node.path(nodes[i]);
						  }
					  }
					  
					  if (this.node.isArray()) {
						  for (final JsonNode objNode : this.node) {
							  this.valorRetorno   = objNode.path(requisicao.getNomeAtributo().trim().toString()).toString();
							  this.valorValidar   = requisicao.getValorValidado().trim();
							  this.requisicao.setValorRetorno(valorRetorno);
							  
							  if (this.valorRetorno.equals(this.valorValidar)) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
								  break;
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
						}
					  } else {
						  this.valorRetorno   = this.node.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);

							 if (this.valorRetorno.equals(this.valorValidar)) {
								  this.requisicao.setValorCerto("S");
								  this.requisicao.setMsg("SUCESSO");
								  break;
							  } else {
								  this.requisicao.setValorCerto("N");
								  this.requisicao.setMsg("INCORRETO");
							  }
					  }
				    break;   
				  default:
				    // code block
				}
				
				log.info("*** testarMetodoGet getForEntity result = " 			+ result);
				log.info(" valor atributo = " 									+ this.node.path(requisicao.getNomeAtributo().trim().toString()).toString());
				log.info("Sucesso Saida getForEntity root = " 					+ this.node);
				log.info("Saida getForEntity result4 = " 						+ result);
				log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
				log.info("Saida getForEntity result4.getHeaders() = " 			+ result.getHeaders());
				log.info("Saida getForEntity result4.getBody() = " 				+ result.getBody());
				log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
			} else {
				this.requisicao.setMsg("SEM COMPARACAO");
			}
			
		}catch (NumberFormatException e) {
			this.requisicao.setMsg("NumberFormatException");
			log.info("erro executaTesteGet " + e.getMessage());
		}catch (RestClientException e) {
			this.requisicao.setMsg(e.getMessage());
			log.info("erro executaTesteGet " + e.getMessage());
		}catch (Exception e) {
			this.requisicao.setMsg("Exception");
			log.info("erro executaTesteGet " + e.getMessage());
		}

		return this.requisicao;

	}

	//Testar metodo Post - exchange
		public Requisicao testarMetodoPost(Requisicao requisicao) {
			
			setRequisicao(requisicao);
			
			this.objectMapper 	= new ObjectMapper();	 
			this.restTemplate 	= new RestTemplate();
			JSONObject my_obj = new JSONObject(requisicao.getCorpoMsg());
			this.request = new 	HttpEntity<String>(my_obj.toString(), headers);
			
			try {
				
				this.result = restTemplate.exchange(requisicao.getUrl(), HttpMethod.POST,  this.request, String.class);
				this.requisicao.setStatusCode(result.getStatusCodeValue());
					
				if (result.getStatusCodeValue() == 201) {
				    JsonNode root = objectMapper.readTree(result.getBody());
					
					switch(requisicao.getAtributo()) {
					  case NUMBER:
						  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (Float.parseFloat(valorRetorno) == Float.parseFloat(valorValidar)) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
						  break;
					  case STRING:
						  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (this.valorRetorno.equals(this.valorValidar)) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
						  break;
					  default:
					    // code block
					}
					
					log.info("*** testarMetodoGet getForEntity result = " 			+ result);
					log.info(" valor atributo = " 									+ root.path(requisicao.getNomeAtributo().trim().toString()).toString());
					log.info("Sucesso Saida getForEntity root = " 					+ root);
					log.info("Saida getForEntity result4 = " 						+ result);
					log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
					log.info("Saida getForEntity result4.getHeaders() = " 			+ result.getHeaders());
					log.info("Saida getForEntity result4.getBody() = " 				+ result.getBody());
					log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
				} else {
					this.requisicao.setMsg("SEM COMPARACAO");
				}
				
			}catch (NumberFormatException e) {
				this.requisicao.setMsg("NumberFormatException");
				log.info("erro executaTesteGet " + e.getMessage());
			}catch (RestClientException e) {
				this.requisicao.setMsg(e.getMessage());
				log.info("erro executaTesteGet " + e.getMessage());
			}catch (Exception e) {
				this.requisicao.setMsg("Exception");
				log.info("erro executaTesteGet " + e.getMessage());
			}

			return this.requisicao;

		}
		
	//Testar metodo Put - exchange
	public Requisicao testarMetodoPut(Requisicao requisicao) {
		
		setRequisicao(requisicao);
		
		this.objectMapper 	= new ObjectMapper();	 
		this.restTemplate 	= new RestTemplate();
		JSONObject my_obj = new JSONObject(requisicao.getCorpoMsg());
		this.request = new HttpEntity<String>(my_obj.toString(), headers);
		
		try {
			
			this.result = restTemplate.exchange(requisicao.getUrl(), HttpMethod.PUT,  this.request, String.class);
			this.requisicao.setStatusCode(result.getStatusCodeValue());
				
			if (result.getStatusCodeValue() == 201) {
			    JsonNode root = objectMapper.readTree(result.getBody());
				
				switch(requisicao.getAtributo()) {
				  case NUMBER:
					  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
					  this.valorValidar   = requisicao.getValorValidado().trim();
					  this.requisicao.setValorRetorno(valorRetorno);
					  
					  if (Float.parseFloat(valorRetorno) == Float.parseFloat(valorValidar)) {
						  this.requisicao.setValorCerto("S");
						  this.requisicao.setMsg("SUCESSO");
					  } else {
						  this.requisicao.setValorCerto("N");
						  this.requisicao.setMsg("INCORRETO");
					  }
					  break;
				  case STRING:
					  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
					  this.valorValidar   = requisicao.getValorValidado().trim();
					  this.requisicao.setValorRetorno(valorRetorno);
					  
					  if (this.valorRetorno.equals(this.valorValidar)) {
						  this.requisicao.setValorCerto("S");
						  this.requisicao.setMsg("SUCESSO");
					  } else {
						  this.requisicao.setValorCerto("N");
						  this.requisicao.setMsg("INCORRETO");
					  }
				    break;
				  default:
				    // code block
				}
				
				log.info("*** testarMetodoGet getForEntity result = " 			+ result);
				log.info(" valor atributo = " 									+ root.path(requisicao.getNomeAtributo().trim().toString()).toString());
				log.info("Sucesso Saida getForEntity root = " 					+ root);
				log.info("Saida getForEntity result4 = " 						+ result);
				log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
				log.info("Saida getForEntity result4.getHeaders() = " 			+ result.getHeaders());
				log.info("Saida getForEntity result4.getBody() = " 				+ result.getBody());
				log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
			} else {
				this.requisicao.setMsg("SEM COMPARACAO");
			}
			
		}catch (NumberFormatException e) {
			this.requisicao.setMsg("NumberFormatException");
			log.info("erro executaTesteGet " + e.getMessage());
		}catch (RestClientException e) {
			this.requisicao.setMsg(e.getMessage());
			log.info("erro executaTesteGet " + e.getMessage());
		}catch (Exception e) {
			this.requisicao.setMsg("Exception");
			log.info("erro executaTesteGet " + e.getMessage());
		}

		return this.requisicao;

	}
	
	//Testar metodo Delete - exchange
		public Requisicao testarMetodoDelete(Requisicao requisicao) {
			
			String valorRetorno   = null;
			String valorValidar   = null;
			setRequisicao(requisicao);
			
			this.objectMapper 	= new ObjectMapper();	 
			this.restTemplate 	= new RestTemplate();
			JSONObject my_obj = new JSONObject(requisicao.getCorpoMsg());
			this.request = new HttpEntity<String>(my_obj.toString(), headers);
			
			try {
				
				this.result = restTemplate.exchange(requisicao.getUrl(), HttpMethod.DELETE,  this.request, String.class);
				this.requisicao.setStatusCode(result.getStatusCodeValue());
					
				if (result.getStatusCodeValue() == 200) {
				    JsonNode root = objectMapper.readTree(my_obj.toString());
					
					switch(requisicao.getAtributo()) {
					  case NUMBER:
						  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (Float.parseFloat(valorRetorno) == Float.parseFloat(valorValidar)) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
						  break;
					  case STRING:
						  this.valorRetorno   = root.path(requisicao.getNomeAtributo().trim().toString()).toString();
						  this.valorValidar   = requisicao.getValorValidado().trim();
						  this.requisicao.setValorRetorno(valorRetorno);
						  
						  if (this.valorRetorno.equals(this.valorValidar)) {
							  this.requisicao.setValorCerto("S");
							  this.requisicao.setMsg("SUCESSO");
						  } else {
							  this.requisicao.setValorCerto("N");
							  this.requisicao.setMsg("INCORRETO");
						  }
					    break;
					  default:
					    // code block
					}
					
					log.info("*** testarMetodoGet getForEntity result = " 			+ result);
					log.info(" valor atributo = " 									+ root.path(requisicao.getNomeAtributo().trim().toString()).toString());
					log.info("Sucesso Saida getForEntity root = " 					+ root);
					log.info("Saida getForEntity result4 = " 						+ result);
					log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
					log.info("Saida getForEntity result4.getHeaders() = " 			+ result.getHeaders());
					log.info("Saida getForEntity result4.getBody() = " 				+ result.getBody());
					log.info("Saida getForEntity result4.getStatusCodeValue() = " 	+ result.getStatusCodeValue());
				} else {
					this.requisicao.setMsg("SEM COMPARACAO");
				}
				
			}catch (NumberFormatException e) {
				this.requisicao.setMsg("NumberFormatException");
				log.info("erro executaTesteGet " + e.getMessage());
			}catch (RestClientException e) {
				this.requisicao.setMsg(e.getMessage());
				log.info("erro executaTesteGet " + e.getMessage());
			}catch (Exception e) {
				this.requisicao.setMsg("Exception");
				log.info("erro executaTesteGet " + e.getMessage());
			}

			return this.requisicao;

		}
				
	private void setRequisicao(Requisicao requisicao) {
		
		this.requisicao = new Requisicao();
		this.requisicao.setId(requisicao.getId());
		this.requisicao.setUrl(requisicao.getUrl());
		this.requisicao.setMetodo(requisicao.getMetodo());
		this.requisicao.setAtributo(requisicao.getAtributo());
		this.requisicao.setNomeAtributo(requisicao.getNomeAtributo());
		this.requisicao.setValorValidado(requisicao.getValorValidado());
		this.requisicao.setCorpoMsg(requisicao.getCorpoMsg());
		this.requisicao.setStatusCode(requisicao.getStatusCode());
		this.requisicao.setValorCerto(requisicao.getValorCerto());
		this.requisicao.setAtributoCorreto(requisicao.getAtributoCorreto());
		this.requisicao.setMsg(requisicao.getMsg());
		this.requisicao.setNodes(requisicao.getNodes());
	}

}
