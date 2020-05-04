package project0;

public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    //first constructor
    public Planet (double xP, double yP, double xV,
        double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    //second constructor

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //add a calcDistance method to calculate distances between two plantes
    public double calcDistance(Planet t){
        return Math.sqrt((this.xxPos - t.xxPos)*(this.xxPos - t.xxPos)
                        + (this.yyPos - t.yyPos)*(this.yyPos - t.yyPos));
    }

    // calculate Force Exerted By
    public double calcForceExertedBy(Planet x){
        double distance = this.calcDistance(x);
        return 6.67e-11 * this.mass*x.mass/distance/distance;
    }

    //calculate x force

    public double calcForceExertedByX(Planet x){
     double exertedForce = this.calcForceExertedBy(x);
        double distance = this.calcDistance(x);
     return exertedForce * (x.xxPos - this.xxPos)/distance;
    }


    //calculate y force
    public double calcForceExertedByY(Planet x){
        double exertedForce = this.calcForceExertedBy(x);
        double distance = this.calcDistance(x);
        return exertedForce * (x.yyPos - this.yyPos)/distance;
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double NetForceExertedByX =0;
        for (Planet x: planets){
            if (!this.equals(x)){
                double exertedForceX = this.calcForceExertedByX(x);
                NetForceExertedByX += exertedForceX;
            }
        }
        return NetForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double NetForceExertedByY =0;
        for (Planet x: planets){
            if (!this.equals(x)){
                double exertedForceY = this.calcForceExertedByY(x);
                NetForceExertedByY += exertedForceY;
            }
        }
        return NetForceExertedByY;
    }

    public void update(double dt,double fX, double fY){
        double ax = fX/this.mass;
        double ay = fY/this.mass;
        this.xxVel = this.xxVel + dt* ax;
        this.yyVel = this.yyVel + dt* ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.setScale(-200.0D, 200.0D);
        StdDraw.picture(this.xxPos/1e+9/2,this.yyPos/1e+8/2, "images/"+this.imgFileName);


    }







}
