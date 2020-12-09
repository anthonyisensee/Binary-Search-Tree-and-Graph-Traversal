import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to convert my old .txt file to a more readable and usable .tsv file. Honestly it was probably harder to write
 * this class than to just copy the data over myself by hand, but oh well. Such is code.
 */
public class oldTxtToNewTsvConverter {

    public static void main (String[] args) {

        ArrayList<String> v1Array = new ArrayList<>();
        ArrayList<String> v2Array = new ArrayList<>();
        ArrayList<String> actionArray = new ArrayList<>();

        // add edges from file
        try {

            /** read old data from file */

            // create needed objects
            File mazeEdges = new File("res\\directedMazeEdges.txt");
            Scanner myScanner = new Scanner(mazeEdges);


            // read .txt file to array
            while (myScanner.hasNextLine()) {

                String vertex1 = "(" + myScanner.nextLine() + ")";
                String action = myScanner.nextLine();
                String vertex2 = "(" + myScanner.nextLine() + ")";

                // skip separating line (and potential descriptions)
                if (myScanner.hasNextLine()) {  // enclosed in if statement so a whitespace line is not required at end of .txt
                    myScanner.nextLine();
                }

                // add file information to arrays
                v1Array.add(vertex1);
                v2Array.add(vertex2);
                actionArray.add(action);


            }

            // close file
            myScanner.close();

            /** write data to new file */

            PrintWriter pw = new PrintWriter(new File("res\\directedMazeEdges.tsv"));

            String header = "From\tTo\tAction\tMake sure line ends with a separating tab!";
            pw.write(header);

            for (int i = 0; i < v1Array.size(); i++) {
                String data =
                        "\n" + v1Array.get(i) +
                        "\t" + v2Array.get(i) +
                        "\t" + actionArray.get(i) +
                        "\t";   // TODO: Figure out how to eliminate needing this final tab

                pw.write(data);
            }
            pw.close();

        } catch(FileNotFoundException e) {
            System.out.println("File read error. Check format.");
            e.printStackTrace();

        }
    }

}
