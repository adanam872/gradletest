package gradletest;

public class DynamicPrograming {

    /*
     * 대학교 알고리즘 동아리 회장인 당신은 회원들과 함께 MT를 왔습니다. 하지만 초보 총무의 실수로 예산이 부족해 긴급하게 학생들에게 추가 모금을 할 수 밖에 없는 상황에 처했습니다. 당신은 회원들에게 부족한 예산을 기부받아 내려고 하고 있습니다. 하지만 학생들은 모두 눈치만 보고 있는 상황입니다.
     * 회원들은 모닥불을 가운데 두고 빙 둘러 앉아 있습니다. 눈치를 많이 보는 학생들은  양 옆에 앉은 학생들 중 한 명이라도 기부를 하면 자신은 기부를 하지 않는다고 합니다. 
     * 학생들의 순서가 배열로 주어지고 각 배열에 학생들이 기부하려고 하는 금액이 저장되어 있습니다. 이 때 배열의 첫번째와 마지막에 위치한 학생들은 서로 인접한 것으로 생각합니다. 어떻게 하면 기부금을   최대로 모을 수 있을까요?
     */
    
    /*
     * 동적계획법을 활용해서 간단하게 풀 수 있는 문제입니다. dp배열을 만들고 각 배열에 저장된 값을 i번째 학생이 돈을 기부했을 때 기부금 최고액이라고 정합니다. 음수가 입력되는 경우가 없기 때문에 3칸 이상 떨어졌을 경우는 고려할 필요가 없습니다. 인접한 회원이 기부했을 때는 제외해야 하기 때문에 매번 탐색에서 바로 이전과 -2번째 배열만 탐색하면 됩니다.
     * 이 때 생각할 거리는 바로 배열 첫번째와 마지막에 위치한 학생이 서로 인접했다는 것을 어떻게 표현 하냐는 것인데, 고민할 필요 없이 애초에 배열을 두개 만들어서 첫번째 학생이 기부를 하는 경우와 그렇지 않은 경우로 나누어 문제를 풀면 됩니다. 코드가 반복되는 것이 싫지만 생각할 수 있는 가장 간단한 해결책입니다. 시간복잡도는 O(N)입니다.
     */
    
    public int maxDona(int[] donations) {
        
        int max = 0;
        int[] dpFi = new int[donations.length];
        
        for (int i = 0; i < donations.length-1; i++) {
            dpFi[i] = donations[i];
            if (i >= 3) {
                dpFi[i] += dpFi[i-3] > dpFi[i-2] ? dpFi[i-3] : dpFi[i-2];
            } else if (i >= 2) {
                dpFi[i] += dpFi[i-2];
            }
            if (max < dpFi[i]) max = dpFi[i];
        }
        
        int[] dpSe = new int[donations.length];
        
        for (int i = 1; i < donations.length; i++) {
            dpSe[i] = donations[i];
            if (i >= 3) {
                dpSe[i] += dpSe[i-3] > dpSe[i-2] ? dpSe[i-3] : dpSe[i-2];
            } else if (i >= 2) {
                dpSe[i] += dpSe[i-2];
            }
            if (max < dpSe[i]) max = dpSe[i];
        }
        
        return max;
    }
    
    public static void main(String[] args) {
        int[] donations = {10, 3, 2, 5, 7, 8};
        System.out.println(new DynamicPrograming().maxDona(donations));
    }
}
