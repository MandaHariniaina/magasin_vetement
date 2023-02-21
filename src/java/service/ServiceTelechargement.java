/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modele.Produit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.FileOutputStream;

/**
 *
 * @author ACER
 */
public class ServiceTelechargement {

    public void telecharger(Produit produit) throws Exception {

    }

    public void uploadImage(Part image, String appPath) throws Exception {
        String fileName = image.getSubmittedFileName();
        InputStream is = image.getInputStream();

        //System.out.println(appPath+ "images\\produits\\" + fileName);
        File fichier = new File(appPath + "images\\produits\\" + fileName);
        boolean res = fichier.createNewFile();

        OutputStream outStream = new FileOutputStream(fichier);

        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        is.close();
        outStream.close();
    }

}
