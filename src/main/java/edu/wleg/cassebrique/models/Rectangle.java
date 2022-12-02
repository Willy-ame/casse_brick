package edu.wleg.cassebrique.models;
import java.awt.*;

public class Rectangle extends Carre {
    protected int hauteur;
    private int compteurcouleur = 0;
    boolean rectangle=true;

   public static int CompteBricks=0;


    public Rectangle(int positionX, int positionY, Color unecouleur, int largeur,int hauteur,boolean jesuisunebricks) {
        super(positionX, positionY, unecouleur, largeur);
        this.hauteur=hauteur;
        this.rectangle=jesuisunebricks;
        if(jesuisunebricks)CompteBricks++;
    }
    public int getHauteur() {
        return hauteur;
    }

    public void dessiner(Graphics2D dessin){
        dessin.setColor(super.getUnecouleur());
        dessin.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        dessin.drawRect(super.positionX,super.positionY,super.getLargeur(),this.hauteur);
        dessin.fillRect(super.positionX,super.positionY,super.getLargeur(),this.hauteur);

        dessin.setColor(Color.LIGHT_GRAY);
        dessin.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        dessin.drawRect(super.positionX,super.positionY,super.getLargeur(),this.hauteur);
    }



    public int setcomptecouleur(int valeur){
        if(compteurcouleur==0) this.setUnecouleur(Color.ORANGE);
        if(compteurcouleur==1) this.setUnecouleur(Color.RED);
        return compteurcouleur=compteurcouleur+valeur;
    }

    public int setcompteurcouleur(){
        return this.compteurcouleur;
    }
    public int getcompteurcouleur(){
        return this.compteurcouleur;
    }

    public boolean isRectangle() {
        return rectangle;
    }

    public void setRectangle(boolean rectangle) {
        this.rectangle = rectangle;
    }

}
