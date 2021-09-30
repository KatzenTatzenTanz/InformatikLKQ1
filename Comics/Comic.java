public class Comic {
    private String name;
    private int year;
    private int value;

    public Comic() {
        this("NO DATA",-1,-1);
    }

    public Comic(String name, int year, int value) {
        this.name = name;
        this.year = year;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comic name(String name) {
        setName(name);
        return this;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            "}";
    }


}