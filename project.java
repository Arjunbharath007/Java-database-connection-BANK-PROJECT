package array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class project {
 public static void main(String[] args) {
	 while (true) {
         System.out.println("1.customer");
         System.out.println("2.New customer Signin");
         System.out.println("3.Exit");
         System.out.println();
         Scanner sc = new Scanner(System.in);
         int a;
         a = sc.nextInt();
         switch (a) {
         case 1:
        	 customerlogin();
        	 break;
         case 2: 
         {
             Scanner scc = new Scanner(System.in);
             System.out.println("WELCOME CREATE ACCOUNT");
             System.out.println("_______________________");
             System.out.println("enter your name");
             String name = scc.next();
             System.out.println("enter your password");
             String pass = scc.next();
             System.out.println("enter your email");
             String email = scc.next();
             try 
             {
             	Class.forName("com.mysql.cj.jdbc.Driver");
 				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj","root","root");
                String sql = "insert into details(name,email,password)values('" + name + "','" + email + "','" + pass + "')";
                PreparedStatement stmt = con.prepareStatement(sql);

                 int i = stmt.executeUpdate();
                 if (i > 0)
                 {
     				String o="select max(id) from details" ; 
     				PreparedStatement stmt1=con.prepareStatement(o);
     				ResultSet rs=stmt1.executeQuery();
     				while(rs.next())
     				{
     					String mid= rs.getString("max(id)");
//     					String n="0";
//     					String p="p";
//     					,'"+name+"','"+pass+"'
     					int amount = 0;
     					String sql2="insert into bank(maxid,amount)values('"+mid+"','"+amount+"')";
     					PreparedStatement stmt2=con.prepareStatement(sql2);
     					int z=stmt2.executeUpdate();
     					if(z>0)
     					{
     					   System.out.println("data added");
     					}
     				}
                 } 
                 else 
                 {
                     System.out.println("Failed to insert user data.");
                 }

                 stmt.close();
                 con.close();
             } 
             catch (Exception e)
             {
                System.out.println(e);
             }
             break;
         }
         case 3:
        	 System.out.println("Exited Sucessfully");
        	 return ;
         default :
         System.out.println(a+" is not in the option");
         }
	 }
}
 private static void customerlogin()
 {
     Scanner scc = new Scanner(System.in);
     System.out.println("enter your name");
     String name = scc.next();
     System.out.println("enter your password");
     String pass = scc.next();
     try {
    	 
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "root");
         String sql = "SELECT * FROM details WHERE name = '" + name + "' AND password = '" + pass + "'";
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         if (rs.next())
         {
         	String ss = rs.getString("id");
         	
             while (true) 
             {
                 System.out.println("ACCOUNT DETAILS");
                 System.out.println("a) Amount Deposit");
                 System.out.println("b) Amount Withdrawal");
                 System.out.println("c) Check Balance");
                 System.out.println("d) Exit");
                 System.out.println();
                 char input = scc.next().charAt(0);
                 switch (input) 
                 {
                     case 'a': 
                     {
                    	 Scanner scan =new Scanner(System.in);
                    	 System.out.println("Enter the amount :");
                    	 int amount = scan.nextInt();
                    	 try {  
                    		    Class.forName("com.mysql.cj.jdbc.Driver");
                    		    Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "root");
                    		    String sqlw = "SELECT amount FROM bank where maxid = '" + ss + "'";
                    		    PreparedStatement stmte = coon.prepareStatement(sqlw);
                    		    ResultSet rs3 = stmte.executeQuery();
                    		   
                    		    
                    		    if (rs3.next()) {
                    		       int balance = rs3.getInt("amount");
                    		       int newamount = balance+amount;
                    		       String sqlq = "UPDATE bank SET amount ='" + newamount + "' where maxid = '" + ss + "'";
                    		       PreparedStatement stmtUpdate = coon.prepareStatement(sqlq);
                    		       int r = stmtUpdate.executeUpdate();

                    		       if (r > 0) {
                    		           System.out.println("Amount Added");
                    		         
                    		       } else {
                    		           System.out.println("No records updated for maxid: " + ss);
                    		       }

                    		       stmtUpdate.close();
                    		       coon.close();

                    		    } else {
                    		        System.out.println("No records found for maxid: " + ss);
                    		    }

                    		    rs3.close();
                    		    stmte.close();
                    		    coon.close();
                    		} catch (Exception e) {
                    		    e.printStackTrace(); 
                    		}

                         break;
                     }
                     case 'b':
                     {
                     	 Scanner scan =new Scanner(System.in);
                    	 System.out.println("Enter the amount :");
                    	 int amount = scan.nextInt();
                    	 try {  
                    		    Class.forName("com.mysql.cj.jdbc.Driver");
                    		    Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "root");
                    		    String sqlw = "SELECT amount FROM bank where maxid = '" + ss + "'";
                    		    PreparedStatement stmte = coon.prepareStatement(sqlw);
                    		    ResultSet rs3 = stmte.executeQuery();
                    		   
                    		    
                    		    if (rs3.next()) {
                    		       int balance = rs3.getInt("amount");
                    		       if (amount <= balance) {
                    		       int newamount = balance-amount;
                    		       String sqlq = "UPDATE bank SET amount ='" + newamount + "' where maxid = '" + ss + "'";
                    		       PreparedStatement stmtUpdate = coon.prepareStatement(sqlq);
                    		       int rowsAffected = stmtUpdate.executeUpdate();

                    		       if (rowsAffected > 0) {
                    		           System.out.println("Amount ");
                    		           System.out.println();
                    		       } else {
                    		           System.out.println("No records updated for maxid: " + ss);
                    		       }

                    		       stmtUpdate.close();
                    		       coon.close();
                    		       } else {
                                       System.out.println("Insufficient balance. Cannot withdraw.");
                                   }
                    		    } else {
                    		        System.out.println("No records found for maxid: " + ss);
                    		    }

                    		    rs3.close();
                    		    stmte.close();
                    		    coon.close();
                    		} catch (Exception e) {
                    		    e.printStackTrace(); 
                    		}
                         break;
                     }
                     case 'c':
                     {
                     
                    	 try {
                    		    Class.forName("com.mysql.cj.jdbc.Driver");
                    		    Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "root");
                    		    String sqlw = "SELECT amount FROM bank where maxid = '" + ss + "'";
                    		    PreparedStatement stmte = coon.prepareStatement(sqlw);
                    		    ResultSet rs3 = stmte.executeQuery();

                    		    if (rs3.next()) {
                    		        int balance = rs3.getInt("amount");
                    		        System.out.println("Account balance: " + balance);
                    		        System.out.println();
                    		    } else {
                    		        System.out.println("No records found for maxid: " + ss);
                    		    }

                    		    rs3.close();
                    		    stmte.close();
                    		    coon.close();
                    		} catch (Exception e) {
                    		    e.printStackTrace(); 
                    		}

                         break;
                     }
                     case 'd': {
                         System.out.println("Exited");
                         return 
                         		;
                     }
                     default:
                         System.out.println("Wrong choice");
                 }
             }
         } else {
             System.out.println("Invalid name or password");
         }

         rs.close();
         stmt.close();
         con.close();
     } catch (Exception e) {
         System.out.println(e);
     }

  
 }

}