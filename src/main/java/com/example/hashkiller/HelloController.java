package com.example.hashkiller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HelloController {
    @FXML
    private TextField contras;
    @FXML
    private TextField hashs;
    @FXML
    private Label resultat;
    FileOutputStream fos ;
    ObjectOutputStream salida ;


    FileInputStream fis;


    List<contra> miLista = new ArrayList<contra>();

    private final com.example.hashkiller.contra contra =new contra();

    List<contra> Comprovar = new ArrayList<contra>();

    @FXML
    public void login() {

        try{
            fis = new FileInputStream("contraseña.dat");
            ObjectInputStream dataInputStreamAux = new ObjectInputStream(fis);
            Comprovar= (List<com.example.hashkiller.contra>) dataInputStreamAux.readObject();
            fos = new FileOutputStream("contraseña.dat");
            salida = new ObjectOutputStream(fos);

            contra.contra= String.valueOf(contras.getText());

            contra.hash= MD5.getMd5(contras.getText());
            hashs.setText(contra.hash);
            Comprovar.add(contra);
            salida.writeObject(Comprovar);
            salida.close();



        }catch (Exception ex){
            System.out.println("no sha pogut escriure al fitxer");
        }
    }

    @FXML
    public void logout() {

        try {

            fis = new FileInputStream("contraseña.dat");
            ObjectInputStream dataInputStreamAux = new ObjectInputStream(fis);
            miLista.clear();

            while (true) {
                try {



                    miLista= (List<com.example.hashkiller.contra>) dataInputStreamAux.readObject();

                }catch (EOFException e){
                    break;
                }


            }



            for (contra persona : miLista) {
                System.out.println(persona.contra);
                System.out.println(persona.hash);

                if (persona.hash.equals(hashs.getText()) ) {
                    resultat.setText("la contrasseña es," + persona.contra);

                } else {
                    resultat.setText("contrasseña no registrada");

                }

            }



            fis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}