package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.model.Book;
import com.library.util.DBConnection;

public class BookDAO {

    public void addBook(Book book) {

        String sql = "INSERT INTO books "
                   + "(book_id, book_name, author, category, quantity) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getQuantity());
            ps.executeUpdate();
            System.out.println("Book added successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void viewBooks() {

        String sql = "SELECT * FROM books";

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("\n===== Book List =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("book_id") + " | " +
                        rs.getString("book_name") + "\t| " +
                        rs.getString("author") + "\t| " +
                        rs.getString("category") + "\t| " +
                        rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public void searchBook(int bookId) {

		String sql = "SELECT * FROM books WHERE book_id = ?";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("\nBook Found!");
				System.out.println("ID: " + rs.getInt("book_id"));
				System.out.println("Name: " + rs.getString("book_name"));
				System.out.println("Author: " + rs.getString("author"));
				System.out.println("Category: " + rs.getString("category"));
				System.out.println("Quantity: " + rs.getInt("quantity"));

			} else {

				System.out.println("Book not found!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}