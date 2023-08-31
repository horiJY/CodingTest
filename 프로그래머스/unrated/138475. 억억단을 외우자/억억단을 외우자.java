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
//         int[][] numAppearSort = new int[e][2];
//         for (int i = 1; i <= e; i++) {
//             numAppearSort[i - 1] = new int[] { i, numAppearCount[i] };
//         }
        
//         Arrays.sort(numAppearSort, (o1, o2) -> o2[1] - o1[1]);
        
        // starts[i]<= answer[i] <= e 범위수 중 많이 등장한 수목록에서 제일 작은 값
        // int[] answer = new int[starts.length];
        // for (int i = 0; i < starts.length; i++) {
        //     for (int j = 0; j < numAppearSort.length; j++) {
        //         if (numAppearSort[j][0] >= starts[i]) {
        //             answer[i] = numAppearSort[j][0];
        //             break;
        //         }
        //     }
        // }

        return answer;
    }
}