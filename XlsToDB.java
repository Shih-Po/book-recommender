package books;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class XlsToDB {
	public static void main(String[] args) throws BiffException, IOException {
		try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://miragechung.ddns.net/mirage?booklist",
				"book", "book");

		) {
			PreparedStatement pstmt = conn
					.prepareStatement("insert into booklist values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			String path = "d:/booklist/bookrep.xls";
			InputStream input = new FileInputStream(path);
			Workbook book = Workbook.getWorkbook(input);

			for (int n = 0; n < book.getNumberOfSheets(); n++) {
				System.out.println("list:" + n);
				Sheet st = book.getSheet(n);
				int len = st.getRows();
				for (int a = 1; a < len; a++) {
					System.out.println(len);
					Cell[] cont = st.getRow(a);
					if (cont.length > 11) {
						System.out.println("row:" + a);
						System.out.println(cont[3].getContents());
						pstmt.setString(1, cont[0].getContents());
						pstmt.setString(2, cont[1].getContents());
						pstmt.setString(3, cont[2].getContents());
						pstmt.setString(4, cont[3].getContents());
						pstmt.setString(5, cont[4].getContents());
						pstmt.setString(6, cont[5].getContents());
						pstmt.setString(7, cont[6].getContents());
						pstmt.setString(8, cont[7].getContents());
						pstmt.setString(9, cont[8].getContents());
						pstmt.setString(10, cont[9].getContents());
						pstmt.setString(11, cont[10].getContents());
						pstmt.setString(12, cont[11].getContents());
						pstmt.setString(14, st.getName().trim());
						if (cont.length < 13) {
							pstmt.setString(13, "no");
							pstmt.addBatch();
						} else if (cont.length == 13) {
							pstmt.setString(13, cont[12].getContents());
							pstmt.addBatch();
						}
					}
				}

				pstmt.executeBatch();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
