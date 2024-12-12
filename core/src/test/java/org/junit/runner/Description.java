package org.junit.runner;

/**
 * 테스트컨테이너 내 `JUnit4` 의 종속성이 있는 것으로 확인되었음 현재 프로젝트에서는 `Junit4` 를 사용자가 임의로도 사용하지 못하게 의존성을 배제해둔 상태 따라서
 * 정상 작동을 위해 임의 `JUnit4` 클래스를 만들어둠 <a
 * href="https://github.com/testcontainers/testcontainers-java/issues/970">Issue</a>
 */
@SuppressWarnings("unused")
public interface Description {}
