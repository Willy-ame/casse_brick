package edu.wleg.cassebrique;

import java.awt.*;

public abstract class Sprite{
        protected int positionX;
        protected int positionY;
        protected Color unecouleur;

        public Sprite(int positionX, int positionY, Color unecouleur) {
                this.positionX = positionX;
                this.positionY = positionY;
                this.unecouleur = unecouleur;
        }

        public abstract void dessiner(Graphics2D dessin);
        public int getPositionX() {
                return positionX;
        }

        public void setPositionX(int positionX) {
                this.positionX = positionX;
        }

        public int getPositionY() {
                return positionY;
        }

        public void setPositionY(int positionY) {
                this.positionY = positionY;
        }

        public Color getUnecouleur() {
                return unecouleur;
        }

        public void setUnecouleur(Color unecouleur) {
                this.unecouleur = unecouleur;
        }


}
