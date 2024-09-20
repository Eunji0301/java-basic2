package test0822;

public class Test0919 extends Object { // Object 클래스는 객체 생성 시 필요, 모든 클래스의 조상
	
	// 생성자
	Test0919() {
		super(); // 상위 Object 생성자 호출 -> 객체생성
	}
	
	int c;
	Test0919(int a) {
		c = a;
	}
	
	// 클래스의 멤버변수를 생성 전역변수
	String str = "안녕";
	
	// 멤버메서드
	public void test() {
		int a = 0; // 지역변수
		
		System.out.println(str);
		System.out.println(a);
	}

	public static void main(String[] args) { // 가상머신과 소통 위한 메서드
		// TODO Auto-generated method stub
		System.out.println("안녕하세요");
//		System.out.println(str);
		
		// 객체생성코드 형식
		// 클래스타입 객체참조변수 = new 클래스생성자();
		Test0919 t = new Test0919();
		System.out.println("t에 담긴 값은 ? " + t);
		
		String aaa = t.str;
		System.out.println("str에 담긴 값은? " + aaa);
		
		Test 0919 tt = new Test0919(1);
		
		C c = new C();
		c.getSum();
		
		D d = new D();
		d.calculate();
	}

}
