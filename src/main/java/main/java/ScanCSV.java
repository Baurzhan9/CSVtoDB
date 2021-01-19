package main.java;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScanCSV implements Services{

    private String CsvPath;
    private String split;
    private SqlRequest request;
    private DatabaseConnection databaseConnection;
    private BufferedReader data = null;
    private BufferedReader column = null;


    public ScanCSV(String csv, String split, String request) throws IOException, SQLException {
        this.CsvPath = csv;
        this.split = split;
    }

    public ScanCSV() {

    }

    public void setCsvPath(String csvPath) {
        this.CsvPath = csvPath;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public void setRequest(SqlRequest request) {
        this.request = request;
    }


    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }



    @Override
    public void all() throws SQLException, IOException {

        String TableName = TableName(request.Request());

        data = new BufferedReader(new FileReader(CsvPath));
        column = new BufferedReader(new FileReader(CsvPath));

        String columns = column.readLine();
        long row = data.lines().count();

        String [][] table = Checker();

        for(int i = 0; i < row-1; i++){
            String array = "";
            for(int j = 0; j < columns.split(split).length; j++){
                if(j == 0){
                    array += table[i][j] + ",";
                } else if(j != columns.split(split).length-1) {
                    array += "'" + table[i][j] + "'" + ",";
                }else{
                    array += "'" + table[i][j] + "'" ;
                }
            }
            System.out.println(array);
            databaseConnection.updateData(TableName, columns, array);
        }
    }

    public String[][] Checker() throws IOException {

        data = new BufferedReader(new FileReader(CsvPath));
        column = new BufferedReader(new FileReader(CsvPath));

        String columns = column.readLine();

        long row = data.lines().count();
        String [][] table = new String[(int) row][columns.split(split).length];
        String line = "";
        String [] tabl;

        for(int i = 0; i < row-1; i++){
            if((line = column.readLine()) != null) {
                tabl = line.split(split);

                for (int j = 0; j < columns.split(split).length; j++) {
                    if(tabl[j].trim().length() == 0){
                        table[i][j] = "null";
                    }else {
                        table[i][j] = tabl[j];
                    }

                    int indexx =  table[i][j].indexOf('"');
                    int indexc =  table[i][j].indexOf("'");
                    if(indexc == -1){ }else {
                        StringBuffer stringBuffer = new StringBuffer(table[i][j]);
                        stringBuffer.delete(indexc,indexc+1);
                        table[i][j] = stringBuffer.toString();
                    }
                    if(indexx == -1){ }else {
                        StringBuffer stringBuffer = new StringBuffer(table[i][j]);
                        stringBuffer.delete(indexx,indexx+1);
                        table[i][j] = stringBuffer.toString();
                    }
                }
            }
        }

        return table;
    }


    public String TableName(String request) {
        String name = request;

        int index = name.indexOf("TABLE");
        int last = name.indexOf("(");

        String TableName = name.substring(index + 6, last - 1);

        return TableName;
    }


}
