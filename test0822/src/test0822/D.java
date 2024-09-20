package test0822;

// 클래스 D를 생성해서 멤버변수 x를 설정하고 x값이 3이고 5보다 작으면 작다라고 출력, 5보다 크면 크다라고 출력

public class D {
	int x = 3; // 전역변수
	
	public void calculate() {
		if (x < 5) {
			System.out.println("작다");
		} else if (x > 5) {
			System.out.println("크다");
		}
	}
}
