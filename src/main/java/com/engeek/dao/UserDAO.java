package com.engeek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.engeek.logic.Constant;
import com.engeek.model.User;



public class UserDAO {

	public static List<User> getUserByUserName(String userName) {
		String sql = "SELECT * from User where userName = '" + userName  + "'";
		return getUser(sql);
	}
	
	public static User resultToUser(ResultSet rs) {
		User User = new User();
		try {
			User.setId(rs.getInt("id"));
			User.setUserName(rs.getString("userName"));
			User.setPassWord(rs.getString("passWord"));
			User.setName(rs.getString("Name"));
			User.setType(rs.getInt("type"));
			User.setHint(rs.getInt("hint"));
			User.setInvitedFriends(rs.getInt("invitedFriends"));
			
			User.setGender(rs.getString("gender"));
			User.setAvatarUrl(rs.getString("avatarUrl"));
			User.setGoogle(rs.getString("google"));
			
			User.setCreated(rs.getTimestamp("created"));
			User.setPaymentTime(rs.getTimestamp("paymentTime"));
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return User;
	}
	
	public static List<User> getUser(String sql) {
		List<User> result = new ArrayList<User>();
		Connection conn = null;
		Statement stmt = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
            stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                User User = resultToUser(rs);
                result.add(User);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
        return result;
    }
	
	public static boolean updateUserProfile(String userName, 
			String name, String gender, String google, String facebook, String avatarUrl,
			int type, int hint, int invitedFriends) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
			stmt = conn.createStatement();
			

			String sql = "UPDATE "
					+ " User SET"
					
					
					+ " name = '" + name + "', "
					+ " gender = '" + gender + "', "
					+ " google = '" + google + "', "
					+ " facebook = '" + facebook + "', "
					+ " avatarUrl = '" + avatarUrl + "', "
					
					+ " type = " + type + ", "
					+ " hint = " + hint + ", "
					+ " invitedFriends = " + invitedFriends + " "
					
					+ " where userName = '" + userName + "'";
			
		    stmt.executeUpdate(sql);
			
		    return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static void updateUserAccount(User u) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
			stmt = conn.createStatement();
			

			String sql = "UPDATE "
					+ " User SET"
					
					+ " name = '" + u.getName() + "', "
					+ " passWord = '" + u.getPassWord() + "', "
					+ " name = '" + u.getName() + "', "
					+ " gender = " + u.getGender() + ", "
					+ " avatarUrl = '" + u.getAvatarUrl() + "', "
					+ " google = '" + u.getGoogle() + "', "
					+ " facebook = '" + u.getFacebook() + "', "
					
					+ " type = " + u.getType() + ", "
					+ " hint = " + u.getHint() + ", "
					+ " invitedFriends = " + u.getInvitedFriends() + " "
					
					+ " where userName = '" + u.getUserName() + "'";
			
		    stmt.executeUpdate(sql);
			

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		
	}
	
	
	public static boolean updatePaymentStatus(String userName, int paymentStatus) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
			stmt = conn.createStatement();

			String sql = "UPDATE "
					+ " User SET"
					+ " paymentStatus = " + paymentStatus + ", "
					+ " paymentTime = '" + (new Timestamp(System.currentTimeMillis())).toString() + "', "
					+ " where userName = '" + userName + "'";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static boolean updateUserPassword(String userName, String passWord) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
			stmt = conn.createStatement();

			String sql = "UPDATE "
					+ " User SET"
					+ " passWord = '" + passWord + "' "
					+ " where userName = '" + userName + "'";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return false;
	}
	
	public static boolean createUserAccount(User u) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, Constant.USER, Constant.PASS);
			stmt = conn.createStatement();

			String sql = "INSERT INTO "
					+ " User(userName, passWord)"
					+ " VALUES ("
					+ " '" + u.getUserName() + "', "
					+ " '" + u.getPassWord() + "' "
					+ ");";
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return false;
	}

}
