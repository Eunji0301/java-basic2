package test0822;

public class Test0913 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("엄은지");
		
		// 숫자형 변수 a의 값은 1입니다.
		// a의 값이 1이면 참이고 2이면 거짓입니다. 라는 코드 작성
		
		int a = 1;
		
		if(a == 1) {
			System.out.println("참");
		} else if (a == 2) {
			System.out.println("거짓");
		}
		
		// 1을 10번 출력
		for(int i = 0; i < 10; i++) {
			System.out.println(1);
		}
		
		int i = 0; // 초기식
		while (i < 10) { // 반복식
			System.out.println(1);
			i++; // 증감식
		}
	}

}