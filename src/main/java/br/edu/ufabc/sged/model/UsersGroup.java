package br.edu.ufabc.sged.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class UsersGroup extends PanacheEntity{
	
	@NotEmpty(message = "Nome do grupo deve ser especificado.")
    public String nome;
	
	@NotEmpty(message = "Descrição do grupo deve seespecificada.")
    public String descricao;
	
	@NotNull(message = "Nível do grupo não pode ser nulo.")
    public int nivel;
	
	//@ManyToMany
	//public Collection<User> user = new ArrayList<>();
}
