package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.model.BookIssue;
import com.library.util.DBConnection;

public class BookIssueDAO {

	// 1. ISSUE BOOK

	public void issueBook(BookIssue bookIssue) {

		Connection con = null;

		try {

			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String checkBook = "SELECT quantity FROM books WHERE book_id = ?";
			PreparedStatement ps1 = con.prepareStatement(checkBook);
			ps1.setInt(1, bookIssue.getBookId());
			ResultSet rs = ps1.executeQuery();

			if (!rs.next()) {

				System.out.println("Book not found!");
				con.rollback();
				return;
			}

			int quantity = rs.getInt("quantity");

			if (quantity <= 0) {

				System.out.println("Book is not available!");
				con.rollback();
				return;
			}

			String insertIssue = "INSERT INTO book_issues "
					+ "(issue_id, book_id, member_id, issue_date, return_date, status) " + "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps2 = con.prepareStatement(insertIssue);
			ps2.setInt(1, bookIssue.getIssueId());
			ps2.setInt(2, bookIssue.getBookId());
			ps2.setInt(3, bookIssue.getMemberId());
			ps2.setDate(4, bookIssue.getIssueDate());
			ps2.setDate(5, bookIssue.getReturnDate());
			ps2.setString(6, bookIssue.getStatus());
			ps2.executeUpdate();

			String updateBook = "UPDATE books " + "SET quantity = quantity - 1 " + "WHERE book_id = ?";

			PreparedStatement ps3 = con.prepareStatement(updateBook);
			ps3.setInt(1, bookIssue.getBookId());
			ps3.executeUpdate();
			con.commit();
			System.out.println("Book issued successfully!");

		} catch (Exception e) {

			try {

				if (con != null) {

					con.rollback();
					System.out.println("Transaction failed. Changes rolled back.");
				}

			} catch (Exception rollbackException) {

				rollbackException.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			try {

				if (con != null) {

					con.setAutoCommit(true);
					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	// 2. VIEW ISSUED BOOKS

	public void viewIssuedBooks() {

		String sql = "SELECT " + "bi.issue_id, " + "b.book_name, " + "m.member_name, " + "bi.issue_date, "
				+ "bi.return_date, " + "bi.status " + "FROM book_issues bi " + "JOIN books b ON bi.book_id = b.book_id "
				+ "JOIN members m ON bi.member_id = m.member_id";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("\n===== Issued Books =====");

			while (rs.next()) {

				System.out.println(rs.getInt("issue_id") + " | " + rs.getString("book_name") + " | "
						+ rs.getString("member_name") + " | " + rs.getDate("issue_date") + " | "
						+ rs.getDate("return_date") + " | " + rs.getString("status"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 3. RETURN BOOK

	public void returnBook(int issueId, java.sql.Date returnDate) {

		Connection con = null;

		try {

			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			String getIssue = "SELECT book_id, issue_date, status " + "FROM book_issues " + "WHERE issue_id = ?";
			PreparedStatement ps1 = con.prepareStatement(getIssue);
			ps1.setInt(1, issueId);
			ResultSet rs = ps1.executeQuery();

			if (!rs.next()) {

				System.out.println("Issue record not found!");
				con.rollback();
				return;
			}

			int bookId = rs.getInt("book_id");

			java.sql.Date issueDate = rs.getDate("issue_date");
			String status = rs.getString("status");

			if (status.equalsIgnoreCase("Returned")) {

				System.out.println("Book already returned!");
				con.rollback();
				return;
			}

			long days = (returnDate.getTime() - issueDate.getTime()) / (1000 * 60 * 60 * 24);
			long allowedDays = 7;
			long lateDays = 0;

			if (days > allowedDays) {

				lateDays = days - allowedDays;
			}

			long fine = lateDays * 10;

			String updateIssue = "UPDATE book_issues " + "SET return_date = ?, status = 'Returned' "
					+ "WHERE issue_id = ?";

			PreparedStatement ps2 = con.prepareStatement(updateIssue);
			ps2.setDate(1, returnDate);
			ps2.setInt(2, issueId);
			ps2.executeUpdate();
			String updateBook = "UPDATE books " + "SET quantity = quantity + 1 " + "WHERE book_id = ?";
			PreparedStatement ps3 = con.prepareStatement(updateBook);
			ps3.setInt(1, bookId);
			ps3.executeUpdate();
			con.commit();

			System.out.println("Book returned successfully!");
			System.out.println("Days borrowed: " + days);
			System.out.println("Late days: " + lateDays);
			System.out.println("Fine: ₹" + fine);

		} catch (Exception e) {

			try {

				if (con != null) {

					con.rollback();
					System.out.println("Transaction failed. Changes rolled back.");
				}

			} catch (Exception rollbackException) {

				rollbackException.printStackTrace();
			}

			e.printStackTrace();

		} finally {

			try {

				if (con != null) {

					con.setAutoCommit(true);
					con.close();
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}