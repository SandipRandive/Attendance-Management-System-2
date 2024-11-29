import java.sql.*;
import java.util.Scanner;

public class AttendanceManagementSystem {
// Database connection details
private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/attendance_management";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "sandip.1418sql";

// Database connection method
private static Connection getConnection() throws SQLException {
return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
}
// Add a new student
public static void addStudent(String name, String email) {
String query = "INSERT INTO students (name, email) VALUES (?, ?)";
try (Connection conn = getConnection(); PreparedStatement pstmt =
conn.prepareStatement(query)) {
pstmt.setString(1, name);
pstmt.setString(2, email);
pstmt.executeUpdate();
System.out.println("Student added successfully.");
} catch (SQLException e) {
System.out.println("Error adding student: " + e.getMessage());
}
}

// Mark attendance
public static void markAttendance(int studentId, String date, String status) {
String query = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";
try (Connection conn = getConnection(); PreparedStatement pstmt =
conn.prepareStatement(query)) {
pstmt.setInt(1, studentId);
pstmt.setDate(2, Date.valueOf(date));
pstmt.setString(3, status);
pstmt.executeUpdate();
System.out.println("Attendance marked successfully.");
} catch (SQLException e) {
System.out.println("Error marking attendance: " + e.getMessage());
}
}

// View attendance records
public static void viewAttendance(int studentId) {
String query = "SELECT * FROM attendance WHERE student_id = ?";
try (Connection conn = getConnection(); PreparedStatement pstmt =
conn.prepareStatement(query)) {
pstmt.setInt(1, studentId);
ResultSet rs = pstmt.executeQuery();
while (rs.next()) {
System.out.printf("Date: %s, Status: %s%n", rs.getDate("date"), rs.getString("status"));
}
} catch (SQLException e) {
System.out.println("Error retrieving attendance: " + e.getMessage());
}

}
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

while (true) {
System.out.println("\n--- Attendance Management System ---");
System.out.println("1. Add Student");
System.out.println("2. Mark Attendance");
System.out.println("3. View Attendance");
System.out.println("4. Exit");
System.out.print("Choose an option: ");

int choice = scanner.nextInt();
scanner.nextLine(); // Consume newline

switch (choice) {
case 1:
System.out.print("Enter name: ");
String name = scanner.nextLine();
System.out.print("Enter email: ");
String email = scanner.nextLine();
addStudent(name, email);
break;

case 2:
System.out.print("Enter student ID: ");
int studentId = scanner.nextInt();

scanner.nextLine(); // Consume newline
System.out.print("Enter date (YYYY-MM-DD): ");
String date = scanner.nextLine();
System.out.print("Enter status (Present/Absent): ");
String status = scanner.nextLine();
markAttendance(studentId, date, status);
break;
case 3:
System.out.print("Enter student ID: ");
int id = scanner.nextInt();
viewAttendance(id);
break;

case 4:
System.out.println("Exiting...");
scanner.close();
return;

default:
System.out.println("Invalid choice. Try again.");
}
}
}
}