package ir.maktab.finalproject.model.enums;

public enum Type {

    descriptive("descriptive"),
    multipleChoice("multipleChoice"),
    mixed("mixed");


    private String text;

    Type(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Type fromString(String text) {
        for (Type b : Type.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
