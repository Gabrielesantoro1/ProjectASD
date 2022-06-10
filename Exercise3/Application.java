package Exercise3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Exercise3.src.ComparatorInteger;
import Exercise3.src.ComparatorString;
import Exercise3.src.MinHeap;
import Exercise3.src.MinHeapException;

/**
 * Application class where it is possible to try our library MinHeap
 */
public class Application {
    public static void main(String[] args) {
        /**
         * MinHeap for Integer data
         */
          ComparatorInteger compInt = new ComparatorInteger();
          Random r = new Random();
          Scanner scanner = new Scanner(System.in);
  
          try{
            ArrayList<Integer> array = new ArrayList<>();
            MinHeap<Integer> heap = new MinHeap<>(compInt);
            heap.setArray(array);
            Boolean choice = true;
  
            System.out.println("Let's build a minHeap! How many random elements you want the program to insert? ");
            Integer elem = scanner.nextInt();
              choice = false;
          
            for(int k = 0; k<elem; k++){
            int x = (r.nextInt(200)+1);
              heap.minHeapInsert(x);   
            }
  
            System.out.println("Do you want to print your minHeap? y/n");
            String print = scanner.nextLine();
            if(print.equals("y"))
              System.out.println(heap.toString());
  
            while(choice){
              System.out.println("What function of the heap would you like to try? The possible choices are:" );
              System.out.println("in for insert; ex for extractMin; dec for DecreaseKey; getp for getFather; getl for GetLeft; getr for GetRight ");
              System.out.println("If you want to exit write exit");
              String fun = scanner.nextLine();
  
              switch(fun){
                case "in":
                System.out.println("Write the element to insert: ");
                Integer j = scanner.nextInt();
                heap.minHeapInsert(j);
                System.out.println("Now the heap is "+heap.toString());
                break;
  
                case "ex":
                System.out.println("You choose extractmin! The element extracted is: "+heap.heapExtractMin());
                System.out.println("Heap now : "+heap.toString());
                break;
  
                case "dec":
                System.out.println("What's the index of the element you want to decrease?");
                Integer index = scanner.nextInt();
                System.out.println("Now insert the element you wnat to put at that index");
                Integer key = scanner.nextInt();
                heap.heapDecreaseKey(index, key);
                System.out.println("Heap actually : "+heap.toString());
                break;
  
                case "getp":
                System.out.println("insert the index of the element whose father you want to get: ");
                Integer k = scanner.nextInt();
                System.out.println(heap.getParentElem(array, k));
                break;
  
                case "getl":
                System.out.println("insert the index of the element whose left son you want to get: ");
                Integer m = scanner.nextInt();
                System.out.println(heap.getLeftElem(array, m));
                break;
  
                case "getr":
                System.out.println("insert the index of the element whose lright son you want to get: ");
                Integer n = scanner.nextInt();
                System.out.println(heap.getRightElem(array, n));
                break;
  
                case "exit":
                  choice = false;
                  break;
  
              }
  
            }
            
          }catch(MinHeapException e){
            e.printStackTrace();
          }
          System.out.println("");
  
          /**
           * MinHeap for String data 
           */
          ComparatorString compStr = new ComparatorString();
          ArrayList<String> array_str = new ArrayList<>();
          Boolean choice = true;
            try{
              System.out.println("Do you want to build a heap with Strings? y/n");
              choice = scanner.nextLine().equals("y") ? true : false;
              if(choice){
                MinHeap<String> heap_str = new MinHeap<>(compStr);
                heap_str.setArray(array_str);
  
                System.out.println("First of all, we have to insert some elements; how many?");
                Integer x = scanner.nextInt();
  
                for(int j=0;j<x;j++){
                  String s = scanner.next();
                  heap_str.minHeapInsert(s);
                }
  
                System.out.println("Let's print the heap : "+heap_str.toString());
                while(choice){
                  System.out.println("Now what function of the heap would you like to try? The possible choices are:" );
                  System.out.println("in for insert; ex for extractMin; dec for DecreaseKey; getp for getFather; getl for GetLeft; getr for GetRight ");
                  System.out.println("If you want to exit write exit");
                  String fun = scanner.nextLine();
  
                  switch(fun){
                    case "in":
                    System.out.println("Write the element to insert: ");
                    String j = scanner.nextLine();
                    heap_str.minHeapInsert(j);
                    System.out.println("Now the heap is "+heap_str.toString());
                    break;
  
                    case "ex":
                    System.out.println("You choose extractmin! The element extracted is: "+heap_str.heapExtractMin());
                    System.out.println("Heap now : "+heap_str.toString());
                    break;
  
                    case "dec":
                    System.out.println("What's the index of the element you want to decrease?");
                    Integer index = scanner.nextInt();
                    System.out.println("Now insert the element you wnat to put at that index");
                    String key = scanner.nextLine();
                    heap_str.heapDecreaseKey(index, key);
                    System.out.println("Heap actually : "+heap_str.toString());
                    break;
  
                    case "getp":
                    System.out.println("insert the index of the element whose father you want to get: ");
                    Integer k = scanner.nextInt();
                    System.out.println(heap_str.getParentElem(array_str, k));
                    break;
  
                    case "getl":
                    System.out.println("insert the index of the element whose left son you want to get: ");
                    Integer m = scanner.nextInt();
                    System.out.println(heap_str.getLeftElem(array_str, m));
                    break;
  
                    case "getr":
                    System.out.println("insert the index of the element whose lright son you want to get: ");
                    Integer n = scanner.nextInt();
                    System.out.println(heap_str.getRightElem(array_str, n));
                    break;
  
                    case "exit":
                      choice = false;
                      break;
                  }
                }
            }else{
              System.out.println("Ok, let's exit");
            }
              
            }catch(MinHeapException e){
              e.printStackTrace();
            }
          scanner.close();
          System.out.println("");
    }
}
