# 1. 생각 나는 대로  
    public int solution(int[] budgets, int M) {
        int answer = 0;
        
        // 1. 총 계산이 넘지 않는 경우 : 상한액? 기준이 없어 보여서 큰 값으로
        long totalBudgets = 0; // 값이 더 클 경우 오버 플로우가 날수도 있다.
        for (int budget : budgets) {
            totalBudgets += budget;
            if(answer < budget) answer = budget;
        }            
        if (totalBudgets <= (long) M) return answer;

        // 2. 총 계산이 넘는 경우 : 상한액 ? (120 + 110 + 140 + 150) / 4 = 130 을 기준으로, M-작은값 / 큰 값 개수 하면 127
        int limit = (int)(totalBudgets / budgets.length);
        int cnt=0;
        int total=0;
        for(int value : budgets){
            if(check(value, limit)){
                total = total + value;
                cnt = cnt + 1;
            }
        }             
        answer = (M-total)/(budgets.length-cnt);
        
        return answer;
    }
    public static boolean check(int value, int limit){
        return value < limit ? true:false;
    }

# 2. 이분 탐색
상한액을 어떻게 찾아야 할 지 공식이 없는 것 같음, 수를 모두 넣어보아서 최대가 되는 상한액을
문제의 태그가 이분탐색인 만큼 이분탐색을 사용, 0~최댓값을 이분탐색의 방법으로 최대 상한액을

class Solution {
    public int solution(int[] budgets, int M) {
        int answer = 0;
        
        // 1. 총 계산이 넘지 않는 경우 
        long totalBudgets = 0; // 값이 더 클 경우 오버 플로우가 날수도 있다.
        for (int budget : budgets) {
            totalBudgets += budget;
            if(answer < budget) answer = budget;
        }            
        if (totalBudgets <= (long) M) return answer;

        // 2. 총 계산이 넘는 경우                
        int min = min(budgets);
        int max = max(budgets);
        int temp = 0;
        
        while(min <= max){ // 이분 탐색   
            int mid = (min + max) /2;
            int total=0;
            for(int budget : budgets){
                if(budget < mid){
                    total=total+budget;
                }
                else{
                    total=total+mid;
                } 
            }
            
            if(total == M){
                answer = mid;
                break;
            }
            else if(total > M){
                max = mid - 1;
            }             
            else {
                if(total > temp){
                    temp = total;
                    answer = mid;
                }                
                min = mid + 1; 
            }
        }
        return answer;        
     }    
    
     public static int max(int arr[]){
         int max=Integer.MIN_VALUE;
         for(int i: arr){
             if(i > max) max = i;
         }
         return max;
     }
}
