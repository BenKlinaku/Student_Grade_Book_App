
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Classroom {
    private Map<String, Student> ListOfStudents=new HashMap<>();

    public void addStudent(String s,Student st) { ListOfStudents.put(s,st); }
    public void removeStudent(String key){  ListOfStudents.remove(key); }
    public boolean findStudent(String key){
        return ListOfStudents.containsKey(key);
    }
    public int getStudentCount(){
        return ListOfStudents.size();
    }
    public void scoreInserts(String s){
        ListOfStudents.get(s).setStudentScores();
    }
    public String getName(String s){
        return ListOfStudents.get(s).getName();
    }
    public int getGrade(String s){
        return ListOfStudents.get(s).getGrade();
    }
    public int getAssignments(String s){
        return ListOfStudents.get(s).getNumberOfAssignments();
    }
    public int[] getScores(String s){
        return ListOfStudents.get(s).getScores();
    }
    public double getAverage(String s){
        return ListOfStudents.get(s).getAverage();
    }
    public double classAverage(){
        double sumOfAverages=0;
        int numbOfStudents = ListOfStudents.size();
        Iterator<Student> studentIterator= ListOfStudents.values().iterator();
        while (studentIterator.hasNext()){
            sumOfAverages += studentIterator.next().getAverage();
        }
        return sumOfAverages/numbOfStudents;
    }
}
