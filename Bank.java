import java.util.*;
class UserDetails
{
    private  int age,user_acno;
    private double user_bal;
    private String user_fname,user_lname,user_gender,mob_no,user_pass;
    UserDetails(int user_acno,String user_fname,String user_lname,String user_gender,String mob_no,int age,double user_balance,String user_pass)
    {
        
        this.user_acno=user_acno;
        this.user_fname=user_fname;        
        this.user_lname=user_lname;
        this.user_gender=user_gender;
        this.mob_no=mob_no;
        this.age=age;
        user_bal=user_balance;
        this.user_pass=user_pass;
    }
    public String getuser_fname()
    {
        return user_fname;
    }

    public String getuser_lname()
    {
        return user_lname;
    }

    public String getuser_gender()
    {
        return user_gender;
    }

    public String getmob_no()
    {
        return mob_no;
    }

    public int getage()
    {
        return age;
    }

    public double getuser_balance()
    {
        return user_bal;
    } 

    public int getacno() //Account number
    {
        return user_acno;
    }

    public String getpass() //User Password
    {
        return user_pass;
    }
     public void deposit(double amount)
    {
       user_bal+=amount;          
    }
    public void withdraw(double amount)
   {
        if (user_bal >= amount) 
        {
            user_bal -= amount;
            System.out.println("Amount withdrawed successfully!");
        } 
        else
         {
            System.out.println("Insufficient funds!");
            user_bal = user_bal-10;
            System.out.println("Due to insufficient withdrawal amount we have deducted 10 INR from your account");

        }
    }

}

class User 
{

   // Admin ad = new Admin();
    private List<UserDetails> accounts = new ArrayList<>();
   // Scanner sc = new Scanner(System.in);
    public UserDetails findUserDetails(int acno,String tpass)
        {
        for (UserDetails account : accounts) 
        {
            if ((account.getacno()==acno)&&(account.getpass().equals(tpass)))
            {
                return account;
            }
        }
        return null;
        }

        public UserDetails searchByAcc(int acno)
        {
        for (UserDetails account : accounts) 
        {
            if (account.getacno()==acno)
            {
                return account;
            }
        }
        return null;
    }

    public void showallrec()
    {
        System.out.print("\nRecords: "+accounts.size());
        for(UserDetails acc : accounts)
        {
                                       
            System.out.print("\n-----------------------------------------------------------------\n");
            System.out.print("Account Number: "+acc.getacno()+"\nName: "+acc.getuser_fname()+" "+acc.getuser_lname()+"\nGender: "+acc.getuser_gender()+"\nAge: "+acc.getage()+"\nMobile Number: "+acc.getmob_no()+"\nAccount Balance: "+acc.getuser_balance());
            System.out.print("\n-----------------------------------------------------------------\n");
        }
    }

    public boolean delete_acc(int temp)
    {
       boolean t = accounts.removeIf(acc->acc.getacno()==temp);
       return t;
    }

    public void account_add(UserDetails account)
    {
        accounts.add(account);
    }
   
   
}

class Menu /*extends User*/{
    Scanner sc = new Scanner(System.in);
    Admin ad = new Admin();
    User u = new User();
        public  void mainMenu()
        {   
        int acno=10001;
        while (true) 
        {   
            System.out.print("\n\t\t<-------------- Welcome to JAVA BANK ---------------->\n");
            System.out.println("\n1. Open new account");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice)
             {
                case 1:
                    System.out.print("\n\n\t\t<------------   Registration Form  ------------->\n\n");
                    System.out.print("Enter first name: ");
                    String fname=sc.next();
                    fname=fname.toUpperCase();
                    System.out.print("Enter last name: ");
                    String lname=sc.next();
                    lname=lname.toUpperCase();
                    System.out.print("Enter gender: ");
                    String gender=sc.next();
                    gender=gender.toUpperCase();
                    System.out.print("Enter mobile number: ");
                    String mob=sc.next();
                    System.out.print("Enter age: ");
                    int age=sc.nextInt();
                    System.out.print("Minimum opening balance should be 500 INR\nEnter opening balance: ");
                    double opening_bal=sc.nextDouble();
                    if((gender.equals("MALE"))||(gender.equals("M"))||(gender.equals("FEMALE"))||(gender.equals("F")))
                    {
                        if(mob.length()==10)
                        {
                            if((opening_bal>=500)&&(age>=18))
                            {
                                System.out.print("Create Password: ");
                                String pass = sc.next();
                                UserDetails account = new UserDetails(acno,fname,lname,gender,mob,age,opening_bal,pass);
                                u.account_add(account);
                                //accounts.add(account);
                                System.out.println("Account opened successfully!");
                                System.out.println("\nYour Account number: "+acno);
                                acno++;
                            }
                            else
                            {
                                System.out.print("Minimum opening balance should be 500 INR or Your age should be 18 or greater");
                            }
                        }
                        else
                        {
                            System.out.print("Mobile number should have 10 digits");
                        }
                    }
                    else
                    {
                        System.out.print("Enter gender Male/Female or M/F");
                    }
                    break;

                case 2:
                    System.out.print("\n\t\t<-------------- Welcome to JAVA BANK ---------------->");
                    System.out.print("\n\n\t\t<----------------- Login page----------------->\n\n");
                    System.out.print("Enter Account number: ");
                    int tacno=sc.nextInt();
                    System.out.print("Enter password: ");
                    String tpass=sc.next();
                    UserDetails Xyz = u.findUserDetails(tacno,tpass);
                    if (Xyz != null) 
                    {
                       System.out.print("\nLogin successfully");
                       if((Xyz.getuser_gender().equals("MALE"))||(Xyz.getuser_gender().equals("M")))
                       {
                            System.out.print("\n\nWelcome Mr. "+Xyz.getuser_fname()+" "+Xyz.getuser_lname()); 
                       }
                       else
                       {
                            System.out.print("\n\nWelcome Ms./Mrs. "+Xyz.getuser_fname()+" "+Xyz.getuser_lname()); 
                       }
                       checklogin(tacno,tpass);

                       
                    } 
                    else
                    {
                        System.out.println("\nAccount not found!");
                    }            
                    break;
                case 3: 
                    String user_name,user_pass;
                    System.out.print("Enter Admin Username: ");
                    user_name=sc.next();
                    System.out.print("Enter Admin Password: ");
                    user_pass=sc.next();
                    if((user_name.equals(ad.getadmin_username()))&&(user_pass.equals(ad.getadmin_pass())))
                    {  
                       
                        while(true)
                       {
                            System.out.print("\n\t\tAdmin Menu");
                            System.out.print("\n\t<-------------------------->\n");
                            System.out.println("\n1. Show All Records");
                            System.out.println("2. Search Account");
                            System.out.println("3. Delete Account");
                            System.out.println("4. Exit");
                            System.out.print("Enter your choice: ");
                            int option = sc.nextInt();
                            if (option<=4) {
                                if(option==1){
                                    u.showallrec();
                                    /*System.out.print("\nRecords: "+accounts.size());
                                    for(UserDetails acc : accounts)
                                    {
                                       
                                        System.out.print("\n-----------------------------------------------------------------\n");
                                        System.out.print("Account Number: "+acc.getacno()+"\nName: "+acc.getuser_fname()+" "+acc.getuser_lname()+"\nGender: "+acc.getuser_gender()+"\nAge: "+acc.getage()+"\nMobile Number: "+acc.getmob_no()+"\nAccount Balance: "+acc.getuser_balance());
                                        System.out.print("\n-----------------------------------------------------------------\n");
                                    }*/
                                }
                            
                                else if(option==2){
                                    int temp_acno;
                                    System.out.print("\nEnter account number: ");
                                    temp_acno=sc.nextInt();
                                    UserDetails searchacc = u.searchByAcc(temp_acno);
                                    if (searchacc != null) 
                                    {
                                        System.out.print("\n-----------------------------------------------------------------\n");
                                        System.out.print("Account Number: "+searchacc.getacno()+"\nName: "+searchacc.getuser_fname()+" "+searchacc.getuser_lname()+"\nGender: "+searchacc.getuser_gender()+"\nAge: "+searchacc.getage()+"\nMobile Number: "+searchacc.getmob_no()+"\nAccount Balance: "+searchacc.getuser_balance());
                                        System.out.print("\n-----------------------------------------------------------------\n");

                                    } 
                                    else
                                    {
                                        System.out.print("Account not found!");
                                    }    
                                }

                                else if(option==3){
                                    System.out.print("\nEnter account number: ");
                                    int temp = sc.nextInt();
                                    boolean t = u.delete_acc(temp);
                                    //accounts.removeIf(acc->acc.getacno()==temp);
                                    if(t==true)
                                        System.out.print("Deleted Successfully");
                                    else
                                        System.out.print("Account Not Found!");
                                }
                                else if(option==4){ 
                                    break;   
                                }   
                            }
                            else
                            {
                                System.out.print("Entered Wrong Choice");
                            }

                       }
                       
                    }
                    else
                    {
                        System.out.print("\nWrong Admin Username or Admin Password");
                    }
                    
                    break;

                case 4:
                    System.out.println("Exiting the banking system.");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

     public  void checklogin(int acno,String l_mob)
    { 
        while (true)
        {
            System.out.println("\n\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Logout");
            System.out.print("Select an option: ");

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) 
            {
                case 1:
                    System.out.print("\n\t\t<-------------- Welcome to JAVA BANK ---------------->");
                    System.out.print("\n\t\t<--------For depositng the money fill the following details-------->\n");
                    UserDetails Xyz = u.findUserDetails(acno,l_mob);
                    if (Xyz != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        Xyz.deposit(depositAmount);
                        System.out.println("Amount deposited successfully!");
                    } else 
                    {
                        System.out.println("Account not found!");
                    }
                    break;

                case 2:
                     System.out.print("\n\t\t<-------------- Welcome to JAVA BANK ---------------->"); 
                     System.out.print("\n\t\t<--------For withdrawing the money fill the following details-------->\n");
                    UserDetails withdrawAccount = u.findUserDetails(acno,l_mob);
                    if (withdrawAccount != null) 
                    {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                       
                    } 
                    else 
                    {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                     System.out.print("\n\t\t<-------------- Welcome to JAVA BANK ---------------->");
                     System.out.print("\n\t\t<--------For checking the balance fill the following details-------->\n");
                    UserDetails checkBalanceAccount = u.findUserDetails(acno,l_mob);
                    if (checkBalanceAccount != null)
                     {
                        System.out.println("Account Holder: " + checkBalanceAccount.getuser_fname()+" "+checkBalanceAccount.getuser_lname());
                        System.out.println("Balance: " + checkBalanceAccount.getuser_balance());
                    } else
                     {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the login system.");
                    return;
                    
                 default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }
}

class Admin{ 
    private static String admin_username="ADMIN";
    private static String admin_pass="@Admin2600";  
    public String getadmin_username()
    {
        return admin_username;
    }

    public String getadmin_pass()
    {
        return admin_pass;
    }    
}

class BankSys
{
    public static void main(String[] args) {
        Menu m = new Menu();
        m.mainMenu();
    }
}