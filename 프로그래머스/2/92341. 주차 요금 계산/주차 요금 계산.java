import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    static HashMap<String, Integer> carParkingTime;
    static int baseFee, baseTime, parkingFee, parkingTime;

    public int[] solution(int[] fees, String[] records) {
        baseTime = fees[0];
        baseFee = fees[1];
        parkingTime = fees[2];
        parkingFee = fees[3];
        carParkingTime = new HashMap<>();
        HashMap<String, int[]> timeStamp = new HashMap<>();
        for (int i = 0; i < records.length; i++) {
            String[] data = records[i].split(" ");
            if (data[2].equals("IN")) {
                timeStamp.put(data[1], new int[] { 0, 0 });
                timeStamp.get(data[1])[0] = convertMinute(data[0]);
            } else {
                timeStamp.get(data[1])[1] = convertMinute(data[0]);
                carParkingTime.put(data[1],
                        carParkingTime.getOrDefault(data[1], 0)
                                + (timeStamp.get(data[1])[1] - timeStamp.get(data[1])[0]));
            }
        }

        ArrayList<String> carSorts = new ArrayList<>();
        for (String car : timeStamp.keySet()) {
            carSorts.add(car);
        }
        carSorts.sort(null);

        int[] answer = new int[carSorts.size()];
        for (int i = 0; i < answer.length; i++) {
            if (timeStamp.get(carSorts.get(i))[1] == 0) {
                carParkingTime.put(carSorts.get(i),
                        carParkingTime.getOrDefault(carSorts.get(i), 0)
                                + (convertMinute("23:59") - timeStamp.get(carSorts.get(i))[0]));
            }
            answer[i] = calculateParkingfee(carParkingTime.get(carSorts.get(i)));
        }

        return answer;
    }

    private int calculateParkingfee(int totalTime) {
        int fee = baseFee;
        if (totalTime - baseTime > 0) {
            int remain = (totalTime - baseTime) % parkingTime;
            if (remain == 0) {
                fee += ((totalTime - baseTime) / parkingTime) * parkingFee;
            } else {
                fee += ((totalTime - baseTime) / parkingTime + 1) * parkingFee;
            }
        }
        return fee;
    }

    private int convertMinute(String timeString) {
        String[] data = timeString.split(":");
        return Integer.parseInt(data[0]) * 60 + Integer.parseInt(data[1]);
    }
}