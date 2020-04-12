/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufabc.sged.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

/**
 *
 * @author Caique
 */

@Entity
public class User extends PanacheEntity {
	
	@NotEmpty(message = "Nome não pode ser vazio")
	public String nome;
	
	@NotEmpty(message = "Email não pode ser vazio")
	public String email;
	
	@NotEmpty(message = "Senha não pode ser vazio")
	public String senha;
	
	@NotNull(message = "Nível de acesso não pode ser nulo")
	public int nivel_de_acesso;
	
	@NotNull(message = "Situação não pode ser nulo")
	public int situacao;
	
	//@ManyToMany
	//public Collection<UsersGroup> group = new ArrayList<>();
}
