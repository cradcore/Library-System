package L5Swing;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Lab5 {

	// Class variables
	private String globalURL = "jdbc:mysql://localhost:8080/cradcore";
	private String[] userpass = {"cradcore", "830311994"};

	public void readXML(String fileName) {
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("Borrowed_by");

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element sectionNode = (Element) fstNode;

					// Member ID
					NodeList memberIdElementList = sectionNode.getElementsByTagName("MemberID");
					Element memberIdElmnt = (Element) memberIdElementList.item(0);
					NodeList memberIdNodeList = memberIdElmnt.getChildNodes();
					String memID = ((Node) memberIdNodeList.item(0)).getNodeValue().trim();
					//					System.out.println("MemberID : " + memID);

					// ISBN
					NodeList secnoElementList = sectionNode.getElementsByTagName("ISBN");
					Element secnoElmnt = (Element) secnoElementList.item(0);
					NodeList secno = secnoElmnt.getChildNodes();
					String isbn = ((Node) secno.item(0)).getNodeValue().trim();
					//					System.out.println("ISBN : " + isbn);

					// Check Out
					NodeList codateElementList = sectionNode.getElementsByTagName("Checkout_date");
					Element codElmnt = (Element) codateElementList.item(0);
					NodeList cod = codElmnt.getChildNodes();
					String co = ((Node) cod.item(0)).getNodeValue().trim();
					//					System.out.println("Checkout_date : " + co);

					// Check In
					NodeList cidateElementList = sectionNode.getElementsByTagName("Checkin_date");
					Element cidElmnt = (Element) cidateElementList.item(0);
					NodeList cid = cidElmnt.getChildNodes();
					String ci = ((Node) cid.item(0)).getNodeValue().trim();
					//					System.out.println("Checkin_date : " + ci);

					// Update database
					if (co.equals("N/A"))
						checkIn(memID, isbn, co, ci);
					else if (ci.equals("N/A"))
						checkOut(memID, isbn, co, ci);


					System.out.println();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkIn(String memID, String isbn, String co, String ci) {
		System.out.println("CHECK IN!");
		String update = "UPDATE BorrowedBy SET checkinDate = \'" + ci + "\' WHERE memberID = " + memID + " AND ISBN = \'" + isbn + "\'";
		System.out.println(update);
		if(!checkData(memID, isbn, co, ci, "in")) {
			System.out.println("Book does not have a checkout date in this library! Cannot checkin");
			return;
		}
		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Update record
			stmnt.executeUpdate(update);

			con.close();
		} catch (Exception e) {
			System.out.println("ERROR Updating BorrowedBy (" + memID + ", \'" + isbn + "\', \'" + co + "\', \'" + ci + "\')");
		}
	}

	public void checkOut(String memID, String isbn, String co, String ci) {
		System.out.println("CHECK OUT!");
		String insert = "INSERT INTO BorrowedBy VALUES (" + memID + ", \'" + isbn + "\', \'" + co + "\', \'\')";
		System.out.println(insert);
		if(!(checkData(memID, isbn, co, ci, "out"))) {
			System.out.println("Book is not in this library! Cannot checkout");
			return;
		}
		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Create new record
			stmnt.executeUpdate(insert);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkData(String memID, String isbn, String co, String ci, String inOrOut) {

		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();


			// For checkins
			if (inOrOut.equals("in")) {
				try{
					rs = stmnt.executeQuery("SELECT * FROM BorrowedBy");
					while (rs.next()) {
						if(rs.getString("checkoutDate").equals("N/A") || rs.getString("checkoutDate").length() == 0) {
							System.out.println("Bad checkout date? " + rs.getString("checkoutDate"));
							return false;
						}
					}
					return true;
				}catch(Exception e){
					System.out.print(e);
				}
			}
			// For checkouts
			else {
				try{
					rs = stmnt.executeQuery("SELECT * FROM StoredOn");
					while (rs.next()) {
						if(rs.getString("ISBN").equals(isbn) && Integer.parseInt(rs.getString("totalCopies")) > 0)
							return true;
					}
					return false;
				}catch(Exception e){
					System.out.print(e);
				}
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean doesIDExist(String ID) {
		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for Member ID
			try{
				rs = stmnt.executeQuery("SELECT * FROM Member");
				while (rs.next()) 
					if(rs.getString("memberID").equals(ID))
						return true;
			}catch(Exception e){System.out.print(e);}
			con.close();
		} catch (Exception e) {e.printStackTrace();}

		return false;
	}

	public int addMember(String fn, String ln, String dob, String g) {
		String memID = "";
		for(int i = 0; i < 4; i++) {
			Random random = new Random();
			memID += Integer.toString(random.nextInt(10));
		}
		int ID = Integer.parseInt(memID);
		Connection con = null;
		try {
			Statement stmnt;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Add Member to Database
			try{
				String insertMember = "INSERT INTO Member (memberID, firstName, lastName, DOB, gender) VALUES (" + ID + ", \'" + fn + "\', \'" + ln + "\', \'" 
						+ dob + "\', \'" + g + "\')";
				System.out.println(insertMember);
				stmnt.executeUpdate(insertMember);
			}catch(Exception e){System.out.print(e);return -1;}
			con.close();
		} catch (Exception e) {e.printStackTrace();return -1;}

		return ID;

	}

	public String getMemberName(int memID) {
		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for Member ID
			try{
				rs = stmnt.executeQuery("SELECT * FROM Member");
				while (rs.next()) 
					if(rs.getString("memberID").equals(memID + ""))
						return rs.getString("firstName") + " " + rs.getString("lastName");
			}catch(Exception e){System.out.print(e);}
			con.close();
		} catch (Exception e) {e.printStackTrace();}

		return null;
	}

	public String[] search(String searchBy, String searchFor) {
		ArrayList<String> results = new ArrayList<String>();
		if (searchBy.equals("Title"))
			results = searchByTitle(searchFor);
		else if (searchBy.equals("Author"))
			results = searchByAuthor(searchFor);

		if(results.size() == 0)
			return new String[0];
		return results.toArray(new String[0]);
	}

	private ArrayList<String> searchByTitle(String searchFor) {
		ArrayList<String> results = new ArrayList<String>();

		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for Member ID
			try{
				rs = stmnt.executeQuery("SELECT * FROM Book");
				while (rs.next()) 
					if(rs.getString("title").contains(searchFor))
						results.add(rs.getString("ISBN"));
			}catch(Exception e){System.out.print(e);}
			con.close();
		} catch (Exception e) {e.printStackTrace();}

		return results;
	}

	private ArrayList<String> searchByAuthor(String searchFor) {
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		for(int i = 0; i < searchFor.length(); i++) {
			if(searchFor.charAt(i) == ' ') {
				name.add(searchFor.substring(0, i));
				name.add(searchFor.substring(i + 1, searchFor.length()));
			}
		}
		if (name.size() == 0)
			name.add(searchFor);

		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for Author ID
			String authID = "";
			try{
				rs = stmnt.executeQuery("SELECT * FROM Author");
				while (rs.next()) 
					if(name.size() == 1) {
						if (rs.getString("firstName").equals(name.get(0)) || rs.getString("lastName").equals(name.get(0))) {
							authID = rs.getString("authorID");
							break;
						}
					}
					else {
						if (rs.getString("firstName").equals(name.get(0)) || rs.getString("lastName").equals(name.get(1))) {
							authID = rs.getString("authorID");
							break;
						}
					}
			}catch(Exception e){System.out.print(e);}

			// Search for ISBN
			try{
				rs = stmnt.executeQuery("SELECT * FROM WrittenBy");
				while (rs.next()) 
					if (rs.getString("authorID").equals(authID))
						results.add(rs.getString("ISBN"));

			}catch(Exception e){System.out.print(e);}
			con.close();
		} catch (Exception e) {e.printStackTrace();}

		return results;
	}

	public ArrayList<String> getInfoFromISBN(String[] ISBNs) {
		ArrayList<String> results = new ArrayList<String>();
		Connection con = null;
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for ISBNS
			try{
				rs = stmnt.executeQuery("SELECT * FROM Book");
				while (rs.next()) 
					for(int i = 0; i < ISBNs.length; i++) {
						if(rs.getString("ISBN").equals(ISBNs[i]))
							results.add(rs.getString("ISBN") + ",    " + rs.getString("title") + ",    " + rs.getString("yearPublished"));	
					}
			}catch(Exception e){System.out.print(e);}

			con.close();
		} catch (Exception e) {e.printStackTrace();}

		return results;
	}

	public String[] searchISBN(String ISBN) {
		ArrayList<String> results = new ArrayList<String>();
		Connection con = null;
		int numCheckedOut = 0;
		int totalCopies = 0;
		
		try {
			Statement stmnt;
			ResultSet rs;

			// Register the JDBC driver for MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Define URL of database server for 'cradcore' on the faure
			String url = globalURL;

			// Get a connection to the database for cradcore
			con = DriverManager.getConnection(url, userpass[0], userpass[1]);

			// Get Statement object
			stmnt = con.createStatement();

			// Search for Member ID
			try{
				rs = stmnt.executeQuery("SELECT * FROM StoredOn");
				while (rs.next()) 
					if(rs.getString("ISBN").equals(ISBN)) {
						results.add("ISBN: " + rs.getString("ISBN") + ",    Library: " + rs.getString("libName") + ",    Shelf Number: " + rs.getString("shelfNum") + 
								",    Total copies: " + rs.getString("totalCopies"));
						totalCopies += Integer.parseInt(rs.getString("totalCopies"));
					}
			}catch(Exception e){System.out.print(e);}

			// Search for Member ID
			try{
				rs = stmnt.executeQuery("SELECT * FROM BorrowedBy");
				while (rs.next()) {
					if(rs.getString("ISBN").equals(ISBN)  && rs.getString("checkinDate").length() == 0)
						numCheckedOut++;
				}
			}catch(Exception e){System.out.print(e);}
			con.close();
		} catch (Exception e) {e.printStackTrace();}
		results.add(numCheckedOut + " copies are currently checked out");
		if(numCheckedOut >= totalCopies) 
			results.add("All copies are currently checked out, sorry!");

		return results.toArray(new String[0]);
	}
}










