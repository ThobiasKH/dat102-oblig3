package uke10;

public class Person {
    private String navn;
    private MengdeADT<String> hobbyer;

    public Person(String navn, String ... hobbyer) {
        this.navn = navn;
        this.hobbyer = new JavaSetToMengde<>();
        for (String hobby : hobbyer) {
            this.hobbyer.leggTil(hobby);
        }
    }

    public String getNavn() {
        return navn;
    }

    public MengdeADT<String> getHobbyer() {
        return hobbyer;
    }
}
