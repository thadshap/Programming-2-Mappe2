package stud.ntnu.IDATT2001.MappeDel2.model;

/**
 * Immutable class to store information about a file path
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class PathContent {
    private final String dirName;
    private final String fileName;

    /**
     * Constructor
     * @param dirName
     * @param fileName
     */
    public PathContent(String dirName, String fileName) {
        this.dirName = dirName;
        this.fileName = fileName;
    }

    /**
     * Accessor for directory name
     * @return
     */
    public String getDirName() {
        return dirName;
    }

    /**
     * Accessor for file name
     * @return
     */
    public String getFileName() {
        return fileName;
    }
}
