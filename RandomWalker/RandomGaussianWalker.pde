import java.util.Random;
class RandomGaussianWalker {
 float mean ;
 float sd;
 float xx;
 Random random;
  
 RandomGaussianWalker(float mean, float sd) {
   
   this.mean = mean;
   this.sd = sd;
   random = new Random();
 }
 
 void move() {
   
   float value = (float) random.nextGaussian();
    
   value = value*sd;
   value = value+ mean;
   xx = value;
 }
 void render() {
   ellipseMode(CENTER);
   
   ellipse(xx,height/2,2,1);
 }

}
