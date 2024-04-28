package com.github.zdanielm.sfm_projekt.model;

import javax.persistence.*;

@Entity
@Table(name = "gotham_knights")
public class Gotham_knights {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "knight_id")
    private int bat_id;

    @Column(name = "username")
    private String username;

    @Column(name = "Gname")
    private String Gname;

    @Column(name = "access_right")
    private String access_rights;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "gothamKnight", cascade = CascadeType.ALL)
    private Allies ally;

    public Gotham_knights() {
    }

    public Gotham_knights(String username, String gname, String access_rights, String password) {
        this.username = username;
        Gname = gname;
        this.access_rights = access_rights;
        this.password = password;
    }

    public void setName(String name) {
        this.Gname = name;
    }

    /*public void setBat_id(int bat_id) {
        this.bat_id = bat_id;
    }*/

    public int getBat_id() {
        return bat_id;
    }

    public String getAccess_rights() {
        return access_rights;
    }
    public String getGName() {
        return Gname;
    }
    public String getPassword() {
        return password;
    }

    public void setAccess_rights(String access_rights) {
        this.access_rights = access_rights;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public void setBat_id(int bat_id) {
        this.bat_id = bat_id;
    }

    public Allies getAlly() {
        return ally;
    }

    public void setAlly(Allies ally) {
        this.ally = ally;
    }
}

