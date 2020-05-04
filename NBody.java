package project0;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class NBody {



    public static double readRadius(String fileName){
        In in;
        int count =1;
        double radius = 0;
        try {
            in = new In(fileName);
            while (!in.isEmpty()& count <3) {
                String s = in.readString();
                radius = Double.parseDouble(s);
                System.out.println(s);
                count ++;
            }
        }
        catch (Exception e) { System.out.println(e); }
        return radius;
    }

    public static Planet[] readPlanets (String fileName){
        In in;
        int count =1;
        ArrayList<Planet> planets = new ArrayList<Planet>();
        in = new In(fileName);
        System.out.println("hello"+in.readDouble());
        System.out.println("hello1"+in.readDouble());
        try {
               while (in.hasNextLine()) {
                    Planet p = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
                    planets.add(p);
                }
            }
        catch (Exception e) { System.out.println(e); }
        return planets.toArray(new Planet[0]);
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        double T = in.nextDouble();
        System.out.println("T is hello" + T);
        double dt = in.nextDouble();
        System.out.println("dt is hello " + dt);

        System.out.println("filename is " + filename);

        Double radius = readRadius(filename);
        Planet[] planetArray  = readPlanets(filename);
        drawOne("images/starfield.jpg");
        for (Planet p: planetArray){
            p.draw();
        }

        StdDraw.enableDoubleBuffering();
        for (int i=0; i<T; i+=dt){
            double [] xForces = new double[5];
            double [] yForces = new double[5];
            for (int j =0; j<5; j++){
                xForces[j] = planetArray[j].calcNetForceExertedByX(planetArray);
                yForces[j] = planetArray[j].calcNetForceExertedByY(planetArray);
                planetArray[j].update(dt,xForces[j],yForces[j]);
                System.out.println("x position" + planetArray[j].xxPos);
                System.out.println("x position" + planetArray[j].yyPos);
            }
            drawOne("images/starfield.jpg");
            for (Planet p: planetArray){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(50);
            System.out.println("hello");
        }







    }

    public static void drawOne(String imageToDraw) {
        StdDraw.setScale(-300.0D, 300.0D);
        StdDraw.clear();
        StdDraw.picture(100D, 100.0D, imageToDraw);
        StdDraw.show();
        StdDraw.pause(2000);
    }



}
