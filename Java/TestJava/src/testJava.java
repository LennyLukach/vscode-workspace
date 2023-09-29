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

    public String getName() {
        return this.name;
    }
}