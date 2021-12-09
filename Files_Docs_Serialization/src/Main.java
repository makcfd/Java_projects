import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringBuilder logs = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        List<String> nameOfFolders = Arrays.asList("src", "res", "savegames", "temp");
        List<String> srcSubFolders = Arrays.asList("main", "test");
        List<String> resSubFolders = Arrays.asList("drawables", "vectors", "icons");
        String root = "/Users/Armi/IdeaProjects/Game/";

        // Creating general directories
        for (String name : nameOfFolders) {
            File dir = new File(root + name);
            if (dir.mkdir()) {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has been created. \n");
            } else {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has NOT been created. \n");
            }
        }
        // Creating src subfolders
        for (String name : srcSubFolders) {
            File dir = new File(root + "src/" + name);
            if (dir.mkdir()) {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has been created in src. \n");
            } else {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has NOT been created in src. \n");
            }
        }
        // Creating res subfolders
        for (String name : resSubFolders) {
            File dir = new File(root + "res/" + name);
            if (dir.mkdir()) {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has been created in res. \n");
            } else {
                logs.append(dateFormat.format(cal.getTime()) + " Folder <" + name + "> has NOT been created in res. \n");
            }
        }
        //==============================================================
        // Creating files "Main.java", "Utils.java"
        //==============================================================

        List<String> mainFiles = Arrays.asList("Main.java", "Utils.java");

        for (String fileName: mainFiles) {
            File myFile = new File(root + "src/main/" + fileName);
            try {
                if (myFile.createNewFile())
                    logs.append(dateFormat.format(cal.getTime()) + " File <" + fileName + "> has been created in src/main/. \n");
            } catch (IOException ex) {
                logs.append(dateFormat.format(cal.getTime()) + ex.getMessage());
            }
        }
        //==============================================================
        // Saving logs into temp.txt file
        //==============================================================

        try (FileWriter writer = new FileWriter(root + "temp/temp.txt", true))
        {
            writer.write(logs.toString());
            writer.flush();
        } catch (IOException ex) {
            logs.append(ex.getMessage());
        }
        System.out.println(logs.toString());
    }
}
