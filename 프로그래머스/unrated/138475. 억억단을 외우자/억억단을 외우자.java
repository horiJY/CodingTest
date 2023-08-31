import java.util.Arrays;

class Solution {
    public int[] solution(int e, int[] starts) {
        int[] numAppearCount = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                numAppearCount[j]++;
            }
        }

        int[] cache = new int[e+1];            
        cache[e] = e;
        for (int i = e-1; i > 0; i--) {
               if(numAppearCount[i] < numAppearCount[cache[i+1]]){
                   cache[i] = cache[i+1];
               }else{
                   cache[i] = i;
               }
        }
        
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = cache[starts[i]];   
        }


        return answer;
    }
}