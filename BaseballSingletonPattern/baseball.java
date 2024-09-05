import java.io.Console;

/**
 *
 * @author mlegi
 */
interface ThrowBehavior{
    public void performThrow();
}
class Pitching implements ThrowBehavior{
    @Override
    public void performThrow(){
        System.out.print(" is throwing the ball to the catcher.\n");
    }
}
class notThrowing implements ThrowBehavior{
    @Override
    public void performThrow(){
        System.out.print(" didn't throw the ball.\n");
    }
}
class fielding implements ThrowBehavior{

    @Override
    public void performThrow() {
        System.out.print(" threw the ball to another fielder\n");
    }
    
}
class backToPitcher implements ThrowBehavior{
    @Override
    public void performThrow(){
        System.out.print(" threw it back to pitcher.\n");
    }
}


//////////////////////////////////END OF THROW BEHAVIORS
interface CatchBehavior{
    public void performCatch();
}
class caughtWithGlove implements CatchBehavior{
    @Override
    public void performCatch() {
      System.out.print(" caught the ball with his glove.\n");  
    }
}
class bareHands implements CatchBehavior{
    @Override
    public void performCatch() {
        System.out.print(" caught the ball with his bare hands. \n");
    }
}    
class toCatcher implements CatchBehavior{
    @Override
    public void performCatch() {
        System.out.print(" caught the ball and threw it back to the pitcher\n");
    }
}
class notCatching implements CatchBehavior{

    @Override
    public void performCatch() {
        System.out.print(" did not catch the ball.\n");
    }
}
//////////////////////END OF CATCH BEHAVIORS
interface BattingBehavior{
    public void performBat();
}
class notBatting implements BattingBehavior{

    @Override
    public void performBat() {
        System.out.print(" swung by not batting\n");
    }
}
class BatBySwingingForContact implements BattingBehavior{

    @Override
    public void performBat() {
         System.out.print(" swings by focusing on contact.\n");
    }
}

class SwingForPower implements BattingBehavior{

    @Override
    public void performBat() {
         System.out.print(" swung for power.\n");
    }
    
}
///////////////////////////END OF BATTING BEHAVIORS



class Pitcher extends baseball{//Pitcher class
   ThrowBehavior throwBehavior; 
   CatchBehavior catchBehavior;
   BattingBehavior battingBehavior;
   String name = null;

   public Pitcher() {
       throwBehavior = new Pitching();
       catchBehavior = new caughtWithGlove();
       battingBehavior = new notBatting();
        }
   @Override
   public void SetName(String n ){
       name = n;
   }
   @Override
   public void PerformThrow(){
       this.Display();
       throwBehavior.performThrow();
   }
   @Override
    public void PerformBat(){
        this.Display();
    battingBehavior.performBat();}
   @Override
    public void PerformCatch(){
        this.Display();
    catchBehavior.performCatch();}
   @Override
    public void SetBattingBehavior(BattingBehavior b){
    battingBehavior = b;}
   @Override
    public void SetCatchingBehavior(CatchBehavior c){
    catchBehavior =c;}
   @Override
    public void SetThrowingBehavior(ThrowBehavior t){
    throwBehavior = t;}
   @Override
    public void Display(){
    System.out.print(name);}
   }
class Batter extends baseball{
   ThrowBehavior throwBehavior; 
   CatchBehavior catchBehavior;
   BattingBehavior battingBehavior;
   String name;

    public Batter() {
        throwBehavior = new notThrowing();
        catchBehavior = new bareHands();
        battingBehavior = new BatBySwingingForContact();
    }
    @Override
   public void SetName(String n ){
       name = n;
   }
   @Override
   public void PerformThrow(){
       this.Display();
       throwBehavior.performThrow();
   }
   @Override
    public void PerformBat(){
        this.Display();
    battingBehavior.performBat();}
   @Override
    public void PerformCatch(){
        this.Display();
    catchBehavior.performCatch();}
   @Override
    public void SetBattingBehavior(BattingBehavior b){
    battingBehavior = b;}
   @Override
    public void SetCatchingBehavior(CatchBehavior c){
    catchBehavior =c;}
   @Override
    public void SetThrowingBehavior(ThrowBehavior t){
    throwBehavior = t;}
   @Override
    public void Display(){
    System.out.print(name);}
    
}
class Fielder extends baseball{
   ThrowBehavior throwBehavior; 
   CatchBehavior catchBehavior;
   BattingBehavior battingBehavior;
   String name = null;
    
   public Fielder(){
       throwBehavior = new fielding();
       catchBehavior = new caughtWithGlove();
       battingBehavior = new notBatting();
   }
    @Override
   public void SetName(String n ){
       name = n;
   }
   @Override
   public void PerformThrow(){
       this.Display();
       throwBehavior.performThrow();
   }
   @Override
    public void PerformBat(){
        this.Display();
    battingBehavior.performBat();}
   @Override
    public void PerformCatch(){
        this.Display();
    catchBehavior.performCatch();}
   @Override
    public void SetBattingBehavior(BattingBehavior b){
    battingBehavior = b;}
   @Override
    public void SetCatchingBehavior(CatchBehavior c){
    catchBehavior =c;}
   @Override
    public void SetThrowingBehavior(ThrowBehavior t){
    throwBehavior = t;}
   @Override
    public void Display(){
    System.out.print(name);}
    
}
class PinchHitter extends baseball{
   ThrowBehavior throwBehavior; 
   CatchBehavior catchBehavior;
   BattingBehavior battingBehavior;
   String name = null;
       public PinchHitter(){
           throwBehavior = new notThrowing();
           catchBehavior = new notCatching();
           battingBehavior = new SwingForPower();
       }
        @Override
   public void SetName(String n ){
       name = n;
   }
   @Override
   public void PerformThrow(){
       this.Display();
       throwBehavior.performThrow();
   }
   @Override
    public void PerformBat(){
        this.Display();
    battingBehavior.performBat();}
   @Override
    public void PerformCatch(){
        this.Display();
    catchBehavior.performCatch();}
   @Override
    public void SetBattingBehavior(BattingBehavior b){
    battingBehavior = b;}
   @Override
    public void SetCatchingBehavior(CatchBehavior c){
    catchBehavior =c;}
   @Override
    public void SetThrowingBehavior(ThrowBehavior t){
    throwBehavior = t;}
   @Override
    public void Display(){
    System.out.print(name);}
}
   class Catcher extends baseball{
   ThrowBehavior throwBehavior; 
   CatchBehavior catchBehavior;
   BattingBehavior battingBehavior;
   String name = null;
   public Catcher(){
       throwBehavior = new backToPitcher();
       catchBehavior = new toCatcher();
       battingBehavior = new notBatting();
   }
    public void SetName(String n ){
       name = n;
   }
   @Override
   public void PerformThrow(){
       this.Display();
       throwBehavior.performThrow();
   }
   @Override
    public void PerformBat(){
        this.Display();
    battingBehavior.performBat();}
   @Override
    public void PerformCatch(){
        this.Display();
    catchBehavior.performCatch();}
   @Override
    public void SetBattingBehavior(BattingBehavior b){
    battingBehavior = b;}
   @Override
    public void SetCatchingBehavior(CatchBehavior c){
    catchBehavior =c;}
   @Override
    public void SetThrowingBehavior(ThrowBehavior t){
    throwBehavior = t;}
   @Override
    public void Display(){
    System.out.print(name);}
}
/////////////////////////////BaseBall class

public abstract class baseball {
    String name = null;
    ThrowBehavior throwBehavior;
    CatchBehavior catchBehavior;
    BattingBehavior battingBehavior;
    public baseball(){}
    public void SetName(String n){}
    public void PerformThrow(){}
    public void PerformBat(){}
    public void PerformCatch(){}
    public void SetBattingBehavior(BattingBehavior b){}
    public void SetCatchingBehavior(CatchBehavior c){}
    public void SetThrowingBehavior(ThrowBehavior t){}
    public void Display(){}


    public static void main(String[] args) {
 Pitcher jb = new Pitcher(); 
 jb.SetName("Jim  B");  
 jb.Display();
 System.out.println();
 jb.PerformThrow();
 jb.PerformCatch();
 jb.PerformBat();
 Batter pedro = new Batter();
 pedro.SetName("Pedro A");
 pedro.Display();
 System.out.println();
 pedro.PerformThrow();
 pedro.PerformCatch();
 pedro.PerformBat();
 Fielder max = new Fielder();
 max.SetName("Max M");
 max.Display();
 System.out.println();
 max.PerformThrow();
 max.PerformCatch();
 max.PerformBat();
 Catcher chris = new Catcher();
 chris.SetName("Chris S");
 System.out.println();
 chris.Display();
 System.out.println();
 chris.PerformThrow();
 chris.PerformCatch();
 chris.PerformBat();
 System.out.println("JB the pitcher is crazy and confused.");
 jb.SetBattingBehavior(new BatBySwingingForContact());
 jb.Display();
 System.out.println();
 jb.PerformBat();
 PinchHitter bobcat = new PinchHitter();
 bobcat.SetName("Bobcat");
 bobcat.Display();
 System.out.println();
 bobcat.PerformThrow(); 
 bobcat.PerformCatch();
 bobcat.PerformBat();
    }
}
