package edu.wleg.cassebrique.models;

import edu.wleg.cassebrique.Sprite;
import java.awt.*;

public class Rond extends Sprite {
    protected int diametre;

    public Rond(int positionX, int positionY, Color unecouleur, int diametre) {
        super(positionX, positionY, unecouleur);
        this.diametre = diametre;
    }

    public int getDiametre() {
        return diametre;
    }

    @Override
    public void dessiner(Graphics2D dessin){
        dessin.setColor(getUnecouleur());
        dessin.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        dessin.fillOval(getPositionX(),getPositionY(),this.diametre,this.diametre);
//        dessin.fillRect(super.positionX,super.positionY,this.diametre,this.diametre);
    }

    public void autodetruireobject(){
        super.unecouleur= Color.white;
        this.diametre= -1;
        this.positionX= -1;
        this.positionY= -1;
    }


}
