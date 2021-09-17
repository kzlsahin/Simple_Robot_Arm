import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class robot_kolu extends PApplet {

int adimsayisi = 10;
int t = 10; //kolkalinligi
int n,c;
float xhedef, yhedef, xmhedef, ymhedef, xson, yson, Xmouse, Ymouse, xmdis, ymdis;
float l1, l2, ailk, qilk, altilk;
float xm, ym, am, qm;
float Y, alt;
float q = 0.f;//(ikinci kolun yatayla yaptığı başlangıç açısı
float a = 0.f;//birinci kolun yatayla yaptığı başlangıç açısı
boolean HAREKET = false;
public void setup(){
  //orientation(PORTRAIT); bu android için
  //fullScreen();
  
  
  background(100);
  textSize(20);
  mouseX=0;
  mouseY=500;
  am=0.f;
  qm=0.f;
  alt=0.f;
  l1 = 250.f;
  l2 = 300.f;
  xson = l1 + l2; yson = 0;
  xhedef = xson; yhedef = yson;
  xmdis=0;
  ymdis=0;
  
  n=0;  xm=0;  ym=0; c=adimsayisi;
  mousePressed=false;
  ailk=0; qilk=0; altilk=0;
}

public void draw(){
 
  if(HAREKET){
    
    if(n<adimsayisi-1){n=n+1; c=n+1; }
a = acos(am);
q = acos(qm);
//Kapanis
 if(n==adimsayisi-1){
        n=0;
        c=adimsayisi;
    xson=xhedef;
    yson=yhedef;
    ailk=a;
    qilk=q;
    altilk=alt;
    HAREKET=false;  }  


}
  background(100);
 
  pushMatrix();
  translate(200,500);
  line(0,0,cos(PI/4)*500,-500);
  line(0,0,cos(PI/4)*500,500);
  rotate(alt*c/adimsayisi);
  line(0,0,l1+l2,0);
   text(degrees(alt)+"  "+degrees(a)+"  "+degrees(q),350,50);
   text(xmdis+"  "+ymdis,350,150);
    popMatrix();
    text(mouseX+"   "+mouseY,mouseX,mouseY);
  pushMatrix();
  translate(200,500);
  rotate(altilk+(alt-altilk)*c/adimsayisi);
   kolGosterl1(a);
   kolGosterl2(a,q);
   popMatrix();


 delay(100);
}
public void mouseClicked(){
    Xmouse=mouseX-200; Ymouse=mouseY-500;
    
        if(!HAREKET){
  adim(Xmouse,Ymouse);  //hareket var mı kontrol et, varsa x2 ve y2 noktalarının adımları tanımlanıyor
 }
  //else 
  //text("hareketin bitmesini bekleyiniz",mouseX,mouseY);
  
}

public void kolGosterl1(float a){
  pushMatrix();
  translate(0,0);
  rotate(-(ailk+(a-ailk)*c/adimsayisi));
  //rotate(alt);
  fill(204, 102, 0);
  rect(0,0,l1,t);
  popMatrix();
}
  
 public void kolGosterl2(float a, float q){
  xmdis=l1-(l1*cos(-(ailk+(a-ailk)*c/adimsayisi)));
  ymdis=sin(-(ailk+(a-ailk)*c/adimsayisi))*l1;
  pushMatrix();
  translate(l1-xmdis,ymdis);
  rotate(qilk+(q-qilk)*c/adimsayisi);
  //rotate(alt);
  fill(204, 102, 0);
  rect(0,0,l2,(t));
  popMatrix();
  
}


public void adim(float x, float y){
xhedef = x;
yhedef = y;
if((xhedef != xson) || (yhedef != yson)){
  HAREKET = true;
  Y= sqrt(sq(xhedef)+sq(yhedef));
  alt=asin(yhedef/Y);      
    
    if(l1+l2-Y>0){
    am= (sq(l2)-sq(l1)-sq(Y))/(l1*Y*(-2));}//cos(a);
    else if(l1+l2-Y<=0){
    am=1;
    }        
    if(l1+l2-Y>0){
    qm = (sq(l1)-sq(l2)-sq(Y))/(l2*Y*(-2));//cos(q)
    }
    else if(l1+l2-Y<=0){
    qm=1;
    }   
}
}
  public void settings() {  size(1200, 850);  pixelDensity(displayDensity()); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "robot_kolu" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
