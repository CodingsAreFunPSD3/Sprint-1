import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
/**
 *
 *
 */
public class psd3 {
    public static void main(String[] args)throws IOException{
		login();
}

private static void login () throws IOException {
	 Scanner s1,s2,s3;
	        s1=new Scanner(new FileInputStream("password.txt"));
	        s2=new Scanner(System.in);
	        s3=new Scanner(System.in);
	        boolean flag=false;
	        String name,pword,role, n,p ;
	        System.out.println("Enter quit to exit");
	        System.out.println("Enter name:");
	        n=s2.next();
	        if(n.equals("quit")){
				System.exit(0);
			}
	        System.out.println("Enter password:");
        p=s2.next();
	 while(s1.hasNext()) {
	            name=s1.next();
	            pword=s1.next();
	            role=s1.next();
	            if(n.equals(name) && p.equals(pword) && role.equals("admin")) {
	                System.out.println("You are logged in as "+ name + " (admin).");
	             flag=true;
	             admin();
			 }
	             else if (n.equals(name) && p.equals(pword) && role.equals("tutor")) {
	             System.out.println("You are logged in as "+ name + " (tutor).");
	             flag=true;
	             tutor();

			}
	        }
	        if(!flag)
        System.out.println("Incorrect password.");
        login();
}


private static void admin() throws IOException{

	String choice;
	Scanner in = new Scanner(System.in);
	System.out.println("What do you want to do?\nPress 1 to export all student grade to CSV file.\nPress 2 to export all grade for a single student.\nPress 3 to Logout and return to Login page.");
	choice = in.nextLine();
	int choose =Integer.parseInt(choice);
	switch (choose){
		case 1:
				System.out.println("Course name");
                String assignmentName = in.nextLine();
			   generateCsvFile(assignmentName+".csv");
			   System.out.println("File exported as "+assignmentName+".csv");
			   admin();
	    case 2:
				System.out.println("Enter output file name");
                String Student = in.nextLine();
	    	genertatetranscipt(Student+".txt");
	    				   System.out.println("Transcript Created as "+ Student+".txt");
			   admin();
	   	case 3:
	   		login();
	   	   default:
	   	   System.out.println("Wrong input");
	   	   admin();
	      }
}
private static void generateCsvFile(String sFileName)
   {
	try
	{

                FileWriter writer = new FileWriter(sFileName);
                Scanner in = new Scanner(System.in);
                writer.append('\n');
                writer.append("StudentName");
                writer.append(',');
                System.out.println("Enter number of Assignment");
                String noOfAssignment = in.nextLine();
                int noOfAssignmentint =Integer.parseInt(noOfAssignment);

                for (int i=0;i<noOfAssignmentint;i++){
                int o = noOfAssignmentint-i;
				int a=i+1;
                writer.append("Assignment" + a +',');
                }

                writer.append("Average grade");
                writer.append(',');
                writer.append('\n');
                System.out.println("Number of student");
                String noOfStudent = in.nextLine();
                int noOfStudentint =Integer.parseInt(noOfStudent);
               for (int i=0;i<noOfStudentint;i++){
                int o = noOfStudentint-i;
                System.out.println("Number of student remaining: "+o);
                System.out.println("Enter Student Name");
                String name = in.nextLine();
                writer.append(name);
                writer.append(',');
                int[] avgGrade = new int[2000];
                for(int e=0;e<noOfAssignmentint;e++){
                    int a= e+1;
                    System.out.println("Enter Student grade");
                    System.out.println("for Assignment "+a+": ");
                    String grade = in.nextLine();
                    int gradeint =Integer.parseInt(grade);
                    writer.append(grade);
                    writer.append(',');
                    avgGrade[e]=gradeint;
               }
                String avgGraded;
                int avg=0;
                for(int e=0;e<noOfAssignmentint;e++){
                avg = avg+(int)avgGrade[e];
                }
                avgGraded = ""+avg/noOfAssignmentint;
                writer.append(avgGraded);
                writer.append(',');
                writer.append('\n');
               }

	    writer.flush();
	    writer.close();
	}
	catch(IOException e)
	{
	     e.printStackTrace();
	}
    }



private static void genertatetranscipt(String sFileName){

	BufferedReader br = null;
	String line = "";
	Scanner in = new Scanner(System.in);
	try {
		FileWriter writer = new FileWriter(sFileName);
		System.out.println("Enter Student Name");
		String Student= in.nextLine();
		writer.append("Name: "+Student+"\n");
		writer.append("Course"+"\t\t\tGrade"+"\n");
		System.out.println("How many course he take?");
		String amount=in.nextLine();
		int am =Integer.parseInt(amount);

		for (int c=0;c<am;c++){
			System.out.println("What course he take?");
			String Course= in.nextLine();
			br = new BufferedReader(new FileReader(Course+".csv"));


			while ((line = br.readLine()) != null) {

					String cvsSplitBy = ",";
					String[] csv = line.split(cvsSplitBy);
					int csvlength = csv.length;
					int b= csvlength - 1;
					String avg = "Average grade";

			if(csv[0].equals(Student)){
				writer.append(Course+"\t\t\t"+ csv[b]+"\n");
			}
			}
			}
			writer.flush();
			writer.close();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

  }

public static void tutor() throws IOException{

	String choice;
	Scanner in = new Scanner(System.in);
	System.out.println("What do you want to do?\nPress 1 to mark attendant.\nPress 2 to edit attendant.\nPress 3 to view attendant.\nPress 4 to Logout and return to Login page.");
	choice = in.nextLine();
	int choose =Integer.parseInt(choice);
	switch (choose){
		case 1:
				System.out.println("Which week?");
            String getWeek = in.nextLine();
	markattend("Attendance for Week "+ getWeek+".csv");
			   System.out.println("attendant have been updated");
			   tutor();
	    case 2:
                                System.out.println("Edit Attendance for week : ");
                                        Scanner scanWeek = new Scanner(System.in);
                                                  Scanner scanner = new Scanner(new File("getNames.csv"));
							getWeek = scanWeek.next();
                                editCsvFile("Attendance for Week "+ getWeek+".csv");
                                scanner.close();

			   tutor();
			    case 3:
			                                   System.out.println("View which week : ");
			                                         Scanner Week = new Scanner(System.in);

			                                       Scanner sc = new Scanner(new File("getNames.csv"));
			   					String eWeek = Week.next();
			                                   viewCsvFile("Attendance for Week "+ eWeek+".csv");
			                      sc.close();

			   tutor();
	   	case 4:
	   		login();
	   	   default:
	   	   System.out.println("Wrong input");
	   	   tutor();
	      }
}

private static void markattend(String sFileName) throws IOException {

            Scanner scanner = new Scanner(new File("getNames.csv"));
	    Scanner in = new Scanner(System.in);
            String store = "";
            scanner.useDelimiter(",");

            while(scanner.hasNext()){
                   // System.out.print(scanner.next());
                    store = store + scanner.nextLine() + "\n";
                }
               // System.out.println(store);
                  String[] data = store.split("\n");
                //System.out.println(data.length);
               // System.out.println(data[0]);

                Scanner scanAttendance = new Scanner(System.in);
                String getAttendance = "";
                for (int a = 0; a < data.length; a++) {
                System.out.println("Mark Attendance for " +data[a] + " : ");
                getAttendance = getAttendance + scanAttendance.next() + "\n";
                }
                String[] attendance = getAttendance.split("\n");

                //System.out.println(getAttendance);

              // Set up the FileWriter with our file name.
              FileWriter saveFile = new FileWriter(sFileName);

              // Write the data to the file.
              saveFile.write("Name"); //First column
              saveFile.write(","); //Push the next input to the next column
              saveFile.write("Attendance" + "\n"); //Second column

              for (int i = 0; i < data.length; i++) {
                    saveFile.write(data[i] + ","); //First column ; push
                    saveFile.write(attendance[i] + '\n'); //second column
                }

             // All done, close the FileWriter.
             saveFile.close();
    }
private static void editCsvFile(String sFileName) throws IOException {
  try{

      //   System.out.println(sFileName);

        Scanner scanner = new Scanner(new File(sFileName));
        Scanner in = new Scanner(System.in);
        Scanner scanName = new Scanner(System.in);
        Scanner scanAttendance = new Scanner(System.in);
        String store = "";
        String getName;
        String getAttendance;
        String choice;

        scanner.useDelimiter(",");

        while(scanner.hasNext()){
          // System.out.print(scanner.next() + " ");
             store = store + scanner.nextLine() + ",";
        }
          String[] data = store.split(",");

          System.out.println("Enter the name to edit: ");
          getName = scanName.nextLine();

          boolean found = false;
          String result = "";

          for (int a = 0; a < data.length; a++) {
          //    System.out.println("Name is "+getName);

            if (getName.toLowerCase().equals(data[a].toLowerCase())) {
                //System.out.println(data[a]);
               // System.out.println(data[a+1]);
                System.out.println("Edit Attendance: ");
                getAttendance = scanAttendance.nextLine();
                 //System.out.println(getAttendance);
                data[a+1] = getAttendance;
                found = true;
                break;
                   //System.out.println("data+1 " +data[a+1].toString());
            } else {
                found = false;
            }
        }

          if (found == false){
          System.out.println("Name not found!");
          }

         // System.out.println(data.length);
       FileWriter saveFile = new FileWriter(sFileName);

       for (int b = 0; b < data.length; b++) {
          if (b % 2 == 0) {
             saveFile.write(data[b] + ","); //First column ; push
          } else {
             saveFile.write(data[b] + "\n");
          }
            }

       saveFile.close();

        System.out.println("Please choose an input: Key 1 to continue, Key 2 to cancel.");
        choice = in.nextLine();
        int choose = Integer.parseInt(choice);
        switch (choose){
             case 1:
                editCsvFile(sFileName);
                scanner.close();
                break;
            default:
                scanner.close();
                break;
            }

    }  catch(IOException e){
        System.out.println("Could not find file " + sFileName);
    }
  }
  private static void viewCsvFile(String sFileName) throws IOException {
				String choice;
		        Scanner scanner = new Scanner(new File(sFileName));
			    Scanner in = new Scanner(System.in);
		        scanner.useDelimiter(",");
		        while(scanner.hasNext()){
					String s=scanner.next();
		            System.out.printf(scanner.next()+"\t");


}
         scanner.close();
  }

}

