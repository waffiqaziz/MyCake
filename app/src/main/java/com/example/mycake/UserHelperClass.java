package com.example.mycake;

public class UserHelperClass {
  private String fullName, username, password, password2, phoneNumber, email;


  public UserHelperClass(String fullName, String username, String password, String password2, String phoneNumber, String email) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.password2 = password2;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public UserHelperClass() {
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword2() {
    return password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
