import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 첫줄에테스트케이스의개수C(C <= 20)
 * 
 * 두줄씩 멤버의 성별, 팬의 성별 주어짐 (F,M)
 * 멤버와 팬의 수는 1이상 200_000 이하 정수
 * 
 * 멤버 전체가 포옹을하는 일을 count
 */

public class BS_3FanMeeting {
    static int[] result;
    static char[] members, fans;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        while (C-- > 0) {
            // String memStr = br.readLine();
            members = br.readLine().toCharArray();
            fans = br.readLine().toCharArray();
            result = new int[fans.length - members.length + 1];
            // for (int i = 0; i < members.length; i++) {
            // members[i] = memStr.charAt(memStr.length() - i - 1);
            // }

            execKaratsuba();

            int answer = 0;
            for (int i = 0; i < result.length; i++) {
                if (result[i] == 0) {
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }

    private static void execKaratsuba() {
        for (int i = 0; i < members.length; i++) {
            for (int j = 0; j < fans.length; j++) {
                if (j - i < 0) {
                    continue;
                }

                if (j - i < result.length) {
                    int m = members[i] == 'M' ? 1 : 0;
                    int f = fans[j] == 'M' ? 1 : 0;
                    result[j - i] += m * f;
                } else {
                    break;
                }
            }
        }
    }
}
