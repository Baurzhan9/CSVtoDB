package main.java;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlRequest implements Services{

    private String request = "";
    private String SqlPath;
    private DatabaseConnection databaseConnection;

    public String SqlRequest(String sql) throws IOException, SQLException {

        FileReader fileReader = new FileReader(sql);
        Scanner scan = new Scanner(fileReader);
        while (scan.hasNextLine()) {
            request += scan.nextLine();
        }
        fileReader.close();

        return request;
    }
    public String Request() throws IOException, SQLException {
        return SqlRequest(SqlPath);
    }

    public void setSqlPath(String sqlPath) {
        this.SqlPath = sqlPath;
    }


    @Override
    public void all() throws SQLException, IOException, NullPointerException {
        FileReader fileReader = new FileReader(SqlPath);
        Scanner scan = new Scanner(fileReader);
        while (scan.hasNextLine()) {
            request += scan.nextLine();
        }
        fileReader.close();

        databaseConnection.createTable(request);
//        return request;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
