package fr.vincentfillon.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Utilisateur {
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);
    long millis = cal.getTimeInMillis();

    private int idUser=0;
    private int idRole=0;

    private String username="";



    private String password="";
    private String birthday = simpleDateFormat.format(new Date());
    private Timestamp createdAt= new Timestamp(millis);

    public Utilisateur(int idUser, int idRole, String username, String password, String birthday, Timestamp createdAt) {
        this.idUser = idUser;
        this.idRole = idRole;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }
    public Utilisateur(){};
    public int getIdUser() {
        return idUser;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
