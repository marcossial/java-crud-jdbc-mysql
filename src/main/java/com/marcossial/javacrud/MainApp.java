package com.marcossial.javacrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

                String sql;
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

                        sql = "INSERT INTO livros (titulo, autor, ano_publicacao, editora, quantidade_estoque) "
                                + "VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            preparedStatement.setString(1, titulo);
                            preparedStatement.setString(2, autor);
                            preparedStatement.setInt(3, anoPublicacao);
                            preparedStatement.setString(4, editora);
                            preparedStatement.setInt(5, quantidadeEstoque);

                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Book registered.");
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to create new book: " + e);
                        }
                        break;
                    case 2:
                        // READ
                        ArrayList<Livro> livros = new ArrayList<>();
                        sql = "SELECT * FROM livros";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                             ResultSet resultSet = preparedStatement.executeQuery()) {
                            while (resultSet.next()) {
                                Livro l = new Livro();
                                l.setId(resultSet.getInt("id"));
                                l.setTitulo(resultSet.getString("titulo"));
                                l.setAutor(resultSet.getString("autor"));
                                l.setAno(resultSet.getInt("ano_publicacao"));
                                l.setEditora(resultSet.getString("editora"));
                                l.setQuantidade(resultSet.getInt("quantidade_estoque"));
                                livros.add(l);
                            }

                            for (Livro l : livros) {
                                System.out.println(l + "\n");
                            }

                            System.out.println("Press enter to continue");
                            scanner.nextLine();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to server: " + e);
        }
    }
}
