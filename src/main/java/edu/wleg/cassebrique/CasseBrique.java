package edu.wleg.cassebrique;


import javax.swing.*;
import edu.wleg.cassebrique.models.*;
import edu.wleg.cassebrique.models.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CasseBrique extends Canvas {

   public static int largeur = 300;
   public static int hauteur = 350;
   public static boolean continuepartie = true;

    private int largeurbarre = 100;
    private int hauteurbarre = 20;
    Barre labarre = new Barre((largeur/2)-(100/2),hauteur-(hauteur/8),Color.DARK_GRAY,largeurbarre,hauteurbarre,false);
    Balle laballe = new Balle(labarre.getPositionX()+(labarre.getLargeur()/2)-13,labarre.getPositionY()-25,Color.CYAN,26,0,0,false);
    ArrayList<Rectangle>lesrectangles=new ArrayList<>();
    ArrayList<Bonus> lesbonus= new ArrayList<>();
    ArrayList<Balle> lesballes = new ArrayList<>();

    static int compte=0; //Compter nombre de bricks cassé
    static boolean start = false; //permet de controler

    public CasseBrique() throws InterruptedException  {

        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeur, hauteur));
        setBounds(0, 0, largeur,hauteur);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();
        fenetre.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case 32:
                        if(!start){
                            laballe.setVitesseballe(nombrealeatoire(0,1,1));
                            laballe.setVitesseballey(3);
                            start=true;
                        }
                        break;
                    case 37:
                        labarre.deplacementgauche(laballe);
                        if(laballe.getVitesseballe() == 0 && laballe.getVitesseballey() == 0){
                            laballe.resetballe(labarre);
                        }
                    break;
                    case 39:
                        labarre.deplacementdroite(laballe);
                        if(laballe.getVitesseballe() == 0 && laballe.getVitesseballey() == 0){
                            laballe.resetballe(labarre);
                        }
                        break;
                }
            }
        });

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        do {
             start = false;

            //Reset ma barre et ma balle.
            labarre.reset(hauteur,largeur,largeurbarre,hauteurbarre);
            laballe.resetballe(labarre);
            int largeurrectangle=60;
            int hauteurrectangle=25;
            int nbrectanglex = largeur/largeurrectangle;
            int nbrectangley = ((hauteur/2)/hauteurrectangle);
            int espacex=0;
            int espacey=0;

            for (int i =0;i<nbrectangley;i++){
                for (int y =0;y<nbrectanglex;y++){
                    lesrectangles.add(new Rectangle(espacex,espacey,Color.GREEN,largeurrectangle,hauteurrectangle,true));
                    espacex=espacex+largeurrectangle;
                }
                espacex=0;
                espacey=espacey+hauteurrectangle;
            }

            lesrectangles.add(labarre);
            lesballes.add(laballe);

            boolean resul = demarrer();
            continuepartie =resul;
        }
        while(continuepartie);
        fenetre.dispose();
    }

    public boolean demarrer() throws InterruptedException {


        boolean start = true; //Quitte le jeux / ou recommence
        while(start) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.white);
            dessin.fillRect(0,0,largeur,hauteur);


            ArrayList<Rectangle> Controlemesrectangles = new ArrayList<>();
            ArrayList<Balle> Controlemesballes = new ArrayList<>();
            for (Balle uneballe: lesballes) {

                uneballe.mouve();
                uneballe.dessiner(dessin);
                //Game over - perdu
                if (uneballe.gameover()){
                    start= winordead("Perdu");
                }

                boolean verifballebonus= uneballe.collisionbonus(labarre);

                if(verifballebonus){Controlemesballes.add(uneballe);}

                for (Rectangle unrec : lesrectangles) {
                    unrec.dessiner(dessin);

                    boolean trouve = uneballe.collision(unrec);

                    if(trouve && unrec.isRectangle()) {
                        unrec.setcomptecouleur(1);
                        if (unrec.getcompteurcouleur() == 3) {
                            lesbonus.add(new Bonus(unrec.positionX + (unrec.getLargeur() / 2) - 5, unrec.positionY + unrec.getHauteur(), Color.magenta, 10));
                            Controlemesrectangles.add(unrec);
                            compte++;
                        }
                    }
                 }
            }
            if(lesballes!=null)for (Balle balle:Controlemesballes){ lesballes.remove(balle);}
            if(lesrectangles!=null)for (Rectangle rectangle:Controlemesrectangles){ lesrectangles.remove(rectangle);}


            int nombreAleatoire=0;
            //Pour les bonus qui arrivent en même temps
            ArrayList<Bonus> Controlemesbonus = new ArrayList<>();
            for (Bonus lebonus :lesbonus) {
                lebonus.mouve();
                lebonus.dessiner(dessin);
                boolean verif = lebonus.collision(labarre,hauteur);
                if(verif) {
                    Controlemesbonus.add(lebonus);
                    if (lebonus.getEndroit() == 1) {
                        nombreAleatoire = nombrealeatoire(1, 2, 0);
                    }
                }

                switch (nombreAleatoire){
                    case 1: //Agrandir Barre
                        int taille = 20;
                        labarre.setLargeur(labarre.getLargeur()+20);
                        labarre.setPositionX(labarre.getPositionX()-10);
                        break;
                    case 2: //Géneration Balle
                        lesballes.add(new Balle(((labarre.getPositionX()+labarre.getLargeur())/2),
                                labarre.getPositionY()-25,Color.BLUE,25,nombrealeatoire(0,1,1),-1,true));
                        break;
                }
                nombreAleatoire=0;
            }
            if(lesbonus!=null) for (Bonus bonus: Controlemesbonus) {lesbonus.remove(bonus);}

            //Win - Gagné
            if(Rectangle.CompteBricks==compte && start)start = winordead("Gagné");


            if(!start){
                lesballes.clear();
                lesrectangles.clear();
                lesbonus.clear();
            }


            getBufferStrategy().show();
            Thread.sleep(1000/60);

            dessin.dispose();

        }
        return continuepartie;
    }

    public boolean winordead(String Titre){
        System.out.println("appel");
        boolean start = false;
        compte=0;
        Rectangle.CompteBricks=0;
        int a = JOptionPane.showConfirmDialog(this, "Voulez vous recommencez ?", Titre, JOptionPane.YES_OPTION);
        if(a == 0)continuepartie=true;
        else  continuepartie=false;
        return start;
    }

    public int nombrealeatoire(int min , int max,int parametre){
        int nombreAleatoire = min + (int)(Math.random() * ((max - min) + 1));
        if(parametre==1){
            if(nombreAleatoire == 0) nombreAleatoire=-1;}
        return nombreAleatoire;
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}