package training.xml;

public class Person {

    private int id;
    private String name;
    private String address;
    private int cash;
    private String education;

    public Person() {
    }

    public Person(int id, String name, String address, int cash, String education) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.SPACE)
                .append(Constants.NAME)
                .append(Constants.EQUAL)
                .append(getName())
                .append(Constants.SEMICOLON)
                .append(Constants.SPACE)
                .append(Constants.ADDRESS)
                .append(Constants.EQUAL)
                .append(getAddress())
                .append(Constants.SEMICOLON)
                .append(Constants.SPACE)
                .append(Constants.CASH)
                .append(Constants.EQUAL)
                .append(getCash());
        return  builder.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Person)) return false;

        Person test = (Person) obj;

        if (!this.name.equals(test.name)) return false;
        if (!this.address.equals(test.address)) return false;
        return this.cash == test.cash;
    }

    @Override
    public int hashCode(){
        int code = 31;
        code *= (name == null ? 1 : name.hashCode());
        code *= (address == null ? 1 : address.hashCode());
        code *= (cash == 0 ? 1 : cash);
        code += (education == null ? 1 : education.hashCode());
        return code;
    }
}
