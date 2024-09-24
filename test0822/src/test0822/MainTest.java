package test0822;

import java.util.ArrayList;

public class MainTest {

	public static void main(String[] args) {
		// Sample 인터페이스를 구현해서 호랑이가 4발로 뛰어다닌다 라고 출력

		Test0920 t = new Test0920();
		// 1. Test0920 객체 생성하기
		// new 연산자로 메모리 공간을 할당하고 생성자를 호출하여 객체를 생성한 후 그 객체의 주소값을 객체참조변수 t에 담는다.
		// 객체참조변수 t의 타입은 Test0920 클래스 타입이다.

		// 객체참조변수 t값을 찍어봄으로써 객체 생성 여부를 알 수 있다.
		System.out.println(t);

		t.move();

		try {
			a();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayListTest at = new ArrayListTest();

		// ArrayList 자바가 제공하는 컬렉션 프레임워크 클래스, 빈 상자와 같다.
		// 배열의 특성을 가지고 있어서 속도가 빠른게 장점, 자동으로 번호표가 부여된다.
		// 데이터 조회 열람 시 사용을 많이 한다.
		// 입출력이 수정 삽입 삭제가 빈번한 데이터는 효과가 별로 없다.
		ArrayList<String> tt = at.test();

		System.out.println(tt);

		String name = tt.get(1);
		System.out.println(name);

		// 제너릭을 통한 객체 타입 생성
		ArrayList<BoardVo> alist = new ArrayList<>();

		BoardVo bv = new BoardVo(); // BoardVo 객체를 생성한다.
		bv.setSubject("게시판 제목입니다.");
		bv.setContents("게시판 내용입니다.");
		bv.setBidx(1);

		BoardVo bv2 = new BoardVo();
		bv2.setSubject("게시판 2번 제목입니다.");
		bv2.setContents("게시판 2번 내용입니다.");
		bv2.setBidx(2);

		alist.add(bv);
		alist.add(bv2);

		System.out.println(alist);
		System.out.println(alist.get(1).getSubject());

		String aa = "안녕";
		String bb = "하세요";
		String cc = aa + bb;
		
		System.out.println(cc);
		
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("hello ");
		sbBuffer.append("I'm ");
		sbBuffer.append("so happy!");
		
		String resultString = sbBuffer.toString();
		System.out.println(resultString);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("hi ").append("it's raining!");
		
		String result2 = stringBuilder.toString();
		System.out.println(result2);
	}

	public static void a() throws Exception {
		System.out.println("a입니다.");
	}

	public static void b() throws Exception {
		System.out.println("b입니다.");
	}

	public static void c() throws Exception {
		System.out.println("c입니다.");
	}
}
