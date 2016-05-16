package books;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorList {
	public static void main(String[] args) {
		try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://miragechung.ddns.net/book?", "book",
				"book");
				java.sql.Statement st = conn.createStatement();
				java.sql.Statement st2 = conn.createStatement();
				java.sql.Statement st3 = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from booklist");) {
			st2.execute("create table if not exists author (name varchar(200),bookname varchar(200)) engine = InnoDB");
			java.sql.PreparedStatement pstmt = conn.prepareStatement("replace into author values (?,?)");
			while (rs.next()) {
				String bookname = rs.getString("bookname");
				String allauthors = rs.getString("author").replaceAll("\\s", "");
				String authors="";
				if(allauthors.contains("‧")){
					authors = allauthors.replaceAll("‧", "．");
				}
				else if(allauthors.contains("•")){
					authors = allauthors.replaceAll("•", "．");
				}
				else{
					authors = allauthors;
				}
				if(authors.contains("、")){
				String[] author = authors.split("、");
				for (String name : author) {
					if (name.length() > 1) {
						pstmt.setString(1, name);
						pstmt.setString(2, bookname);
						pstmt.addBatch();
					}
				}
				}				
				else if(authors.contains("\\+")){
					String[] author = authors.split("\\+");
					for (String name : author) {
						if (name.length() > 1) {
							pstmt.setString(1, name);
							pstmt.setString(2, bookname);
							pstmt.addBatch();
						}
					}
				}
				else if(authors.contains("\\.")){
					String[] author = authors.split("\\.");
					for (String name : author) {
						if (name.length() > 1) {
							pstmt.setString(1, name);
							pstmt.setString(2, bookname);
							pstmt.addBatch();
						}
					}
				}
				else if(authors.contains("&")){
					String[] author = authors.split("&");
					for (String name : author) {
						if (name.length() > 1) {
							pstmt.setString(1, name);
							pstmt.setString(2, bookname);
							pstmt.addBatch();
						}
					}
				}
				else if(authors.contains("/")){
					String[] author = authors.split("/");
					for (String name : author) {
						if (name.length() > 1) {
							pstmt.setString(1, name);
							pstmt.setString(2, bookname);
							pstmt.addBatch();
						}
					}
				}
				else{
					pstmt.setString(1, authors);
					pstmt.setString(2, bookname);
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			System.out.println("finish");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
