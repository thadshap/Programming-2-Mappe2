package stud.ntnu.IDATT2001.MappeDel2;

public class PathContent {
    private final String dirName;
    private final String fileName;

    public PathContent(String dirName, String fileName) {
        this.dirName = dirName;
        this.fileName = fileName;
    }

    public String getDirName() {
        return dirName;
    }

    public String getFileName() {
        return fileName;
    }
}
