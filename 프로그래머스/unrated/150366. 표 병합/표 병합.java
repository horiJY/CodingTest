import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        Cell[][] cell = new Cell[51][51];

        for (int r = 1; r < cell.length; r++) {
            for (int c = 1; c < cell[0].length; c++) {
                cell[r][c] = new Cell();
            }
        }

        for (String command : commands) {
            String[] input = command.split(" ");

            switch (input[0]) {
                case "UPDATE" -> {
                    if (input.length == 4) { // update r c value
                        int r = Integer.parseInt(input[1]);
                        int c = Integer.parseInt(input[2]);
                        Cell target = cell[r][c].parent;
                        String value = cell[r][c].value = input[3];

                        for (int rr = 1; rr < cell.length; rr++) { // merge 되어있을 수 있어 전체를 순회
                            for (int cc = 1; cc < cell[0].length; cc++) {
                                if (cell[rr][cc].parent == target) {
                                    cell[rr][cc].value = value;
                                }
                            }
                        }
                    } else if (!input[1].equals(input[2])) { // update value1 value2
                        for (int r = 1; r < cell.length; r++) {
                            for (int c = 1; c < cell[0].length; c++) {
                                if (cell[r][c].value.equals(input[1])) {
                                    cell[r][c].value = input[2];
                                }
                            }
                        }
                    }
                }
                case "MERGE" -> {
                    int r1 = Integer.parseInt(input[1]);
                    int c1 = Integer.parseInt(input[2]);
                    int r2 = Integer.parseInt(input[3]);
                    int c2 = Integer.parseInt(input[4]);
                    boolean cell1HasValue = cell[r1][c1].value.equals("") ? false : true;
                    boolean cell2HasValue = cell[r2][c2].value.equals("") ? false : true;
                    Cell target = null;
                    Cell fixParent = null;
                    String fixValue = "";

                    if (!cell1HasValue && cell2HasValue) {
                        target = cell[r1][c1].parent;
                        cell[r1][c1].parent = fixParent = cell[r2][c2].parent;
                        cell[r1][c1].value = fixValue = cell[r2][c2].value;
                    } else {
                        target = cell[r2][c2].parent;
                        cell[r2][c2].parent = fixParent = cell[r1][c1].parent;
                        cell[r2][c2].value = fixValue = cell[r1][c1].value;
                    }

                    for (int rr = 1; rr < cell.length; rr++) {
                        for (int cc = 1; cc < cell[0].length; cc++) {
                            if (cell[rr][cc].parent == target) {
                                cell[rr][cc].parent = fixParent;
                                cell[rr][cc].value = fixValue;
                            }
                        }
                    }
                }
                case "UNMERGE" -> {
                    int r = Integer.parseInt(input[1]);
                    int c = Integer.parseInt(input[2]);
                    Cell target = cell[r][c].parent;
                    String origValue = cell[r][c].value;

                    for (int rr = 1; rr < cell.length; rr++) {
                        for (int cc = 1; cc < cell[0].length; cc++) {
                            if (cell[rr][cc].parent == target) {
                                cell[rr][cc] = new Cell();
                            }
                        }
                    }
                    cell[r][c].parent = cell[r][c];
                    cell[r][c].value = origValue;
                }
                case "PRINT" -> {
                    int r = Integer.parseInt(input[1]);
                    int c = Integer.parseInt(input[2]);
                    if (cell[r][c].value.equals("")) {
                        answer.add("EMPTY");
                    } else {
                        answer.add(cell[r][c].value);
                    }
                }

            }

        }

        return answer.toArray(new String[0]);
    }
}

class Cell {
    Cell parent = this;
    String value = "";
}