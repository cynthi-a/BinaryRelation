import java.io.FileWriter;
import java.io.IOException;

public class BinaryRelationTest {

    public static void main(String[] args) {
        runTests();
    }

    @SuppressWarnings("unchecked")
    private static void runTests() {
        BinaryRelation implementedBinaryRelation = new BinaryRelationImpl<String, String>(9);
        StringBuilder stringBuilder = new StringBuilder();
        FileWriter fileWriter;

        clearAndRepopulate(implementedBinaryRelation);

        String newLine = String.format("%n");
        String separator = newLine + "-------------------------------------------------" + newLine;
        String binaryRelationToString = "Content of the binary relation: " + newLine + implementedBinaryRelation.toString();
        String description = "Description: ";

        stringBuilder.append("TESTING MY BINARY RELATION");
        stringBuilder.append(separator);

        stringBuilder.append("1) addPair(X xValue, Y yValue)" + newLine + description +
                            "The relation adds a given pair." + newLine + "Test data: " + newLine);
        stringBuilder.append(newLine +
                "addPair(FR, French);" + newLine +
                "addPair(IT, Italian);" + newLine +
                "addPair(DE, German);" + newLine +
                "addPair(BE, French);" + newLine +
                "addPair(BE, Flemish);" + newLine +
                "addPair(NL, Dutch);" + newLine +
                "addPair(UK, English);" + newLine +
                "addPair(IE, English);" + newLine +
                "addPair(IE, Irish);" + newLine + newLine + binaryRelationToString + separator);

        stringBuilder.append("2) contains(X xValue, Y yValue)" + newLine + description +
                            "Returns a boolean to indicate if the binary relation contains a " +
                "given X value and Y value pair." + newLine);
        stringBuilder.append("Result of calling contains(NL, Dutch): " + implementedBinaryRelation.contains("NL", "Dutch") +
                            separator);

        stringBuilder.append("3) getValues(X xValue)" + newLine + description + "Returns a set containing all values" +
                " that are related to the given X value" + newLine);
        stringBuilder.append("Result of calling getValues(BE): " + implementedBinaryRelation.getValues("BE") + separator);

        stringBuilder.append("4) getXvalues(Y yValue)" + newLine + description + "Returns a set containing all keys that" +
                " are related to the given Y value" + newLine);
        stringBuilder.append("Result of calling getXvalues(French): " + implementedBinaryRelation.getXvalues("French") + separator);

        stringBuilder.append("5) removePair(X xValue, Y yValue)" + newLine + description + "Removes a given pair." + newLine);
        stringBuilder.append("BinaryRelation before calling removePair(NL, Dutch): " + newLine + implementedBinaryRelation + newLine +
                            "BinaryRelation after calling removePair(NL, Dutch): " + newLine);
        implementedBinaryRelation.removePair("NL", "Dutch");
        stringBuilder.append(implementedBinaryRelation.toString() + separator);

        implementedBinaryRelation.addPair("NL", "Dutch");

        stringBuilder.append("6) removeRelationsByX(X xValue)" + newLine + description + "Removes all pairs that contain " +
                "a given xValue" + newLine);
        stringBuilder.append(binaryRelationToString +newLine);
        stringBuilder.append("BinaryRelation after calling removeRelationsByX(BE):" + newLine);
        implementedBinaryRelation.removeRelationsByX("BE");
        stringBuilder.append(implementedBinaryRelation + separator);

        clearAndRepopulate(implementedBinaryRelation);

        stringBuilder.append("7) removeRelationsByY(Y yValue)" + newLine + description + "Removes all pairs that contain " +
                "a given Y value" + newLine);
        stringBuilder.append(binaryRelationToString + newLine);
        stringBuilder.append("BinaryRelation after calling removeRelationsByY(French):" + newLine);
        implementedBinaryRelation.removeRelationsByY("French");
        stringBuilder.append(implementedBinaryRelation + separator);

        clearAndRepopulate(implementedBinaryRelation);

        stringBuilder.append("8) clear()" + description + "Clears the relation" + newLine + "BinaryRelation " +
                "before calling clear(): " + implementedBinaryRelation + newLine + "Result after calling clear()" +
                " and contains(IE, Irish): ");
        implementedBinaryRelation.clear();
        String resultForClearing = Boolean.toString(implementedBinaryRelation.contains("IE", "Irish"));
        stringBuilder.append(resultForClearing);

        System.out.print(newLine + stringBuilder.toString() + newLine + newLine + "(The same output can be" +
                " found in B_output.txt)" + newLine);

        try {
            fileWriter = new FileWriter("B_output.txt");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    //helper method to ensure that tests start off with the same data
    private static void clearAndRepopulate(BinaryRelation binaryRelation) {
        binaryRelation.clear();

        binaryRelation.addPair("FR", "French");
        binaryRelation.addPair("DE", "German");
        binaryRelation.addPair("IT", "Italian");
        binaryRelation.addPair("BE", "French");
        binaryRelation.addPair("BE", "Flemish");
        binaryRelation.addPair("NL", "Dutch");
        binaryRelation.addPair("UK", "English");
        binaryRelation.addPair("IE", "English");
        binaryRelation.addPair("IE", "Irish");
    }
}
