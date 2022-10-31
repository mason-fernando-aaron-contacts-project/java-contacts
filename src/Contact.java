import util.Input;

public class Contact {
    static Input input = new Input();
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Number;

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public Contact() {
    }

    public Contact(String name, String number){
        this.Name = name;
        this.Number = number;
    }



}

