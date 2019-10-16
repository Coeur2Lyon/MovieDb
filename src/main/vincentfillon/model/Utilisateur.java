package main.vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class Utilisateur {
    String dateFormat = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);
    long millis = cal.getTimeInMillis();

    private int idUser=0;
    private int idRole=0;

    private StringProperty username;
    private StringProperty password;
    private StringProperty email;
    private String birthday = simpleDateFormat.format(new Date());
    private Timestamp createdAt= new Timestamp(millis);
    private int isDeleted;

    private Object StringProperty;

    public Utilisateur(){this(0,0,null,null,null,null);}

    public Utilisateur(int idUser, int idRole, String username, String password, String email, String birthday, Date createdAt, int isDeleted) {
        this.idUser = idUser;
        this.idRole = idRole;
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email=new SimpleStringProperty(email);
        this.birthday =birthday;
        this.createdAt = new Timestamp(createdAt.getTime());
        this.isDeleted =isDeleted;
    }
    public Utilisateur(int idUser, int idRole, String username, String password, String email, String birthday) {
        this.idUser = idUser;
        this.idRole = idRole;
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.email=new SimpleStringProperty(email);
        this.birthday = birthday;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdRole() {
        return idRole;
    }


    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) { this.createdAt = new Timestamp(createdAt.getTime());}

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public int getIsDeleted() { return isDeleted;}

    public void setIsDeleted(int isDeleted) {this.isDeleted = isDeleted;}
}
