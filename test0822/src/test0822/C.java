package test0822;

// 클래스 C를 생성해서 1에서 10까지 합을 구하는 메서드를 만들고 그 값을 출력하시오.

public class C {
	int sum = 0; // 값을 담을 변수를 초기화 세팅
	// 총합을 누적해서 담는 변수
	
	public void getSum() {
		for(int i = 1; i <= 10; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
