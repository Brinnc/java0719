package org.sp.app0719.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//오직 member2테이블에 대한 crud를 담당하는 객체
public class Member2DAO {
	String url = "jdbc:mysql://localhost:3306/javase?characterEncoding=utf8";
	String user = "root";
	String pw = "1234";

	// 레코드 1건 등록
	public int insert(Member2 member2) {

		Connection con = null; // 접속 성공 후, 그 정보를 가진 객체, 따라서 접속을 해제할 때 이 객체를 이용할 수 있음
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			// 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");

			// 접속
			con = DriverManager.getConnection(url, user, pw);

			if (con == null) {
				System.out.println("실패");
			} else {
				System.out.println("성공");
				String sql = "insert into member2(id, pass, name, email)";
				sql += " values('" + member2.getId() + "','" + member2.getPass() + "','" + member2.getName() + "','"
						+ member2.getEmail() + "')";
				// System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				result = pstmt.executeUpdate(); // 쿼리 실행

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 이메서드를 호출한자가 그 결과를 알수있도록 반환하자
		return result;
	}

	// 모든 레코드 가져오기
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member2> list = new ArrayList<Member2>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);
			String sql = "select * from member2";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 쿼리 실행 및 표반환

			// rs는 곧 닫히므로, rs를 대체할 dto와 list를 이용해 데이터를 옮겨담자
			while (rs.next()) { // 레코드가 존재하는 만큼
				Member2 dto = new Member2();
				dto.setMember2_idx(rs.getInt("member2_idx"));
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));

				list.add(dto);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	// 1사람에 대한 정보 가져오기
	public Member2 login(Member2 member2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member2 dto = null;

		try {
			// 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");

			// 접속
			con = DriverManager.getConnection(url, user, pw);
			if (con == null) {
				System.out.println("접속 실패");
			} else {
				System.out.println("성공");

				String sql = "select * from member2";
				// sql+=" where id='"+member2.getId()+"' and pass='"+member2.getPass();
				sql += " where id='" + member2.getId() + "' and pass='" + member2.getPass() + "'";

				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				// 로그인 성공 시 레코드는 언제나 1건이므로,
				// rs.next() 메서드 호출 시 true가 반환될지 false가 반환될지에 따라서 로그인 성공 실패 여부를 따져봄
				if (rs.next()) {
					// 성공
					dto = new Member2();
					// 텅 빈 dto에 로그인 성공한 회원의 정보를 옯겨심자
					dto.setMember2_idx(rs.getInt("member2_idx"));
					dto.setId(rs.getString("id"));
					dto.setPass(rs.getString("pass"));
					dto.setName(rs.getString("name"));
					dto.setEmail(rs.getString("email"));

				}

				// System.out.println(sql);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// 1건 수정
	public void update() {

	}

	// 1건 삭제
	public void delete() {

	}
}
