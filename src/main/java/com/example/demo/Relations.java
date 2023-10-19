package com.example.demo;

public class Relations {

    public String name;
    public String ally;
    public String document_identity;

    public Relations(String name, String ally, String document_identity) {
        this.name = name;
        this.ally = ally;
        this.document_identity = document_identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlly() {
        return ally;
    }

    public void setAlly(String ally) {
        this.ally = ally;
    }

    public String getDocument_identity() {
        return document_identity;
    }

    public void setDocument_identity(String document_identity) {
        this.document_identity = document_identity;
    }
}
