import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableExpirementCreator {

   // CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
    private CSVPrinter printer;
    private FileWriter fileWriter ;
    private HashMap<Integer,ArrayList<String>> tableForPrint;
    private short height;
    private short length;

    public TableExpirementCreator(String nameOfcsvFile, short length,short height) {
        this.height = height;
        this.length = length;
        tableForPrint = new HashMap<>();
        for (int i = 0; i < height; i++) {
            tableForPrint.put(i,new ArrayList<>());
        }
            try {
                fileWriter = new FileWriter(nameOfcsvFile);
                printer = new CSVPrinter(fileWriter, CSVFormat.EXCEL);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }



    public void writeToFile(int x , int y,String content)
    {
       if(x<height&& x>=0 && y>=0 && y<length){
        tableForPrint.get(x).add(y,content);
       }else {
           System.out.println("you are out of bounds my good sir.");
       }
    }

    public  void  CreateFile()
    {
        try {
            for (int i = 0; i < height; i++) {
                 printer.printRecord(tableForPrint.get(i));
            }
            fileWriter.flush();
            fileWriter.close();
            printer.close();
        } catch (IOException e) {

            System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");

            e.printStackTrace();

        }

    }

}
