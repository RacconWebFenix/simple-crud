package com.raccon.simplecrud.model.person;

public enum PersonFunction {

    COOPERADOR("cooperador"),
    MUSICO("músico"),
    ANCIAO("ancião"),
    PORTEIRO("porteiro");

    private String personFunction;

    PersonFunction(String personFunction) {
        this.personFunction = personFunction;
    }

    public String getPersonFunction() {
        return personFunction;
    }

}
