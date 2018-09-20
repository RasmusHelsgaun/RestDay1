/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Rasmus
 */
public class User {
    private String username;
    private int deletiondate;

    public User() {
    }

    public User(String username, int deletiondate) {
        this.username = username;
        this.deletiondate = deletiondate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDeletiondate() {
        return deletiondate;
    }

    public void setDeletiondate(int deletiondate) {
        this.deletiondate = deletiondate;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", deletiondate=" + deletiondate + '}';
    }
}
