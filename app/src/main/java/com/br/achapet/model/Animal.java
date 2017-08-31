package com.br.achapet.model;

import java.io.Serializable;

public class Animal implements Serializable {
    private int codigo;
    private String nome;
    private String raca;
    private String tipo;
    private String descricao;
    private int idade;
    private String porte;
    private String foto;
    private int adotado;
    private Usuario usuario;

    public Animal() {}

    public Animal(String nome, String raca, String tipo, String descricao, int idade, String porte, String foto, Usuario dono) {
        this.nome = nome;
        this.raca = raca;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idade = idade;
        this.porte = porte;
        this.foto = foto;
        this.usuario = dono;
        this.adotado = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDono(Usuario dono) {
        this.usuario = dono;
    }

    public int isAdotado() {
        return this.adotado;
    }

    public void setAdotado(int adotado) {
        this.adotado = adotado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idade=" + idade +
                ", porte='" + porte + '\'' +
                ", foto='" + foto + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
