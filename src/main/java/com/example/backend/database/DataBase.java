package com.example.backend.database;

import com.example.backend.host.Host;
/*import lombok.SneakyThrows;*/

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {

/*    public void start(){
        String query = scanner.nextLine();

        if (query.startsWith("select * from ")) {
            command(query);

        } else {
            if (cmd(query) == 0) {
                System.out.println("completed");
            }
        }
    }*/

/*    public static int cmd(String query) {
        try {
            return Host.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @SneakyThrows
    public static void command(String str) {
        String[] st = str.split(" ");
        ResultSet resultSet = Host.statement.executeQuery(str);
        DatabaseMetaData metaData = Host.connection.getMetaData();
        //System.out.println("Enter table name: ");
        //String name = Host.scanner.next();
        try {
            ResultSet rs = metaData.getColumns(null, null, st[3], null);
            StringBuilder builder = new StringBuilder();
            while (rs.next()) {
                String colummName = rs.getString("COLUMN_NAME");
                builder.append(colummName + "#");
            }
            String[] strings = builder.toString().split("#");
            System.out.println(builder);

            while (resultSet.next()) {
                for (String s : strings) {
                    System.out.print(resultSet.getObject(s) + " <---> ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error");
        }


    }*/

}
