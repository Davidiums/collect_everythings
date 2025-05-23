package com.davidius.shared.user;

public class UserContext {
    private static final ThreadLocal<UserContext> ctx = new ThreadLocal<>();
    private String mail, firstName, lastName, role, subject;
    private long id;

    // setters et getters
    public static void set(UserContext user) { ctx.set(user); }
    public static UserContext get() { return ctx.get(); }
    public static void clear() { ctx.remove(); }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}
