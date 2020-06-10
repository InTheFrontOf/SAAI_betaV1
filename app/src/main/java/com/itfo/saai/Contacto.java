package com.itfo.saai;

public class Contacto {
    private  int idUser;
    private  int id_user;
    private String userImg;
    private String nombre;
    private String email;

    public Contacto() {

    }

    public Contacto(int idUser, String nombre, String email, String userImg) {
        this.id_user = id_user;
        this.userImg = userImg;
        this.idUser = idUser;
        this.nombre = nombre;
        this.email = email;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
