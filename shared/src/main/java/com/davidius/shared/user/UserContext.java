package com.davidius.shared.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserContext {
    private static final ThreadLocal<UserContext> ctx = new ThreadLocal<>();
    private String mail, firstName, lastName, role, subject;
    private long id;
    private boolean isAuthenticated;

    // setters et getters
    public static void set(UserContext user) { ctx.set(user); }
    public static UserContext get() { return ctx.get(); }
    public static void clear() { ctx.remove(); }

}
