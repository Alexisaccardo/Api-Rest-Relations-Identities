package com.example.demo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public static String select_document(String document_identity) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM identity WHERE document = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, document_identity);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            document_identity = resultSet.getString("document");
            return document_identity;

        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }

    public Identity register(String document, String name, String cellphone, String address) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM identity");

            String sql = "INSERT INTO identity (document , name, cellphone, address) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, document);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, cellphone);
            preparedStatement.setString(4, address);

            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("La identidad se registro de manera exitosa.");
                return new Identity(document, name, cellphone, address);
            } else {
                System.out.println(Errors.error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Identity(null,null,null,null);
    }

    public Relations register_relations(String name, String ally, String document_identity) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM relations");

            String sql = "INSERT INTO relations (name , ally, document_identity) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, ally);
            preparedStatement.setString(3, document_identity);

            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("La relacion se registro de manera exitosa.");
                return new Relations(name, ally, document_identity);
            } else {
                System.out.println(Errors.error_register_relations);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Relations(null,null,null);
    }

    public List<Identity> search_identity() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM identity");
        List<Identity> list = new ArrayList<>();

        while(resultSet2.next()){

            String document = resultSet2.getString("document");
            String name = resultSet2.getString("name");
            String cellphone = resultSet2.getString("cellphone");
            String address = resultSet2.getString("address");

            Identity identity = new Identity(document, name, cellphone, address);
            list.add(identity);
        }
        return list;
    }

    public List<Relations> search_relations() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM relations");
        List<Relations> list = new ArrayList<>();

        while(resultSet2.next()){

            String name = resultSet2.getString("name");
            String ally = resultSet2.getString("ally");
            String document_identity = resultSet2.getString("document_identity");

            Relations relations = new Relations(name,ally, document_identity);
            list.add(relations);
        }
        return list;
    }
    }





