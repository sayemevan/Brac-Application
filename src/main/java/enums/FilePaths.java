package enums;

public enum FilePaths {

    CONFIG_FILE("configuration//config");

    String filePath;

    public String getPath() {
        return this.filePath;
    }

    FilePaths(String filePath) {

        this.filePath = filePath;
    }
}
