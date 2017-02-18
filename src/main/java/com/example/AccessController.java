package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ummers on 30/03/16.
 */


@Controller
public class AccessController {


    @RequestMapping(value = "/")
    public String redirect() throws IOException {
        return "redirect:swagger-ui.html";
    }


    @RequestMapping(value = "/gcp")
    @ResponseBody
    public Map<String, String> getDBValue() throws IOException {
        String instanceConnectionName = "marine-shell-158521:us-east1:fg-db";

        // TODO: fill this in
        // The database from which to list tables.
        String databaseName = "fg-db";

        String username = "root";

        // TODO: fill this in
        // This is the password that was set via the Cloud Console or empty if never set
        // (not recommended).
        String password = "P@ssw0rd1234";


        //[START doc-example]
        String jdbcUrl = String.format(
                "jdbc:mysql://google/fgcart?cloudSqlInstance=marine-shell-158521:us-east1:fg-db&socketFactory=com.google.cloud.sql.mysql.SocketFactory",
                databaseName,
                instanceConnectionName);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //[END doc-example]
        String tables = "";
        Map<String, String> tableMaps = new HashMap<>();
        try {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SHOW TABLES");
                int i = 0;
                while (resultSet.next()) {
                    tableMaps.put(String.valueOf(i++), resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableMaps;
    }

}
