public class testJava {

    public static void main(String[] args) {

        Account a = new Account("Lenny", 17);
        a.accInfo();
        try {
            a.name = "s";
        }
        catch (Exception e) {
            a.setName("t");
        }
       
    }

}

class Account {
    private String name;
    private int age;

    Account(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void accInfo() {
        System.out.println(this.name + "\n" + this.age);
    }

    public void setName(String name) {
        this.name = name;
    }
}