package com.mannan.demoapp.Repository.AzureConn;

import lombok.Data;

import java.sql.*;

@Data
public class DefaultCon {
    private String con =
            "jdbc:sqlserver://piegroup1.database.windows.net:1433;" +
                    "database=fontysin;" +
                    "user=piegroup1_dboer@piegroup1;" +
                    "password=!VraagaanFlip;" +
                    "encrypt=true;" +
                    "trustServerCertificate=false;" +
                    "hostNameInCertificate=*.database.windows.net;" +
                    "loginTimeout=30";
    private ResultSet result = null;
}
