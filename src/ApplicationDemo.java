import java.sql.*;
import java.util.Scanner;

public class ApplicationDemo {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Connection c;
        Statement s;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/application","root","dsp0805");
            s = c.createStatement();
            PreparedStatement p = c.prepareStatement("INSERT INTO bank VALUES(?,?,?,?,?)");
            PreparedStatement pDel = c.prepareStatement("DELETE From bank WHERE AccountNo = ?");
            PreparedStatement pName = c.prepareStatement("UPDATE bank SET CustomerName = ? Where AccountNo = ?");
            PreparedStatement pBal = c.prepareStatement("UPDATE bank SET Balance = ? Where AccountNo = ?");
            PreparedStatement pPhn = c.prepareStatement("UPDATE bank SET Phone = ? Where AccountNo = ?");
            PreparedStatement pAdd = c.prepareStatement("UPDATE bank SET Address = ? Where AccountNo = ?");
            ResultSet r;

            boolean flag = true;
            while(flag) {
                System.out.println("\n***********************************************************************************");
                System.out.println("view - 1 , insertion - 2 , Deletion - 3 , Updation - 4 , Exit - 5");
                System.out.print("Enter your choice: ");
                int value = scn.nextInt();
                switch (value) {
                    case 1://veiwn successfull
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.format("%10s %10s %10s %10s %10s" , "Account" , "Name" , "Balance" , "Phone" , "Address\n");
                        System.out.println("--------------------------------------------------------------------------------");
                        r = s.executeQuery("select * from bank");
                        while (r.next())
                        {
                            System.out.format("%10s %10s %10s %10s %10s",r.getString(1) , r.getString(2) , r.getString(3) , r.getString(4) , r.getString(5));
                            System.out.println();
                        }
                        r.close();
                        System.out.println("--------------------------------------------------------------------------------");
                        break;
                    case 2://insertion successfull
                        System.out.print("Enter Account no: ");
                        int acn = scn.nextInt();
                        p.setInt(1,acn);
                        System.out.print("Enter Name: ");
                        String name = scn.nextLine();
                        name = scn.nextLine();
                        p.setString(2,name);
                        System.out.print("Enter Balance: ");
                        int balance = scn.nextInt();
                        p.setInt(3,balance);
                        System.out.print("Enter Phone no: ");
                        int phone = scn.nextInt();
                        p.setInt(4,phone);
                        System.out.print("enter Address: ");
                        String add = scn.nextLine();
                        add = scn.nextLine();
                        p.setString(5,add);
                        int result = p.executeUpdate();
                        if(result == 0)
                            System.out.println("Values are not inserted!!!");
                        break;
                    case 3://deletion successfull
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.format("%10s %10s %10s %10s %10s" , "Account" , "Name" , "Balance" , "Phone" , "Address\n");
                        System.out.println("--------------------------------------------------------------------------------");
                        r = s.executeQuery("select * from bank");
                        while (r.next())
                        {
                            System.out.format("%10s %10s %10s %10s %10s",r.getString(1) , r.getString(2) , r.getString(3) , r.getString(4) , r.getString(5));
                            System.out.println();
                        }
                        System.out.println("--------------------------------------------------------------------------------");
                        System.out.println("To Delete a Row");
                        System.out.print("Enter Account no: ");
                        acn = scn.nextInt();
                        pDel.setInt(1,acn);
                        pDel.executeUpdate();
                        break;
                    case 4://updation of data
                        System.out.println("To Update");
                        System.out.println("Name - 1 Balance - 2 Phone - 3 Address - 4");
                        System.out.print("Enter Choice: ");
                        int choice = scn.nextInt();
                        System.out.print("Enter Account no: ");
                        acn = scn.nextInt();
                        switch (choice)
                        {
                            case 1 :
                                System.out.print("New Name: ");
                                name = scn.nextLine();
                                name = scn.nextLine();
                                pName.setString(1,name);
                                pName.setInt(2,acn);
                                pName.executeUpdate();
                                break;
                            case 2 :
                                System.out.print("Balance: ");
                                balance = scn.nextInt();
                                pBal.setInt(1,balance);
                                pBal.setInt(2,acn);
                                pBal.executeUpdate();
                                break;
                            case 3 :
                                System.out.print("Phone: ");
                                phone = scn.nextInt();
                                pPhn.setInt(1,phone);
                                pPhn.setInt(2,acn);
                                pPhn.executeUpdate();
                                break;
                            case 4 :
                                System.out.print("Address: ");
                                add = scn.nextLine();
                                add = scn.nextLine();
                                pAdd.setString(1,add);
                                pAdd.setInt(2,acn);
                                pAdd.executeUpdate();
                                break;
                            default:
                                System.out.println("Wrong Choice");
                        }
                        System.out.println("hr");
                        break;
                    case 5://exit successful
                        flag = false;
                        break;
                    default:
                        System.out.println("Wrong Choice!!");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
