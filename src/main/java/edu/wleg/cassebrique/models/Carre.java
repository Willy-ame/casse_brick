package edu.wleg.cassebrique.models;

import edu.wleg.cassebrique.Sprite;

import java.awt.*;

public class Carre extends Sprite {
    protected int largeur;

    public Carre(int positionX, int positionY, Color unecouleur,int largeur) {
        super(positionX, positionY, unecouleur);
        this.largeur=largeur;
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(super.getUnecouleur());
        dessin.fillRect(super.getPositionX(),super.getPositionY(),this.largeur,this.largeur);
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur=largeur;
    }
}
