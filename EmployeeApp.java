import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeApp {
    // Database connection details
    static final String URL = "jdbc:mysql://localhost:3306/company_db";
    static final String USER = "root";
    static final String PASSWORD = "yourpassword";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connected to database!");

            do {
                System.out.println("\n=== Employee Database App ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        addEmployee(conn, sc);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        updateEmployee(conn, sc);
                        break;
                    case 4:
                        deleteEmployee(conn, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    // 1️⃣ Add Employee
    static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, salary);

        int rows = ps.executeUpdate();
        System.out.println(rows + " employee(s) added.");
        ps.close();
    }

    // 2️⃣ View Employees
    static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employee";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Employee List ---");
        while (rs.next()) {
            System.out.printf("%d | %s | %s | %.2f\n",
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("department"),
                rs.getDouble("salary"));
        }
        rs.close();
        stmt.close();
    }

    // 3️⃣ Update Employee
    static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        System.out.print("Enter new department: ");
        String dept = sc.nextLine();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employee SET department = ?, salary = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dept);
        ps.setDouble(2, salary);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();
        System.out.println(rows + " employee(s) updated.");
        ps.close();
    }

    // 4️⃣ Delete Employee
    static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        System.out.println(rows + " employee(s) deleted.");
        ps.close();
    }
}
