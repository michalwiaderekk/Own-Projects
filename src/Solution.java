import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution
        implements
        FileHandler{
    private final String basePath = "C:\\Users\\rwiad\\Desktop\\Uczelnia\\modul_edycyjny";
    private String studentName;
    private Map<String,ArrayList<String>> solutions;
    public Solution(String studentName){
        this.studentName = studentName;
        this.solutions = new HashMap<>();
    }

    @Override
    public void createFile() {
        return;
    }

    @Override
    public void loadFile() {
        Path studentPath = Paths.get(basePath, studentName);

        try {
            if (Files.exists(studentPath) && Files.isDirectory(studentPath)) {
                Files.walk(studentPath)
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".txt"))
                        .forEach(path -> {
                            String taskName = path.getParent().getFileName().toString();
                            String fileName = path.getFileName().toString();

                            solutions.computeIfAbsent(taskName, k -> new ArrayList<>()).add(fileName);
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return this.solutions.toString();
    }
    public int getSolutionsNumber(String taskName){
        return this.solutions.get(taskName).size();
    }
    public int getTaskNumber(){
        return this.solutions.size();
    }
    public String viewSolution(String path){
        Task solution = new Task(path);
        solution.loadFile();
        return solution.toString();
    }
    public String getStudentName(){
        return this.studentName;
    }
    public Map<String, ArrayList<String>> getSolutions() {
        return this.solutions;
    }
}
