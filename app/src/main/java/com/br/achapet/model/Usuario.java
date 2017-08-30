package com.br.achapet.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private int codigo;
    private String nome;
    private String login;
    private String senha;
    private String endereco;
    private String email;
    private String telefone;

    private ArrayList<Animal> meusAnimais = new ArrayList<Animal>();

    public Usuario() {}

    public Usuario(String nome, String login, String senha, String endereco, String email, String telefone) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Animal> getMeusAnimais() {
        return meusAnimais;
    }

    public void setMeusAnimais(ArrayList<Animal> meusAnimais) {
        this.meusAnimais = meusAnimais;
    }

    public void addAnimal(Animal a) {
        this.meusAnimais.add(a);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
