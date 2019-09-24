package entity;

import java.util.Objects;

public class Document {
    private String name;
    private long timeOfPrint;
    private String paperSize;

    public Document(String name, long timeOfPrint, String paperSize) {
        this.name = name;
        this.timeOfPrint = timeOfPrint;
        this.paperSize = paperSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeOfPrint() {
        return timeOfPrint;
    }

    public void setTimeOfPrint(long timeOfPrint) {
        this.timeOfPrint = timeOfPrint;
    }

    public String getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(String paperSize) {
        this.paperSize = paperSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return timeOfPrint == document.timeOfPrint &&
                Objects.equals(name, document.name) &&
                Objects.equals(paperSize, document.paperSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timeOfPrint, paperSize);
    }

    @Override
    public String toString() {
        return "Название документа: " + name + '\'' +
                ", время печати документа: " + timeOfPrint +
                ", размер бумаги: " + paperSize;
    }
}
