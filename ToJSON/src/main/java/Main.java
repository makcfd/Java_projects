import java.io.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.*;
import com.opencsv.bean.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;

public class Main {



    public static void main(String[] args) {
// Task 1: CSV -> Object -> JSON
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String csvFileName = "data.csv";

        List<Employee> listCSV = parseCSV(columnMapping, csvFileName);

        writeString(listToJSON(listCSV), "data.json");
// Task 2: XML -> Object -> JSON
        List<Employee> listXML = parseXML("data.xml");
        writeString(listToJSON(listXML),"data2.json");

    }

    public static List<Employee> parseCSV(String[] mapping, String fileName) {
        // create an array veriable to store objects
        List<Employee> workers;
        try (
                // creating csvReader object as an wrapper to get data from file
                // passing FileReader instance of file
                CSVReader csvReader = new CSVReader(new FileReader(fileName))
        ) {
            // defining strategy  to read csv
            // column mapping and type of class to which data will be mapped
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(mapping);

            // creaing an instance of CcvToBean and mapps data to beans (javaObjects)
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            // parse data to objects -> read file and map data to variables of instances of the class
            workers = csv.parse();

            return workers;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // method to parse XML and to return array of Employee objects 
    public static List<Employee> parseXML(String fileName) {

        List<Employee> workersFromXML = new ArrayList<Employee>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            // from document get root element of XML file
            Node root = document.getDocumentElement();

            // from root get all children
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                // empty Employee object to store data
                Employee tempWorker = new Employee();
                String tag = null;

                Node node_ = nodeList.item(i);
                // getting subchildren, in our case leaf nodes from which we will takes values
                NodeList node2 = node_.getChildNodes();
                if (Node.ELEMENT_NODE == node_.getNodeType()) {
                    Element employee = (Element) node_;
                    // looping thorugh subchildren
                    for (int j = 0; j < node2.getLength(); j++) {
                        Node node2item = node2.item(j);
                        if (Node.ELEMENT_NODE == node2item.getNodeType()) {
                            // get name of each subchilder and save as a tag
                            tag = node2item.getNodeName();
                            // using tag to get necessary values
                            // values used to update temp Employee object
                            switch (tag) {
                                case "id":
                                    tempWorker.id =  Long.parseLong(employee.getElementsByTagName(tag).item(0).getTextContent());
                                    break;
                                case "firstName":
                                    tempWorker.firstName =  employee.getElementsByTagName(tag).item(0).getTextContent();
                                    break;
                                case "lastName":
                                    tempWorker.lastName =  employee.getElementsByTagName(tag).item(0).getTextContent();
                                    break;
                                case "country":
                                    tempWorker.country =  employee.getElementsByTagName(tag).item(0).getTextContent();
                                    break;
                                case "age":
                                    tempWorker.age = Integer.parseInt(employee.getElementsByTagName(tag).item(0).getTextContent());
                                    break;
                            }
                        }
                    }

                    // once we get all values for the Employee object we add to the array
                    workersFromXML.add(tempWorker);
                }
                }
            } catch(Exception e){
                e.printStackTrace();
            }

        return workersFromXML;
        }

    // method that transforms objects into JSON data using GSON library
    public static String listToJSON(List<Employee> workers) {
        Type listType = new TypeToken<List<Employee>>() {}.getType();

        /*
        on lecture we were given Gsonbuilder but accorgind to javadoc:
        https://www.javadoc.io/doc/com.google.code.gson/gson/2.8.5/com/google/gson/GsonBuilder.html
        in simple situations we can directly use constructor Gson()
         */
        // lecture example
        // GsonBuilder builder = new GsonBuilder();
        // Gson gson = builder.create();
        // String json = gson.toJson(workers, listType);

        // personal implementation
        Gson gson = new Gson();
        String json = gson.toJson(workers, listType);
        return json;
    }
    // mothod to wride data to the file
    public static void writeString(String newJSON, String fileNameToWrite) {
        try (FileWriter file = new FileWriter(fileNameToWrite)) {
            file.write(newJSON);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
