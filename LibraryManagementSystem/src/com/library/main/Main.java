package com.library.main;

import java.util.Scanner;

import com.library.dao.BookDAO;
import com.library.dao.BookIssueDAO;
import com.library.dao.MemberDAO;
import com.library.model.Book;
import com.library.model.BookIssue;
import com.library.model.Member;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		BookDAO bookDAO = new BookDAO();
		MemberDAO memberDAO = new MemberDAO();
		BookIssueDAO bookIssueDAO = new BookIssueDAO();

		while (true) {

			System.out.println("\n=== Library Management System ===");

			System.out.println("1. Add Book");
			System.out.println("2. View Books");
			System.out.println("3. Search Book");
			System.out.println("4. Add Member");
			System.out.println("5. View Members");
			System.out.println("6. Search Member");
			System.out.println("7. Issue Book");
			System.out.println("8. Return Book");
			System.out.println("9. View Issued Books");
			System.out.println("10. Exit");

			System.out.print("\nEnter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {

			// 1. ADD BOOK

			case 1:
				System.out.print("Enter Book ID (1 onwards): ");
				int bookId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Book Name: ");
				String bookName = sc.nextLine();

				System.out.print("Enter Author: ");
				String author = sc.nextLine();

				System.out.print("Enter Category: ");
				String category = sc.nextLine();

				System.out.print("Enter Quantity: ");
				int quantity = sc.nextInt();

				Book book = new Book(bookId, bookName, author, category, quantity);
				bookDAO.addBook(book);
				break;

			// 2. VIEW BOOKS

			case 2:
				bookDAO.viewBooks();
				break;

			// 3. SEARCH BOOK

			case 3:
				System.out.print("Enter Book ID to search: ");
				int searchBookId = sc.nextInt();

				bookDAO.searchBook(searchBookId);
				break;

			// 4. ADD MEMBER

			case 4:
				System.out.print("Enter Member ID (101 onwards): ");
				int memberId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Member Name: ");
				String memberName = sc.nextLine();

				System.out.print("Enter Phone: ");
				String phone = sc.nextLine();

				System.out.print("Enter Email: ");
				String email = sc.nextLine();

				Member member = new Member(memberId, memberName, phone, email);
				memberDAO.addMember(member);
				break;

			// 5. VIEW MEMBERS

			case 5:
				memberDAO.viewMembers();
				break;

			// 6. SEARCH MEMBER

			case 6:
				System.out.print("Enter Member ID to search: ");
				int searchMemberId = sc.nextInt();

				memberDAO.searchMember(searchMemberId);
				break;

			// 7. ISSUE BOOK

			case 7:
				System.out.print("Enter Issue ID (2001 onwards): ");
				int issueId = sc.nextInt();

				System.out.print("Enter Book ID: ");
				int issueBookId = sc.nextInt();

				System.out.print("Enter Member ID: ");
				int issueMemberId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Issue Date (yyyy-mm-dd): ");
				String issueDateInput = sc.nextLine();

				java.sql.Date issueDate = java.sql.Date.valueOf(issueDateInput);
				BookIssue bookIssue = new BookIssue(issueId, issueBookId, issueMemberId, issueDate, null, "Issued");
				bookIssueDAO.issueBook(bookIssue);
				break;

			// 8. RETURN BOOK

			case 8:
				System.out.print("Enter Issue ID: ");
				int returnIssueId = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Return Date (yyyy-mm-dd): ");
				String returnDateInput = sc.nextLine();

				java.sql.Date returnDate = java.sql.Date.valueOf(returnDateInput);
				bookIssueDAO.returnBook(returnIssueId, returnDate);
				break;

			// 9. VIEW ISSUED BOOKS

			case 9:
				bookIssueDAO.viewIssuedBooks();
				break;

			// 10. EXIT

			case 10:
				System.out.println("Thank you!");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice!");
			}
		}
	}
}