

public class Planet {
    private static final double g = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        return (g * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
        double dist = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return (p.xxPos - this.xxPos) / dist * force;
    }

    public double calcForceExertedByY(Planet p){
        double dist = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        return (p.yyPos - this.yyPos) / dist * force;
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double res = 0;
        for (Planet p:planets) {
            if(!p.equals(this)) res += calcForceExertedByX(p);
        }
        return res;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double res = 0;
        for (Planet p:planets) {
            if(!p.equals(this)) res += calcForceExertedByY(p);
        }
        return res;
    }
    public void update(double duration,double xxForce,double yyForce){
        double xxAcc = xxForce / this.mass;
        double yyAcc = yyForce / this.mass;
        double newXXVel = this.xxVel + xxAcc * duration;
        double newYYVel = this.yyVel + yyAcc * duration;
        this.xxVel = newXXVel;
        this.yyVel = newYYVel;
        this.xxPos = this.xxPos + duration * newXXVel;
        this.yyPos = this.yyPos + duration * newYYVel;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }
}
