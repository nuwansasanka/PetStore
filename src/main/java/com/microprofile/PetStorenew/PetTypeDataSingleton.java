package com.microprofile.PetStorenew;
import java.util.ArrayList;
import java.util.List;

public class PetTypeDataSingleton {
    private static  PetTypeDataSingleton myInstance;
    public List<PetType> pets = new ArrayList<PetType>();
    PetType petT1 = new PetType();
    PetType petT2 = new PetType();
    PetType petT3 = new PetType();

    private PetTypeDataSingleton() {
        petT1.setPetTypeId(1);
        petT1.setPetType("Dog");

        petT2.setPetTypeId(2);
        petT2.setPetType("Cat");

        petT3.setPetTypeId(3);
        petT3.setPetType("Bird");

        pets.add(petT1);
        pets.add(petT2);
        pets.add(petT3);
    }
    public static  PetTypeDataSingleton getInstance() {
        if (myInstance == null)
            myInstance = new  PetTypeDataSingleton();
        return myInstance;
    }
    public void setArrayList(List<PetType> pets) {
        this.pets = pets;
    }
    public List<PetType> getArrayList() {
        return this.pets;

    }

}
