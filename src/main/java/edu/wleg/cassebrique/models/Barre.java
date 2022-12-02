package edu.wleg.cassebrique.models;

import edu.wleg.cassebrique.CasseBrique;

import java.awt.*;

public class Barre extends Rectangle {

    protected int vitessebar=5;


    public Barre(int positionX, int positionY, Color unecouleur, int largeur, int hauteur, boolean jesuisunebricks) {
        super(positionX, positionY, unecouleur, largeur, hauteur, jesuisunebricks);
        this.vitessebar = vitessebar;
    }

    public void reset(int hauteur,int largeur,int largeurballe,int hauteurballe){
        this.largeur= largeurballe;
        this.hauteur=hauteurballe;
        this.positionX=(largeur/2)-(this.largeur/2);
        this.positionY=hauteur-(hauteur/8);
        this.unecouleur=Color.DARK_GRAY;
        this.rectangle=false;

    }


    public void deplacementdroite(Balle uneballe){
        if(super.positionX+super.largeur < CasseBrique.largeur ){
            super.setPositionX(super.positionX+vitessebar);
        }
    }

    public void deplacementgauche(Balle uneballe){
        if(super.positionX > 0 ){
            super.setPositionX(super.positionX-vitessebar);
        }
    }
}
