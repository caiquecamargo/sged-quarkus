package br.edu.ufabc.sged.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Item extends PanacheEntity{
	
	@NotEmpty(message = "Tipo de item deve ser especificado.")
    public String  tipo;
	
	@NotEmpty(message = "Nome do item não pode ser nulo")
    public String  nome;
	
    public String  restricoes;
    
    public String  src;
    
    //@ManyToOne
	//public User user;
}
