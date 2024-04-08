package com.raccon.simplecrud.model.person;

public enum PersonInstrument {
    PIANO("piano"),
    TECLADO("teclado"),
    ORGAO("órgão"),
    VIOLAO("violão"),
    GUITARRA("guitarra"),
    BAIXO_ELETRICO("baixo elétrico"),
    BATERIA("bateria"),
    FLAUTA("flauta"),
    VIOLINO("violino"),
    SAXOFONE("saxofone"),
    TROMPETE("trompete"),
    TROMBONE("trombone"),
    CLARINETE("clarinete"),
    HARPA("harpa"),
    ACORDEAO("acordeão"),
    PERCUSSAO("percussão");

    private String instrument;

    PersonInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }
}
