import java.util.*;



class Leitner
{
    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("For how many boxes You want to Schedule the learning?");
        System.out.println("-------------------------------------------------------");
        int noOfBox = scan.nextInt();
        while(noOfBox <= 0){
            System.out.println("please enter greater then 0");
            noOfBox = scan.nextInt();
        }
        System.out.println("");
        System.out.println("Upto what numbers you want to learn?(max learn 999)");
        System.out.println("-------------------------------------------------------");
        int noOfCount = scan.nextInt();
        while(noOfCount <= 0){
            System.out.println("please enter greater then 0");
            noOfCount = scan.nextInt();
        }
        System.out.println("");

        LearnCount learnCount = new LearnCount(noOfBox, noOfCount);
        learnCount.start();
        scan.close();
    }
}

class LearnCount{

    private int noOfBox;
    private int noOfCount;
    private ArrayList<Integer> boxes[];
    private Counting count = new Counting();

    LearnCount(int noOfBox, int noOfCount){
        this.noOfBox = noOfBox;
        this.noOfCount = noOfCount;
        boxes = new ArrayList[noOfBox+1];
        for(int i=1; i<=noOfBox; i++){
            boxes[i] = new ArrayList<Integer>();
        }
        for(int i=1; i<=noOfCount; i++){
            boxes[1].add(i);
        }
    }

    public void start(){
        Scanner scan = new Scanner(System.in);
        this.intro();
        while(noOfCount >0){
            ArrayList<Integer> newBoxes[] = new ArrayList[noOfBox+1];
            for(int i=1; i<=noOfBox; i++){
                newBoxes[i] = new ArrayList<Integer>();
            }
            for(int i=1; i<=noOfBox; i++){                                        // iterate throug all boxex
                ArrayList<Integer> currentBox = boxes[i];
                ArrayList<Integer> correct = new ArrayList<Integer>();
                ArrayList<Integer> notCorrect = new ArrayList<Integer>();
                if(i == 1){
                    for(int j=0; j<currentBox.size(); j++){                       //iterate through all boxex element
                        int currentNumber = currentBox.get(j);
                        System.out.println("Enter spelling for "+currentNumber);
                        if(count.convertNumString(currentNumber).equals(scan.next().toLowerCase())){
                            correct.add(currentNumber);
                        }
                        else{
                            notCorrect.add(currentNumber);
                        }
                    }
                    newBoxes[1].addAll(notCorrect);
                    newBoxes[2].addAll(correct);
                    System.out.println("");
                }
                else if(i == noOfBox){
                    for(int j=0; j<currentBox.size(); j++){                       //iterate through all boxex element
                        int currentNumber = currentBox.get(j);
                        System.out.println("Enter spelling for "+currentNumber);
                        if(count.convertNumString(currentNumber).equals(scan.next().toLowerCase())){
                            correct.add(currentNumber);
                            noOfCount--;
                        }
                        else{
                            notCorrect.add(currentNumber);
                        }
                    }
                    newBoxes[1].addAll(notCorrect);
                    System.out.println("");
                }
                else{
                    for(int j=0; j<currentBox.size(); j++){                       //iterate through all boxex element
                        int currentNumber = currentBox.get(j);
                        System.out.println("Enter spelling for "+currentNumber);
                        if(count.convertNumString(currentNumber).equals(scan.next().toLowerCase().trim())){
                            correct.add(currentNumber);
                        }
                        else{
                            notCorrect.add(currentNumber);
                        }
                    }
                    newBoxes[i+1].addAll(correct);
                    newBoxes[1].addAll(notCorrect);
                    System.out.println("");
                }
            }
            boxes = newBoxes;
            this.display();
        }
        scan.close();
    }

    public void display(){
        for(int k=1; k<boxes.length; k++){
            System.out.println("The size for box "+k+" is "+boxes[k].size()+" and contains "+boxes[k]);
        }
        System.out.println("-------------------------------------------------------\n");
    }

    public void intro(){
        System.out.println("lets first you give a read to all number before start:");
        for(int i=1; i<=noOfCount; i++){
            System.out.println(i+": "+count.convertNumString(i));
        }
        System.out.println("-------------------------------------------------------\n");
    }
}

class Counting{
    private String[] _units = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
    private String[] _tens = new String[]{"zero","ten","twenty","thirty","forty","fifty","sixty","seventy","eightty","ninty"};
    private String _hundred = "hundred";
    private String[] _teens = new String[]{"zero","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};

    Counting(){
    }
    public String convertNumString(int num)
    {
        if(num>=1 && num<=9){
            return _units[num];
        }
        else if(num>=11 && num<=19){
            return _teens[num%10];
        }
        else if((num>=20 && num<=99) || num==10){
            int unit = num%10;
            int tens = (num/10)%10;
            if(unit == 0){
                return _tens[tens];
            }
            else{
                return _tens[tens] +" "+ _units[unit];
            }

        }
        else                             //if(num>=100 && num<=999)
        {
            int unit = num%10;
            int tens = (num/10)%10;
            int hundred = (num/100)%10;
            if(unit==0 && tens==0){
                return _units[hundred] +" "+_hundred;
            }
            else 
                return _units[hundred] +" "+_hundred+" "+ convertNumString(num%100); 
        }
    } 
}
