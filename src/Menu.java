import javax.swing.JOptionPane;
import java.util.Arrays;
import java.text.DecimalFormat;
public class Menu {
    private Classroom c=new Classroom();
    private final String invalid="Error: Invalid input";
    private final String notFound="Error: Student is not in classroom.";

    public void mainMenu(){ //the main way the user will be interacting with the program
        boolean active=true;
        do{
            String Menu= JOptionPane.showInputDialog("Type 1 to add a new student\n"+"Type 2 to delete a student\n"+
            "Type 3 to insert a student's scores\n"+ "Type 4 to display all of one student's information\n"+
            "Type 5 to display a student's grade\n"+
            "Type 6 to display classroom grade\n"+"Type 7 to exit").trim();
            switch (Menu){
                case "1":createStudent();                            break;
                case "2":deleteStudent();                            break;
                case "3":insertScores();                             break;
                case "4":displayInfo();                              break;
                case "5":displayGrade();                             break;
                case "6":classroomGrade();                           break;
                case "7":active=false;                               break;
                default: JOptionPane.showMessageDialog(null,invalid); break;
            }
        }
        while(active);
    }
    public void createStudent(){ //method to create a new student and add him/her into the classroom
        String name= JOptionPane.showInputDialog("Type student name:");
        int num= new Integer(JOptionPane.showInputDialog("How many assignments does the student have?"));
        if(num<=0){
            JOptionPane.showMessageDialog(null,invalid);
            return;
        }
        Student student= new Student(name,num);
        c.addStudent(student.getName(),student);
        JOptionPane.showMessageDialog(null,"Student: "+name+" successfully added.");
        //asks if you want to insert the scores right now
        String Question=JOptionPane.showInputDialog("Would you like to insert "+name+"'s scores right now?(Y/N)").trim().toUpperCase();
        if(Question.equals("Y")){
            student.setStudentScores();
        }
        else if(!Question.equals("N")){JOptionPane.showMessageDialog(null,invalid); }
        //asks if you would like another student right now
        String Another=JOptionPane.showInputDialog("Would you like to insert another student?(Y/N)").trim().toUpperCase();
        if(Another.equals("Y")){
            this.createStudent();
        }
        else if(!Another.equals("N")){JOptionPane.showMessageDialog(null,invalid);}
    }
    public void deleteStudent(){//method to remove a student from the classroom
        String whichStudent= JOptionPane.showInputDialog("Which student would you like to remove?").trim();
        boolean found=c.findStudent(whichStudent);
        if(found){
            c.removeStudent(whichStudent);
            JOptionPane.showMessageDialog(null,"Student "+whichStudent+" removed.");
        }
        else {JOptionPane.showMessageDialog(null,"Student is not in classroom.");}
    }
    public void insertScores(){//method to insert a student's scores
        String whichStudent= JOptionPane.showInputDialog("Which student's scores would you like to insert?").trim();
        boolean found=c.findStudent(whichStudent);
        if(found){
            c.scoreInserts(whichStudent);
            JOptionPane.showMessageDialog(null,"Scores set");
        }
        else {JOptionPane.showMessageDialog(null,notFound);}
    }
    public void displayInfo(){
        String whichStudent= JOptionPane.showInputDialog("Which student would you like to look at?").trim();
        String name, assignmentsGrammar;
        int grade,assignments;
        double average;
        int[] scores;
        boolean found=c.findStudent(whichStudent);
        if(found){
            name= c.getName(whichStudent);
            grade=c.getGrade(whichStudent);
            assignments=c.getAssignments(whichStudent);
            average=c.getAverage(whichStudent);
            scores=c.getScores(whichStudent);
            if (assignments==1){
                assignmentsGrammar=" has had 1 assignment.\n"+"The score of which is: ";
            }
            else {assignmentsGrammar=" has had "+assignments+" assignments.\n"+"The scores of which are: ";}
            System.out.print("The student named: "+name+ assignmentsGrammar + Arrays.toString(scores) +".\n"
                    +"Which amounts to an average of: "+ new DecimalFormat("0.00").format(average)+".\n"+
                    "And that translates to a grade of "+grade+".\n");
        }
        else {JOptionPane.showMessageDialog(null,notFound);}
    }
    public void displayGrade(){
        String whichStudent= JOptionPane.showInputDialog("Which student's grade would you like to see?").trim();
        boolean found=c.findStudent(whichStudent);
        if(found){
            int gr=c.getGrade(whichStudent);
            double av=c.getAverage(whichStudent);
            System.out.println(whichStudent+" has an average of "+new DecimalFormat("0.00").format(av)+", which means they have a grade of "+gr+".");
        }
        else {JOptionPane.showMessageDialog(null,notFound);}
    }
    public void classroomGrade(){//method to calculate the grade of the total classroom
        double average=c.classAverage();
        int classGrade=-1;
        if (average>=90){
            classGrade=10;
        }
        else if(average>=80){
            classGrade=9;
        }
        else if(average>=70){
            classGrade=8;
        }
        else if(average>=60){
            classGrade=7;
        }
        else if(average>=50){
            classGrade=6;
        }
        else if(average<50){
            classGrade=5;
        }
        System.out.println("The classroom of "+c.getStudentCount()+" students has an average of: "+
                new DecimalFormat("0.00").format(average)+", which translates to a grade of:"+classGrade+".");
    }
}
