import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Task
        implements
        Iterable<String>,
        FileHandler
{
    // Klasa odpowiedzialna za konkretne polecenie
    private String name; // Nazwa polecenia
    private ArrayList<String> content = new ArrayList<>(); // Arraylista z liniami polecenia
    private String path; // Ścieżka do pliku z poleceniem
    public Task(String name, ArrayList<String> content, String path){
        this.name = name;
        this.content = content;
        this.path = path;
    }
    public Task(String path){
        this.path = path;
    }
    public Task(String name, String path){
        this.name = name;
        this.path = path;
    }

    public String getName(){
        return this.name;
    }
    public ArrayList<String> getContent(){
        return this.content;
    }
    public String getPath(){
        return this.path;
    }

    @Override
    public Iterator<String> iterator() {
        return content.iterator();
    }
    @Override
    public void forEach(Consumer<? super String> action) {
        Iterable.super.forEach(action);
    }
    @Override
    public String toString(){
        String result = "";
        for(String line : this.content){
            boolean firstDollarSign = false;
            boolean startPath = false;
            for(int i = 0; i < line.length(); ++i){
                // Kolorowanie tekstu dla formuł
                if(line.charAt(i) == '$' && !firstDollarSign){
                    result += "\033[0;31m" + line.charAt(i);
                    firstDollarSign = true;
                } else if (line.charAt(i) == '$' && firstDollarSign) {
                    result += line.charAt(i) + "\033[0m";
                    firstDollarSign = false;
                }
                // Kolorowanie tekstu dla ścieżek
                else if (!startPath && !firstDollarSign && line.charAt(i) == '/') {
                    String pathPrefix = "/href";
                    boolean isPath = true;
                    for(int j = 0; j < pathPrefix.length(); ++j){
                        if(line.charAt(i + j) != pathPrefix.charAt(j)){
                            isPath = false;
                            break;
                        }
                    }
                    if(isPath){
                        result += "\033[0;33m" +  line.charAt(i);
                        startPath = true;
                    } else{
                        result += line.charAt(i);
                    }
                } else if (startPath && line.charAt(i) == '}') {
                    result += line.charAt(i) + "\033[0m";
                    startPath = false;
                }
                // Defaultowe wpisywanie tekstu
                else {
                    result += line.charAt(i);
                }
            }
        }
        return result;
    }
    // Metoda zapisująca zadanie do pliku
    @Override
    public void createFile() {
        File file = new File(this.path);
        try (FileWriter fileWriter = new FileWriter(file)) {
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.forEach(line -> {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    // Metoda wczytująca zadanie z pliku
    @Override
    public void loadFile() {
        File file = new File(this.path);
        if(this.content.size() != 0){
            this.content.clear();
        }
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                this.content.add(line + "\n");
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Metoda służąca do modyfikowania polecenia
    public void modifyLine(int lineNumber, String newLine){
        if(lineNumber - 1 >= this.content.size() || lineNumber - 1 < 0){
            System.out.println("Błędny numer linii");
            return;
        }
        this.content.set(lineNumber - 1, newLine + "\n");
    }
    // Metoda służąca do dodawania kolejnej linii do polecenia
    public void addLine(String newLine){
        this.content.set(this.content.size() - 1, this.content.get(this.content.size() - 1) + "\n");
        this.content.add(newLine);
    }
}
