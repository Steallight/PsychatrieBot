package de.steallight.testbot.main;

public class SQLManager {

    public static void onCreate(){
        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS RuleChannel(channelId INTEGER NOT NULL PRIMARY KEY)");
        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS TicketChannel(channelId INTEGER NOT NULL PRIMARY KEY)");
        LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS NotifyChannel(channelId INTEGER NOT NULL PRIMARY KEY)");
    }
}
