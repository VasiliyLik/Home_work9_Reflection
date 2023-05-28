package reflectionAnnotation;

public enum FieldTypes {
    TYPE_STRING("class java.lang.String"),
    TYPE_INT("int"),
    TYPE_DATE("class java.util.Date"),
    TYPE_BOOLEAN("boolean");

    public final String type;

    FieldTypes(String type) {
        this.type = type;
    }
}
