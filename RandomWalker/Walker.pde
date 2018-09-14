class Walker {
 float x;
 float y;
 
 Walker(float x, float y) {
   this.x = x;
   this.y = y;
 }
 
 void move() {
   
   float value = random(1,5);
   switch((int)value)
   {
     case 1:
     x++;
     break;
     case 2:
     x--;
     break;
     case 3:
     y++;
     break;
     case 4:
     y--;
     break;
    default: break;
   }
 }
 void render() {
   ellipseMode(CENTER);
   ellipse(x,y,1,1);
 }

}
