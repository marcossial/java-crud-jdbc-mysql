package com.marcossial.javacrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/basic_crud", "user1", "pass")) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("--- Basic Java CRUD CLI ---");
                System.out.println("choose action: "
                + "\n0 - TERMINATE"
                + "\n1 - CREATE"
                + "\n2 - READ"
                + "\n3 - UPDATE"
                + "\n4 - DELETE");

                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 0:
                        // TERMINATE PROGRAM
                        System.exit(0);
                        break;
                    case 1:
                        // CREATE BOOK
                        System.out.println("Enter the book's title");
                        String titulo = scanner.nextLine();
                        System.out.println("Enter the book's author");
                        String autor = scanner.nextLine();
                        System.out.println("Enter the book's year");
                        int anoPublicacao = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the book's publisher");
                        String editora = scanner.nextLine();
                        System.out.println("Enter how many books are stored");
                        int quantidadeEstoque = scanner.nextInt();
                        scanner.nextLine();

                        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao, editora, quantidade_estoque"
                                + " VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            preparedStatement.setString(1, editora);
                            preparedStatement.setString(2, autor);
                            preparedStatement.setInt(3, anoPublicacao);
                            preparedStatement.setString(4, editora);
                            preparedStatement.setInt(5, quantidadeEstoque);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to create new book: " + e);
                        }
                        break;
                    case 2:
                        // READ

                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to server: " + e);
        }
    }
}
