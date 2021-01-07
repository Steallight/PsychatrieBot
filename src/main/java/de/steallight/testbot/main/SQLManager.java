package de.steallight.testbot.main;

public class SQLManager {

    public static void onCreate() {

        //username MemberID

        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS UserDataBase(name Text, memberid INTEGER)");

    }


}
