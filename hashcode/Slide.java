package hashcode;

import java.util.*;

//Slide class with all the attributes necessary
public class Slide {
    int noOfTags;
    char orientation;
    String[] tags = new String[noOfTags];
    
    public Slide(){}
    public Slide(char orientation, int noOfTags, String[] tags){
        this.orientation=orientation;
        this.noOfTags=noOfTags;
        this.tags=tags;
    }
    
    //gets minimum of two values
    static int min(int i, int k){
        int min = i<k? i : k;
        return min;
    }
    
    //gets minimum of three values
    //we will need this to get the min of {intersection, set difference of i-j, and set difference j-i}
    static int min(int i, int j, int k){
        int varMin = min(i, j) < min(j, k)? min(i, j): min(j, k);
        return varMin;
    }
    
    //we use the min methods stated above to get calculate the interest function
    static int getMin(Slide slide, Slide slide0){

        //we use a set object for storing the tags
        Set<String> set= new HashSet<>();
        int i = 0;
        for(; i<slide.tags.length; i++){
            set.add(slide.tags[i]);
        }
        i=0;
        
        for(; i<slide0.tags.length; i++){
            set.add(slide0.tags[i]);
        }
        
        //we take adavantage of the fact that sets do not alow for replicative data entry
        if(set.size()<(slide.noOfTags+slide0.noOfTags)){
            i=0;
            int intersection, i_j=0, j_i=0;
            for(; i<set.size(); i++){
                if(set.contains(slide.tags[i]) && !Arrays.stream(slide0.tags).anyMatch(slide.tags[i]::equals)) i_j++;
                if(set.contains(slide0.tags[i]) && !Arrays.stream(slide.tags).anyMatch(slide0.tags[i]::equals)) j_i++;
            }
            intersection = (slide.noOfTags+slide0.noOfTags)-set.size();
            return min(intersection, i_j, j_i);
        }
        
        //if there is no intersection the minimum will always be 0
        else return 0;
    }
    
    public static Slide[] interestCalculator(Slide[] show){
        int i=1, currentSmallest=getMin(show[0], show[1]);
        for(; i<show.length-1; i++){
            int j= getMin(show[i], show[i+1]);
            if(j > currentSmallest){
                Slide temp = show[i];
                show[i]=show[i+1];
                show[i+1]=temp;
            }            
        }
       return show; 
    }
}
