package edu.wleg.cassebrique.models;

import edu.wleg.cassebrique.CasseBrique;

import java.awt.*;
public class Balle extends Rond{
    protected int vitesseballex;
    protected int vitesseballey;
    protected boolean bonus =false;

    public Balle(int positionX, int positionY, Color unecouleur, int diametre, int vitesseballe, int vitesseballey, boolean jesuisbonus) {
        super(positionX, positionY, unecouleur, diametre);
        this.vitesseballex = vitesseballe;
        this.vitesseballey = vitesseballey;
        this.bonus=jesuisbonus;
    }

    public void mouve() {
            this.positionX += this.vitesseballex;
            this.positionY += this.vitesseballey;
    }

    public void resetballe(Barre unebarre){
        this.positionX=(unebarre.getPositionX()+(unebarre.getLargeur()/2)-(this.diametre/2));
        this.positionY=unebarre.getPositionY()-25;
        this.vitesseballex=0;
        this.vitesseballey=0;
    }

    public boolean collisionbonus(Rectangle barre){
        boolean trouve = false;
        if (this.positionY >= CasseBrique.hauteur - getDiametre() && this.bonus) {
            trouve=true;
        }
        return trouve;
    }


    public boolean collision(Rectangle barre){

        boolean trouve = false;

        float x = this.positionX+((float)this.diametre/2);
        float y = this.positionY+((float)this.diametre/2);
            //Si collision
            if(this.positionX +this.diametre > barre.getPositionX() && this.positionX < barre.getPositionX()+barre.getLargeur() &&
                    this.positionY +this.diametre > barre.getPositionY() && this.positionY <  barre.getPositionY()+barre.getHauteur()) {
                //Gauche
                if (x < barre.getPositionX() && y > barre.getPositionY() && y < barre.getPositionY() + barre.getHauteur()) {
                    this.positionX = barre.getPositionX() - this.diametre;
                    this.vitesseballex *= -1;
                    trouve=true;

                }
                //Droite
                if (x > barre.getPositionX() + barre.getLargeur() && y > barre.getPositionY() && y < barre.getPositionY() + barre.getHauteur()) {
                    this.vitesseballex *= -1;
                    this.positionX = barre.getPositionX() + barre.getLargeur();
                    trouve=true;
                }
                //Haut
                if (y < barre.getPositionY() && x > barre.getPositionX() && x < barre.getPositionX() + barre.getLargeur()) {
                    //Diriger balle
                    int barrepartie = barre.getLargeur()/3;
                    if(x<barre.getPositionX()+barrepartie && x>barre.getPositionX()){
                        this.vitesseballex = -1;
                    }
                    if(x<barre.getPositionX()+barre.getLargeur() && x>barre.getPositionX()+(barrepartie*2)){
                        this.vitesseballex = +1;
                    }
                    this.vitesseballey *= -1;
                    this.positionY = barre.getPositionY() - this.diametre;
                    trouve=true;
                }
                //Bas
                if (y > barre.getPositionY() + barre.getHauteur() && x > barre.getPositionX() && x < barre.getPositionX() + barre.getLargeur()) {
                    this.vitesseballey *= -1;
                    this.positionY = barre.getPositionY() + barre.getHauteur();
                    trouve=true;
                }
                //Gauche-Haut
                if (x < barre.getPositionX() && y < barre.getPositionY() && y < barre.getPositionY() + barre.getHauteur()) {
                    this.vitesseballex *= -1;
//                    this.vitesseballey *= -1;
                    this.positionX = barre.getPositionX() - this.diametre;
                    this.positionY = barre.getPositionY() - this.diametre;
                    trouve=true;
                }
                //Gauche-Bas
                if (x < barre.getPositionX() && y > barre.getPositionY() && y > barre.getPositionY() + barre.getHauteur()
                        && x < barre.getPositionX() + barre.getLargeur()) {
                    this.vitesseballex *= -1;
                    // this.vitesseballey *= -1;
                    this.positionX = barre.getPositionX() - this.diametre;
                    this.positionY = barre.getPositionY() + barre.getHauteur();
                    trouve=true;

                }
                //Droite-Haut
                if (x > barre.getPositionX()+barre.getLargeur() && y < barre.getPositionY() && y < barre.getPositionY() + barre.getHauteur()) {
                    this.vitesseballex *= -1;
                    //  this.vitesseballey *= -1;
                    this.positionX = barre.getPositionX() + barre.getLargeur();
                    this.positionY = barre.getPositionY() - this.diametre;
                    trouve=true;
                }
                //Droite-Bas
                if (x > barre.getPositionX()+barre.getLargeur() && y > barre.getPositionY() && y > barre.getPositionY() + barre.getHauteur()
                        && x < barre.getPositionX() + barre.getLargeur()) {
                        this.vitesseballex *= -1;
                        // this.vitesseballey *= -1;
                        this.positionX = barre.getPositionX() + barre.getLargeur();
                        this.positionY = barre.getPositionY() + barre.getHauteur();
                        trouve=true;
                }
            }

                //Ecran X
                if (this.positionX >= CasseBrique.largeur - getDiametre()) {
                    this.vitesseballex *= -1;
                    this.positionX=CasseBrique.largeur-getDiametre()-1;

                }

                //Ecran X
                if (this.positionX <= 0) {
                    this.vitesseballex *= -1;
                    this.positionX= 1;

                }

                //Ecran Y
                if (this.positionY > CasseBrique.hauteur - getDiametre()) {
                    this.vitesseballey *= -1;
                    this.positionY = CasseBrique.hauteur - getDiametre()-1;
                }

                //Ecran Y
                if (this.positionY < 0) {
                    this.vitesseballey *= -1;
                    this.positionY = 1;
                }

            return trouve;
    }

    public boolean gameover(){
        boolean mort=false;
        if(!bonus && this.getPositionY()+this.getDiametre() >= CasseBrique.hauteur ){
            mort=true;
        }
        return mort;
    }

    public int getVitesseballe() {
        return vitesseballex;
    }

    public void setVitesseballe(int vitesseballe) {
        this.vitesseballex = vitesseballe;
    }

    public int getVitesseballey() {
        return vitesseballey;
    }

    public void setVitesseballey(int vitesseballey) {
        this.vitesseballey = vitesseballey;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

}
