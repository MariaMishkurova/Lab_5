package Main_part;

public enum Style {
    BLACK("\u001B[0m"), PURPLE("\u001B[35m"), RED("\u001B[31m"), GREEN("\u001B[32m"),
    LINE("-----------------------------------------------------------------------------------------------------------------------");
    private final String s;
    Style(String s){
        this.s = s;
    }
    @Override
    public String toString(){
        return s;
    }

}
