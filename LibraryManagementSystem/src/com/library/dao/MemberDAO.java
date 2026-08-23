package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.model.Member;
import com.library.util.DBConnection;

public class MemberDAO {

	public void addMember(Member member) {

		String sql = "INSERT INTO members " + "(member_id, member_name, phone, email) " + "VALUES (?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, member.getMemberId());
			ps.setString(2, member.getMemberName());
			ps.setString(3, member.getPhone());
			ps.setString(4, member.getEmail());
			ps.executeUpdate();
			System.out.println("Member added successfully!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void viewMembers() {

		String sql = "SELECT * FROM members";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("\n===== Member List =====");

			while (rs.next()) {

				System.out.println(rs.getInt("member_id") + " | " + rs.getString("member_name") + " | "
						+ rs.getString("phone") + " | " + rs.getString("email"));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void searchMember(int memberId) {

		String sql = "SELECT * FROM members WHERE member_id = ?";

		try {

			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("\nMember Found!");
				System.out.println("ID: " + rs.getInt("member_id"));
				System.out.println("Name: " + rs.getString("member_name"));
				System.out.println("Phone: " + rs.getString("phone"));
				System.out.println("Email: " + rs.getString("email"));

			} else {

				System.out.println("Member not found!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}