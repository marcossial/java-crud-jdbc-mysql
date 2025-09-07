package com.marcossial.javacrud;

import java.io.Serializable;

public class Livro implements Serializable {

    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] %s (%d) - %s | Autor: %s | Qtd: %d",
                id, titulo, ano, editora, autor, quantidade
        );
    }
}