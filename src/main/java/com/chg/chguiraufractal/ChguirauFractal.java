/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.chg.chguiraufractal;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Iterator;

public class ChguirauFractal extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
//      g.drawLine(getWidth(), 0, 0, getHeight()); example how to draw and widht and heith
        Iterator<Line> il=listLines.iterator();
        while (il.hasNext())
        {
            Line aLine=il.next();
            g.drawLine(aLine.x,aLine.y, aLine.x2, aLine.y2);
        }

    }
    
    public void fractalTests(Graphics g, int xorig, int yorig , int xtarget, int ytarget, int nIterations, int asize)
    {
        System.out.println("x "+ xorig + " y " + yorig + " x2 " + xtarget + " y2 " + ytarget);
        int nextSize=(int)asize/3;
        
        double yratio=3.464101615137755; //square root (x/3pow2 -(x/2-x/3)pow2
        double xratio=0.5;

        //test hypothenuse
        // size is Math.sqrt(Math.pow(xorig-xtarget,2)-Math.pow(yorig-ytarget,2));
        
        //test draw line 1
        g.drawLine((int)(xorig+asize/3), yorig, (int)(xorig+asize/2), (int)(yorig-asize/Math.sqrt(12)));
        //test draw line 2
        g.drawLine((int)(xorig+2*asize/3), yorig, (int)(xorig+asize/2), (int)(yorig-asize/Math.sqrt(12)));

        //iteration 2
        //test draw line 1
        g.drawLine((int)(xorig+asize/3), yorig, (int)(xorig+asize/2), (int)(yorig-asize/Math.sqrt(12)));
        //test draw line 2
        g.drawLine((int)(xorig+2*asize/3), yorig, (int)(xorig+asize/2), (int)(yorig-asize/Math.sqrt(12)));

    }

    public static void fractal(int xorig, int yorig , double angle, int nIterations, int asize)
    {
        System.out.println("x "+ xorig + " y " + yorig + " angle " + angle + " size " + asize + " iterations " + nIterations);
        
        
        if ((nIterations > 0) && (asize > 2))        
        {
            //draw triagle 1
            double vertexx1=xorig+(asize/ratio)*Math.cos(angle);
            double vertexy1=yorig-(asize/ratio)*Math.sin(angle);
            double vertexx2=vertexx1+(asize/ratio)*Math.cos(angle+equilateralAngle);
            double vertexy2=vertexy1-(asize/ratio)*Math.sin(angle+equilateralAngle);
            double vertexx3=vertexx1+(asize/ratio)*Math.cos(angle);
            double vertexy3=vertexy1-(asize/ratio)*Math.sin(angle);
                    
            listLines.add(new Line ((int)vertexx1,(int)vertexy1,(int)vertexx2,(int)vertexy2));
            listLines.add(new Line ((int)vertexx1,(int)vertexy1,(int)vertexx3,(int)vertexy3));
            listLines.add(new Line ((int)vertexx2,(int)vertexy2,(int)vertexx3,(int)vertexy3));
            nIterations--;
            asize=(int)asize/3;
            fractal((int)vertexx1, (int)vertexy1, angle+equilateralAngle,nIterations,asize);
            fractalLeft((int)vertexx3, (int)vertexy3, angle-equilateralAngle,nIterations,asize);
        }
    }

    public static void fractalLeft(int xorig, int yorig , double angle, int nIterations, int asize)
    {
        double newAngle=angle+Math.toRadians(180);
        System.out.println("Left  - x "+ xorig + " y " + yorig + " angle " + angle + " size " + asize + " iterations " + nIterations);
        
        
        if ((nIterations > 0) && (asize > 2))        
        {
            //draw triagle 1
            double vertexx1=xorig+(asize/ratio)*Math.cos(newAngle);
            double vertexy1=yorig-(asize/ratio)*Math.sin(newAngle);
            double vertexx2=vertexx1+(asize/ratio)*Math.cos(newAngle-equilateralAngle);
            double vertexy2=vertexy1-(asize/ratio)*Math.sin(newAngle-equilateralAngle);
            double vertexx3=vertexx1+(asize/ratio)*Math.cos(newAngle);
            double vertexy3=vertexy1-(asize/ratio)*Math.sin(newAngle);
                    
            listLines.add(new Line ((int)vertexx1,(int)vertexy1,(int)vertexx2,(int)vertexy2));
            listLines.add(new Line ((int)vertexx1,(int)vertexy1,(int)vertexx3,(int)vertexy3));
            listLines.add(new Line ((int)vertexx2,(int)vertexy2,(int)vertexx3,(int)vertexy3));
            nIterations--;
            asize=(int)asize/3;
            fractal((int)vertexx3, (int)vertexy3, angle+equilateralAngle,nIterations,asize);
            fractalLeft((int)vertexx1, (int)vertexy1, angle-equilateralAngle,nIterations,asize);
        }
    }

    public static ArrayList<Line> listLines = new ArrayList<Line>();
    
    public static final double equilateralAngle = Math.toRadians(60);
    public static final int ratio=3;
    
    public static void main(String[] args) {
        /*
        //resolution screen 2560,1440
        int iterations=10;

        double x=650;
        double y=350;
        double x2=1750;
        double y2=350;
        int size=1100;
        double angle = 0; //we do not really need x2, y2, we use equilateral triangles for the fractal, so it is 60 grades
        listLines.add(new Line((int)x,(int)y,(int)x2,(int)y2));
        fractal((int)x, (int)y, angle, iterations, size);
        x2=x+(size*Math.cos(Math.toRadians(60)));;
        y2=y+(size*Math.sin(Math.toRadians(60)));
        angle=Math.toRadians(120);
        listLines.add(new Line((int)x,(int)y,(int)x2,(int)y2));
        fractal((int)x2, (int)y2, angle, iterations, size);
        x=1750;
        y=350;
        angle=Math.toRadians(240);
        listLines.add(new Line((int)x2,(int)y2,(int)x,(int)y));
        fractal((int)x, (int)y, angle, iterations, size);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ChguirauFractal());
                frame.setSize(2500,1420);
                frame.setVisible(true);
            }
        });
*/

        //resolution screen 1706,960
        
        int iterations=10;

        double x=430;
        double y=250;
        double x2=1160;
        double y2=250;
        int size=730;
        double angle = 0; //we do not really need x2, y2, we use equilateral triangles for the fractal, so it is 60 grades
        listLines.add(new Line((int)x,(int)y,(int)x2,(int)y2));
        fractal((int)x, (int)y, angle, iterations, size);
        x2=x+(size*Math.cos(Math.toRadians(60)));;
        y2=y+(size*Math.sin(Math.toRadians(60)));
        angle=Math.toRadians(120);
        listLines.add(new Line((int)x,(int)y,(int)x2,(int)y2));
        fractal((int)x2, (int)y2, angle, iterations, size);
        x=1160;
        y=250;
        angle=Math.toRadians(240);
        listLines.add(new Line((int)x2,(int)y2,(int)x,(int)y));
        fractal((int)x, (int)y, angle, iterations, size);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ChguirauFractal());
                frame.setSize(2500,1420);
                frame.setVisible(true);
            }
        });
        
        
        
    }
}