package ordersparser.model;

public enum FileExtensions {
    JSONL("JSONL"),
    CSV("CSV");

    private final String fileExtension;

    FileExtensions(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
