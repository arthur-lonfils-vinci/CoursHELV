package main;

import domaine.Employe;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class PrintEmploye implements Consumer<Employe> {


    @Override
    public void accept(Employe employe) {
        System.out.println(employe.toString());
    }
}
