import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static int counter = 1;

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

        for (String fileName : mainFiles) {
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

        try (FileWriter writer = new FileWriter(root + "temp/temp.txt", true)) {
            writer.write(logs.toString());
            writer.flush();
        } catch (IOException ex) {
            logs.append(ex.getMessage());
        }
        //System.out.println(logs.toString());


        // serialization of objects
        String pathToSaveProgress = "/Users/Armi/IdeaProjects/Game/savegames/";

        // instantiating object, which we are going to save and zip later
        GameProgress milestone1 = new GameProgress(86, 1, 3, 42);
        GameProgress milestone2 = new GameProgress(13, 7, 94, 99);
        GameProgress milestone3 = new GameProgress(100, 10, 12, 55);

        // saving (serializing object) game progress
        saveGame(pathToSaveProgress, milestone1);
        saveGame(pathToSaveProgress, milestone2);
        saveGame(pathToSaveProgress, milestone3);

        // Create a list of files to zip later
        File dir = new File(pathToSaveProgress);
        List<String> filesToZip = new ArrayList<>();

        if (dir.isDirectory()) {

            for (File item : dir.listFiles()) {

                if (item.isDirectory()) {
                    //System.out.println(item.getName() + " - каталог");
                } else {
                    //System.out.println(item.getName() + " - файл");
                    filesToZip.add(pathToSaveProgress + item.getName());
                }
            }
        }

        // Calling zipping process. It ends with deleting files, which were packed into archive
        zipFiles(pathToSaveProgress, filesToZip);
    }

    // method to save (serialize objects) game progress
    public static void saveGame(String path, GameProgress progress) {
        try (
                ObjectOutputStream progressSavior =
                        new ObjectOutputStream(new FileOutputStream(path  + "data" + counter + ".data"));
        ) {
            progressSavior.writeObject(progress);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> stuffToPack) {
        for (String save : stuffToPack) {
            try (
                    ZipOutputStream zippingProcess =
                            new ZipOutputStream(new FileOutputStream(path + "savedGames.zip"));
                    FileInputStream fileToPack = new FileInputStream(save);
            ) {

                zippingProcess.putNextEntry(new ZipEntry(save));

                byte[] buffer  = new byte[fileToPack.available()];
                fileToPack.read(buffer);

                zippingProcess.write(buffer);
                zippingProcess.closeEntry();

                // once file is zipped we delete it from the folder
                File fileToDelete = new File(save);
                boolean success = fileToDelete.delete();
                System.out.println(save + " deleting process -> "+ success);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
