package de.steallight.testbot.main;

import javax.security.auth.login.LoginException;

public class TestBot {


    public static void main(final String[] args) {
        try {
             Bot bot = new Bot();
        } catch (final LoginException e) {
            e.printStackTrace();
        }
    }

}
