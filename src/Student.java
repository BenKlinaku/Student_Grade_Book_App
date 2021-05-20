import javax.swing.JOptionPane;

public class Student {
    private String studentName;
    private double studentAverage;
    private int studentGrade, numberOfAssignments;
    private int[] studentScores;

    public Student(String name, int assignments) {
        studentName = name;
        numberOfAssignments = assignments;
        studentScores = new int[numberOfAssignments];
    }

    public void setStudentScores() {
        String input = JOptionPane.showInputDialog("Press 1 to insert all scores, 2 to add only one/modify a score or press Enter to exit");
        switch (input.trim()) {
            case "1":
                boolean def = true;//default
                int count = 0;
                while (def && count < numberOfAssignments) {
                    String score = JOptionPane.showInputDialog("Write a score for assignment "+count+ " from 0 to 100");
                    int intScore = new Integer(score);
                    if (intScore < 0 || intScore > 100) {
                        JOptionPane.showMessageDialog(null, "Error: Invalid score");
                        def = false;
                    } else {
                        studentScores[count] = intScore;
                    }
                    count++;
                }
                break;
            case "2":
                boolean modifying=true;
                do {
                    int whichScore = new Integer(JOptionPane.showInputDialog("Which Assigment would you like to score?(Starting from 0)"));
                    if ((whichScore < 0) || (whichScore >= numberOfAssignments)) {
                        JOptionPane.showMessageDialog(null, "Error: Invalid assignment");
                        break;
                    } else {
                        int newScore = new Integer(JOptionPane.showInputDialog("Write the new score"));
                        if (newScore < 0 || newScore > 100) {
                            JOptionPane.showMessageDialog(null, "Error: Invalid score");
                            break;
                        } else {
                            studentScores[whichScore] = newScore;
                        }
                    }
                    String Another = JOptionPane.showInputDialog("Would you like to insert/modify another score?(Y/N)").trim().toUpperCase();
                    if (Another.equals("N")) {
                        modifying=false;
                    }
                    else if (!Another.equals("Y")){
                        JOptionPane.showMessageDialog(null,"Error: Invalid input");
                    }
                }
                while(modifying);
                break;
            default: break;
        }
        this.calculateGrade();
    }
    public int[] getScores(){
        return studentScores;
    }

    public int getGrade(){
        return studentGrade;
    }
    public double getAverage() {
        return studentAverage;
    }
    public String getName(){
        return studentName;
    }
    public int getNumberOfAssignments(){return numberOfAssignments;}

    private void calculateGrade(){
        double sum=0;
        for(int i=0;i<numberOfAssignments;i++){
            sum=sum+studentScores[i];
        }
        studentAverage=sum/numberOfAssignments;
        if (studentAverage>=90){
            studentGrade=10;
        }
        else if(studentAverage>=80){
            studentGrade=9;
        }
        else if(studentAverage>=70){
            studentGrade=8;
        }
        else if(studentAverage>=60){
            studentGrade=7;
        }
        else if(studentAverage>=50){
            studentGrade=6;
        }
        else studentGrade=5;
    }
}
