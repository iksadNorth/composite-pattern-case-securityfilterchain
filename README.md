# composite-pattern-case-securityfilterchain

# 👣 개요

**컴포지트 패턴이란?**

코드 가독성과 유지보수성을 높이기 위해
개별 객체와 집합 객체를 동일하게 다루는 디자인 패턴.

[Composite Pattern - 구조](https://ikadnorth.tistory.com/90)

해당 패턴을 이용해서 SecurityFilterChain 설정을 OCP, SRP 원칙을 지킬 수 있도록

코드를 작성함.

## 👣 문제점

[https://github.com/iksadNorth/composite-pattern-case-securityfilterchain/commit/6335f7f2c5b283a4f16944171700d2cf36f353cf](https://github.com/iksadNorth/composite-pattern-case-securityfilterchain/commit/6335f7f2c5b283a4f16944171700d2cf36f353cf)

![https://blog.kakaocdn.net/dn/Xb40s/btsBb8a7rHw/3uv4vJ7gbY8CUaTbDz1y2k/img.png](https://blog.kakaocdn.net/dn/Xb40s/btsBb8a7rHw/3uv4vJ7gbY8CUaTbDz1y2k/img.png)

그저 **모든 SecurityFilterChain 설정을 SecurityConfig.java 파일에** 하기 때문에

어떤 설정을 제거하거나 추가하려면 OCP 원칙을 해칠 수 밖에 없다.

만약 JWT를 적용하지 않는 App에 JWT를 적용해야 한다면 SecurityConfig에

session policy를 stateless로 설정하고 JWT filter를 적용해야 할 것이다.

하지만 다시 JWT가 아닌 방식을 적용해야 한다면 이것을 수정하기 위해 관련 코드 라인을 분석하고 삭제 해야 한다.

1~2줄라면 걱정없이 삭제하겠지만 App 크기가 비대해지면 일일히 읽고 삭제, 수정하기는 어렵다.

만약 아래와 같이 구현한다면 단순히 JWT 관련 파일만 삭제하고 또다른 기술을 구현한 소스 코드를 작성만 하면

문제없이 적용된다.

특히 나의 경우, Local, Test, Prod 와 같은 **3개의 Profile을 이용**해서 설정들을 분리하고 있었기에

**각 설정을 분리하기 위해 3개의 Config 클래스**를 각각 따로 만들어야 했다.

확실히 이러한 부분들은 **유지 보수성을 크게 떨어뜨림**을 알 수 있다.

때문에 SecurityConfig 클래스를 1개의 클래스로 관리하는 것이 아닌

JwtConfig, CsrfConfig, ErrorHandlerConfig 등등으로 역할에 맞춰 분리하고 싶었다.

## 👣 구현 계획

![https://blog.kakaocdn.net/dn/bXiZrd/btssB0LV6td/nQZdFCKHYxVjlnXFw82610/img.png](https://blog.kakaocdn.net/dn/bXiZrd/btssB0LV6td/nQZdFCKHYxVjlnXFw82610/img.png)

SecurityFilterChainRing 이라는 인터페이스를 만들고 addChainRing이란 메서드를 작성한다.해당 메서드는 HttpSecurity 타입의 파라미터를 받고 이것을 조작한다.

![https://blog.kakaocdn.net/dn/dmmKkv/btssDPXGQa9/G1cDKofVkMhgMZWtqgOM7k/img.png](https://blog.kakaocdn.net/dn/dmmKkv/btssDPXGQa9/G1cDKofVkMhgMZWtqgOM7k/img.png)

SecurityFilterChainRing 구현체들을 모두 Bean Container에 담아내고이것들을 일괄 등록하는 클래스를 구현한다.

SecurityFilterChainRingContainer 라는 클래스를 만들고 SecurityFilterChainRing 를 구현토록한다.다만 SecurityFilterChainRingContainer 클래스는 특별하게  Collection<SecurityFilterChainRing> 타입의 필드를가지고 있어서 SecurityFilterChainRing 구현체들에 모두 접근할 수 있다.SecurityFilterChainRingContainer.addChainRing 메서드는 이 구현체들을 일괄 등록하는 기능을 가지고 있다.
