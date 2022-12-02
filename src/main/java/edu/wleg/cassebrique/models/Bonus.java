package edu.wleg.cassebrique.models;

import java.awt.*;

public class Bonus extends Rond {


    protected int endroit;
    public Bonus(int positionX, int positionY, Color unecouleur, int diametre) {
        super(positionX, positionY, unecouleur, diametre);
    }

    public void mouve() {
        this.positionY++;
    }

    public boolean collision(Rectangle barre,int hauteurecran) {
        boolean collision=false;
        if(this.positionY+this.getDiametre()>=barre.getPositionY() &&
                    this.positionX+this.getDiametre() > barre.getPositionX() &&
                    this.positionX < barre.getPositionX()+barre.getLargeur()){
            collision=true;
            endroit=1;
        }
        if(this.positionY+this.getDiametre()>hauteurecran){
            collision=true;
            endroit=2;
        }

        return collision;
    }

    public int getEndroit() {
        return endroit;
    }

    public void setEndroit(int endroit) {
        this.endroit = endroit;
    }



}
