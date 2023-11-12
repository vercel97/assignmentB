package dat250.votingapp;

import org.h2.tools.Server;

import java.sql.SQLException;

public class DatabaseConsoleServer {
    public static void main(String[] args) throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8081")
                .start();
    }
}
