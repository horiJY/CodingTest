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

                        updateCell(cell, (Cell e) -> e.parent == target, (Cell e) -> e.value = input[3]);
                    } else if (!input[1].equals(input[2])) { // update value1 value2
                        updateCell(cell, (Cell e) -> e.value.equals(input[1]), (Cell e) -> e.value = input[2]);
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

                    final Cell preTarget = target;
                    final Cell preParent = fixParent;
                    final String preValue = fixValue;

                    Consumer<Cell> updateCellConsumer = (Cell e) -> {
                        e.parent = preParent;
                        e.value = preValue;
                    };
                    updateCell(cell, (Cell e) -> e.parent == preTarget, updateCellConsumer);
                }
                case "UNMERGE" -> {
                    int r = Integer.parseInt(input[1]);
                    int c = Integer.parseInt(input[2]);
                    Cell target = cell[r][c].parent;
                    String origValue = cell[r][c].value;

                    Consumer<Cell> updateCellInit = (Cell e) -> {
                        e.parent = e;
                        e.value = "";
                    };
                    updateCell(cell, (Cell e) -> e.parent == target, updateCellInit);

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

    private void updateCell(Cell[][] cell, Predicate<Cell> p, Consumer<Cell> c) {
        for (int row = 1; row < cell.length; row++) { // merge 되어있을 수 있어 전체를 순회
            for (int col = 1; col < cell[0].length; col++) {
                if (p.test(cell[row][col])) {
                    c.process(cell[row][col]);
                }
            }
        }
    }

}

class Cell {
    Cell parent = this;
    String value = "";
}

interface Predicate<T> {
    boolean test(T t);
}

interface Consumer<T> {
    void process(T t);
}