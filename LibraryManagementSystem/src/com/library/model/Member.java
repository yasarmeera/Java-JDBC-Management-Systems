package com.library.model;

public class Member {

	private int memberId;
	private String memberName;
	private String phone;
	private String email;

	public Member(int memberId, String memberName, String phone, String email) {

		this.memberId = memberId;
		this.memberName = memberName;
		this.phone = phone;
		this.email = email;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
}