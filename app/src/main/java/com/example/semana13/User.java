package com.example.semana13;

public class User {
    public String nombre;
    public String numero; // ✅ nuevo campo

    public User() {} // Requerido por Firebase

    public User(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
    }
}
