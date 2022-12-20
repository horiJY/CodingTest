import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] data = br.readLine().split(" ");
            Cube cube = new Cube();
            for (int j = 0; j < N; j++) {
                cube.operate(data[j].split("")[0], data[j].split("")[1]);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cube.U[0]).append('\n').append(cube.U[1]).append('\n').append(cube.U[2]);
            System.out.println(sb);
        }
    }

    static class Cube {
        /**
         * 큐브 면 색상
         * U : 위, w
         * D : 아래, y
         * F : 앞, r
         * B : 뒷, o
         * L : 왼쪽, g
         * R : 오른쪽, b
         */
        char[][] U;
        char[][] D;
        char[][] F;
        char[][] B;
        char[][] L;
        char[][] R;

        public Cube() {
            U = new char[3][3];
            D = new char[3][3];
            F = new char[3][3];
            B = new char[3][3];
            L = new char[3][3];
            R = new char[3][3];
            for (int i = 0; i < 3; i++) {

                Arrays.fill(U[i], 'w');
                Arrays.fill(D[i], 'y');
                Arrays.fill(F[i], 'r');
                Arrays.fill(B[i], 'o');
                Arrays.fill(L[i], 'g');
                Arrays.fill(R[i], 'b');
            }
        }

        /**
         * @param side      : 회전 기준 면
         * @param direction : 해당 면을 바라봤을 때 + 시계방향, - 반시계방향
         */
        private void operate(String side, String direction) {
            switch (side) {
                case "U":
                    turnU(direction);
                    break;
                case "D":
                    turnD(direction);
                    break;
                case "F":
                    turnF(direction);
                    break;
                case "B":
                    turnB(direction);
                    break;
                case "L":
                    turnL(direction);
                    break;
                case "R":
                    turnR(direction);
                    break;
            }
        }

        private void turnR(String direction) {
            char[] temp = { this.U[0][2], this.U[1][2], this.U[2][2] };
            int n = this.R.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.U[0][2] = this.F[0][2];
                this.U[1][2] = this.F[1][2];
                this.U[2][2] = this.F[2][2];
                this.F[0][2] = this.D[0][2];
                this.F[1][2] = this.D[1][2];
                this.F[2][2] = this.D[2][2];
                this.D[0][2] = this.B[2][0];
                this.D[1][2] = this.B[1][0];
                this.D[2][2] = this.B[0][0];
                this.B[0][0] = temp[2];
                this.B[1][0] = temp[1];
                this.B[2][0] = temp[0];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.R[n - 1 - j][i];
                    }
                }
            } else {
                this.U[0][2] = this.B[2][0];
                this.U[1][2] = this.B[1][0];
                this.U[2][2] = this.B[0][0];
                this.B[2][0] = this.D[0][2];
                this.B[1][0] = this.D[1][2];
                this.B[0][0] = this.D[2][2];
                this.D[0][2] = this.F[0][2];
                this.D[1][2] = this.F[1][2];
                this.D[2][2] = this.F[2][2];
                this.F[0][2] = temp[0];
                this.F[1][2] = temp[1];
                this.F[2][2] = temp[2];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.R[j][n - 1 - i];
                    }
                }
            }
            this.R = tempSide;
        }

        private void turnL(String direction) {
            char[] temp = { this.U[0][0], this.U[1][0], this.U[2][0] };
            int n = this.L.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.U[0][0] = this.B[2][2];
                this.U[1][0] = this.B[1][2];
                this.U[2][0] = this.B[0][2];
                this.B[0][2] = this.D[2][0];
                this.B[1][2] = this.D[1][0];
                this.B[2][2] = this.D[0][0];
                this.D[0][0] = this.F[0][0];
                this.D[1][0] = this.F[1][0];
                this.D[2][0] = this.F[2][0];
                this.F[0][0] = temp[0];
                this.F[1][0] = temp[1];
                this.F[2][0] = temp[2];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.L[n - 1 - j][i];
                    }
                }
            } else {
                this.U[0][0] = this.F[0][0];
                this.U[1][0] = this.F[1][0];
                this.U[2][0] = this.F[2][0];
                this.F[0][0] = this.D[0][0];
                this.F[1][0] = this.D[1][0];
                this.F[2][0] = this.D[2][0];
                this.D[0][0] = this.B[2][2];
                this.D[1][0] = this.B[1][2];
                this.D[2][0] = this.B[0][2];
                this.B[0][2] = temp[2];
                this.B[1][2] = temp[1];
                this.B[2][2] = temp[0];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.L[j][n - 1 - i];
                    }
                }
            }
            this.L = tempSide;
        }

        private void turnB(String direction) {
            char[] temp = { this.U[0][0], this.U[0][1], this.U[0][2] };
            int n = this.B.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.U[0][0] = this.R[0][2];
                this.U[0][1] = this.R[1][2];
                this.U[0][2] = this.R[2][2];
                this.R[0][2] = this.D[2][2];
                this.R[1][2] = this.D[2][1];
                this.R[2][2] = this.D[2][0];
                this.D[2][0] = this.L[0][0];
                this.D[2][1] = this.L[1][0];
                this.D[2][2] = this.L[2][0];
                this.L[0][0] = temp[2];
                this.L[1][0] = temp[1];
                this.L[2][0] = temp[0];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.B[n - 1 - j][i];
                    }
                }
            } else {
                this.U[0][0] = this.L[2][0];
                this.U[0][1] = this.L[1][0];
                this.U[0][2] = this.L[0][0];
                this.L[0][0] = this.D[2][0];
                this.L[1][0] = this.D[2][1];
                this.L[2][0] = this.D[2][2];
                this.D[2][0] = this.R[2][2];
                this.D[2][1] = this.R[1][2];
                this.D[2][2] = this.R[0][2];
                this.R[0][2] = temp[0];
                this.R[1][2] = temp[1];
                this.R[2][2] = temp[2];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.B[j][n - 1 - i];
                    }
                }
            }
            this.B = tempSide;
        }

        private void turnF(String direction) {
            char[] temp = { this.U[2][0], this.U[2][1], this.U[2][2] };
            int n = this.F.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.U[2][0] = this.L[2][2];
                this.U[2][1] = this.L[1][2];
                this.U[2][2] = this.L[0][2];
                this.L[0][2] = this.D[0][0];
                this.L[1][2] = this.D[0][1];
                this.L[2][2] = this.D[0][2];
                this.D[0][0] = this.R[2][0];
                this.D[0][1] = this.R[1][0];
                this.D[0][2] = this.R[0][0];
                this.R[0][0] = temp[0];
                this.R[1][0] = temp[1];
                this.R[2][0] = temp[2];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.F[n - 1 - j][i];
                    }
                }
            } else {
                this.U[2][0] = this.R[0][0];
                this.U[2][1] = this.R[1][0];
                this.U[2][2] = this.R[2][0];
                this.R[0][0] = this.D[0][2];
                this.R[1][0] = this.D[0][1];
                this.R[2][0] = this.D[0][0];
                this.D[0][0] = this.L[0][2];
                this.D[0][1] = this.L[1][2];
                this.D[0][2] = this.L[2][2];
                this.L[0][2] = temp[2];
                this.L[1][2] = temp[1];
                this.L[2][2] = temp[0];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.F[j][n - 1 - i];
                    }
                }
            }
            this.F = tempSide;
        }

        private void turnD(String direction) {
            char[] temp = { this.F[2][0], this.F[2][1], this.F[2][2] };
            int n = D.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.F[2] = this.L[2];
                this.L[2] = this.B[2];
                this.B[2] = this.R[2];
                this.R[2] = temp;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.D[n - 1 - j][i];
                    }
                }
            } else {
                this.F[2] = this.R[2];
                this.R[2] = this.B[2];
                this.B[2] = this.L[2];
                this.L[2] = temp;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.D[j][n - 1 - i];
                    }
                }
            }
            this.D = tempSide;
        }

        private void turnU(String direction) {
            char[] temp = { this.F[0][0], this.F[0][1], this.F[0][2] };
            int n = U.length;
            char[][] tempSide = new char[3][3];
            if (direction.equals("+")) {
                this.F[0] = this.R[0];
                this.R[0] = this.B[0];
                this.B[0] = this.L[0];
                this.L[0] = temp;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.U[n - 1 - j][i];
                        /*
                         * 00 01 02 -> 20 10 00
                         * 10 11 12 -> 21 11 01
                         * 20 21 22 -> 22 12 02
                         */
                    }
                }
            } else {
                this.F[0] = this.L[0];
                this.L[0] = this.B[0];
                this.B[0] = this.R[0];
                this.R[0] = temp;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        tempSide[i][j] = this.U[j][n - 1 - i];
                        /*
                         * 00 01 02 -> 02 12 22
                         * 10 11 12 -> 01 11 21
                         * 20 21 22 -> 00 10 20
                         */

                    }
                }
            }
            this.U = tempSide;
        }
    }
}
