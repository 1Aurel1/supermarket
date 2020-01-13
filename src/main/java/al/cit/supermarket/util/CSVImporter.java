package al.cit.supermarket.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class CSVImporter<Object, Response> {

    // List to contain the objects from csv
    private List<Object> objects;
    // List to contain objects that may be duplications(ex. db records)
    private List<Object> listToCompare;

    private MessageSource messageSource;
    // List to contain generated messages
    private List<Response> msgs;

    // Variables for reading the file
    private String cvsSplitBy;
    private int lineNr;
    private int allowedColumns;

    private boolean ignoreDuplications;

    // For user messages
    private StringBuilder parsingErrorMsgs;
    private StringBuilder lineErrorMsgs;
    private StringBuilder duplicationInDBErrorMsgs;
    private StringBuilder duplicationInCSVErrorMsgs;
    private String bufferReaderError;
    private String fileFormatError;

    private int parsingErrorCount;
    private int lineErrorCount;
    private int duplicationInDBErrorCount;
    private int duplicationInCSVErrorCount;

    private boolean hasError;

    // Constructor - initializing the default values
    public CSVImporter() {

        this.objects = new ArrayList<>();
        this.msgs = new ArrayList<>();

        this.cvsSplitBy = ",";
        this.lineNr = 0;
        this.allowedColumns = 0;

        this.ignoreDuplications = false;

        this.parsingErrorMsgs = new StringBuilder();
        this.lineErrorMsgs = new StringBuilder();
        this.duplicationInDBErrorMsgs = new StringBuilder();
        this.duplicationInCSVErrorMsgs = new StringBuilder();
        this.fileFormatError = "";
        this.bufferReaderError = "";

        this.parsingErrorCount = 0;
        this.lineErrorCount = 0;
        this.duplicationInDBErrorCount = 0;
        this.duplicationInCSVErrorCount = 0;

        this.hasError = false;
    }

    // Reading and validating the csv
    public void importFromCSV(MultipartFile file) {

        // Check if file type is csv
        if (isCSV(file.getContentType())) {

            BufferedReader br= null;
            String line;

            try {
                // read the file
                br = new BufferedReader(new InputStreamReader(file.getInputStream()));

                //loping the lines
                while ((line = br.readLine()) != null) {

                    // use comma as separator for columns
                    readRow(line.split(cvsSplitBy));
                }

                // In case of a line error or parsing error the 'hasError' boolean will be set to true
                if (this.lineErrorMsgs.length() != 0 || this.parsingErrorMsgs.length() != 0)
                    this.hasError = true;

                // Setting the user response
                responseConfiguration();

            } catch (IOException e) {

                // handle file input exception
                this.bufferReaderError = this.messageSource.getMessage("import.response.error.default", null, LocaleContextHolder.getLocale());
            } finally {

                if (br != null) {

                    try {

                        br.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isCSV(String type){

        // Check to compare the type of the file, if not correct skip
        if (!(Objects.equals(type, "text/csv") || Objects.equals(type, "application/vnd.ms-excel"))) {

            // Notifying that the format is not correct
            this.fileFormatError = this.messageSource.getMessage("import.response.error.fileFormat", new String[]{type}, LocaleContextHolder.getLocale());

            return false;
        }

        return true;
    }

    private void readRow(String[] c){

        Object object = null;

        boolean isInList = false;
        boolean isInDB = false;

        // Check if the csv has more than 5 columns
        if (this.allowedColumns == 0 || c.length < this.allowedColumns + 1) {

            try {

                // Converting String[] to Object
                object = mapStringToObject(c);

                // Check if city if duplicated in the csv
                if (!this.ignoreDuplications)
                    isInList = isDuplicate(this.objects, object, this.duplicationInCSVErrorMsgs, "import.response.error.duplicationInCSV");

                if (isInList)
                    this.duplicationInCSVErrorCount++;

                // Check if city already exists in db
                if (!this.listToCompare.isEmpty() && !isInList)
                    isInDB = isDuplicate(this.listToCompare, object, this.duplicationInDBErrorMsgs, "import.response.error.duplicationInDB");

                if (isInDB)
                    this.duplicationInDBErrorCount++;

                // Forbid the object from being added to the objects list in case it is duplication in csv or in db
                if (!(isInList || isInDB))
                    this.objects.add(object);

            } catch (NumberFormatException e) {

                // If a number format happens during Float parsing the object will not be added to the objects list
                // Handel float parsing Exceptions
                this.parsingErrorCount++;
                this.parsingErrorMsgs.append(this.messageSource.getMessage("import.response.error.parsingErrorMsg", new String[]{Integer.toString(this.lineNr), e.getMessage().replace("\"", "'")}, LocaleContextHolder.getLocale()));
            }

        } else {

            // If a line error happens during Float parsing the object will not be added to the objects list
            // Handel the line errors
            this.lineErrorCount++;
            this.lineErrorMsgs.append(this.messageSource.getMessage("import.response.error.lineErrorMsg", new String[]{Integer.toString(this.lineNr), Integer.toString(c.length)}, LocaleContextHolder.getLocale()));
        }
    }

    public abstract Object mapStringToObject(String[] strings);

    public abstract boolean isDuplicate(List<Object> objects, Object obj, StringBuilder stringBuilder, String message);

    public abstract void responseConfiguration();
}
