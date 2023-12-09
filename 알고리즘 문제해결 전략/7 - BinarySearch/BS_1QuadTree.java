import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 쿼드 트리: 2" ×2"크기
 * 
 * 모든 픽셀이검은색일경우
 * 압축결과는 b
 *
 * 모든 픽셀이 흰색일경우
 * 압축결과는 w
 *
 * 모든 픽셀이 같은색이 아니라면
 * 4개의 조각으로 쪼갠 뒤 각각을 쿼드트리 압축,
 * 압축결과는 x(왼쪽 위부분의 압축결과)(오른쪽위부분의압축결과)(왼쪽아래부분의압축결과)(오른쪽아래부분의압축결과)
 * 
 * 쿼드트리로압축된흑백그림이주어졌을때, 이그림을상하로뒤집은그림을쿼드트리압축해서출력하는프로그램을작성
 * 
 * @input
 *        케이스의개수 C(C<=50)
 *        C줄에하나씩쿼드트리로압축한그림이주어집니다.
 *        문자열의길이는1_000 이하
 *        그림의 크기는2^20 * 2^20을 넘지않음
 */
public class BS_1QuadTree {
    static int strIdx;
    static String inputStr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        while (C-- > 0) {
            inputStr = br.readLine();
            strIdx = -1;
            System.out.println(printReverseQuadTree());
        }
    }

    private static String printReverseQuadTree() {
        if (inputStr.charAt(++strIdx) != 'x') {
            return String.valueOf(inputStr.charAt(strIdx));
        }

        String lu = printReverseQuadTree();
        String ru = printReverseQuadTree();
        String ld = printReverseQuadTree();
        String rd = printReverseQuadTree();

        return "x" + ld + rd + lu + ru;
    }
}
