---
name: javascript-menu
description: javascript Spring Boot·Thymeleaf 프로젝트에서 예제 메뉴를 추가하거나 변경할 때 index.html 메뉴, UIController 요청 경로, Bootstrap 5.3 응답 카드 HTML을 함께 생성·수정한다.
---

# JavaScript 예제 메뉴와 응답 페이지

이 스킬은 `javascript` 프로젝트의 기존 메뉴 요청 구조를 따른다. 작업 디렉터리를 기준으로 프로젝트를 찾고, 아래 파일과 프로젝트의 AGENTS.md를 확인한다. 다른 기술 스택에 이 구조를 강제로 적용하지 않는다.

- `src/main/resources/templates/index.html`
- `src/main/java/com/mycompany/demo/controller/UIController.java`
- `src/main/resources/templates/basic/exam02-function.html` 또는 가장 최근 예제 템플릿

## 생성 규칙

사용자가 지정한 요청 경로를 메뉴 이름으로 그대로 사용한다. 예를 들어 `/exam03-array`라면 메뉴 이름과 요청 경로는 `/exam03-array`, 템플릿은 `basic/exam03-array.html`, 컨트롤러 반환값은 `basic/exam03-array`다. 경로가 명확하면 추가 확인 없이 진행한다. 경로 또는 예제 주제가 없어 결정할 수 없을 때만 질문한다.

### 메뉴

`index.html`의 `#company-menu`에 기존 메뉴와 같은 버튼을 추가한다. 기존 번호 순서에 맞춰 배치하고 중복 메뉴는 만들지 않는다.

```html
<button class="nav-link text-start text-break rounded px-2 px-sm-3 py-2" type="button">/exam03-array</button>
```

- ‘기본’ 아래에 요청 경로만 표시하고 별도의 번호는 붙이지 않는다.
- 기존 공통 `loadMenu` 처리기를 사용한다. 메뉴 이름으로 `fetch` 요청하고 서버 HTML 응답을 우측 iframe의 `srcdoc`으로 표시하는 구조를 유지한다.
- 새 메뉴마다 클릭 처리기나 정적 탭 패널을 추가하지 않는다. 전체 페이지로 이동하는 링크로 변경하지 않는다.
- 요청 중 안내, HTTP 오류 안내, 이전 요청 취소, 선택 메뉴 표시를 유지한다.
- 우측에 breadcrumb(`HOME / 회사 안내`), 회사 푸터, 회색 바깥 여백을 다시 추가하지 않는다.

### 컨트롤러

`UIController`에 기존 스타일의 요청 메서드를 추가한다. 요청 경로, 뷰 이름, 템플릿 파일 이름을 일치시킨다. 기존 경로가 있으면 해당 메서드를 수정하고 중복 매핑은 만들지 않는다.

```java
@RequestMapping("/exam03-array")
public String exam03Array(HttpServletRequest request, Model model) {
    model.addAttribute("requestPath", request.getRequestURI());
    model.addAttribute("responseResult", "배열 예제 페이지 요청이 정상적으로 처리되었습니다.");
    return "basic/exam03-array";
}
```

- 카드 제목용 `requestPath`는 실제 `request.getRequestURI()`를 사용한다.
- 카드 본문용 `responseResult`에는 사용자가 요청한 응답 데이터를 전달한다. 메뉴와 페이지 골격만 요청한 경우에는 기존 예제처럼 주제에 맞는 요청 처리 안내 문구를 넣는다.
- 기존 import를 재사용한다. 누락된 경우에만 `Model`, `HttpServletRequest`, `RequestMapping`을 추가한다.
- 별도로 요청하지 않은 API나 JavaScript 학습 예제 기능은 추가하지 않는다.

### HTML 응답 카드

`src/main/resources/templates/basic/`에 HTML을 생성한다. 최신 예제 템플릿을 기준으로 요청 경로를 바꾸고, 기존 본문 바인딩을 유지한다.

- `lang="ko"`, UTF-8, viewport, Thymeleaf 네임스페이스를 포함한다.
- 프로젝트와 동일한 Bootstrap 5.3 버전과 로딩 방식을 사용한다. 현재 예제는 CDN의 5.3.3이다.
- 모든 디자인은 Bootstrap 컴포넌트와 유틸리티 클래스를 우선 사용한다. Bootstrap에서 제공하지 않는 기능에만 CSS를 작성한다.
- 카드: `card shadow-sm rounded-3 overflow-hidden`.
- 카드 헤더: `card-header bg-body-tertiary p-3 p-md-4`.
- 제목: `h1`을 유지하되 `fs-6 fw-normal mb-0`로 본문과 같은 크기·굵기를 사용한다. `th:text="${requestPath}"`로 실제 경로를 출력하고 정적 대체 문구도 새 경로로 바꾼다.
- 본문: `card-body p-3 p-md-4` 안에서 `th:text="${responseResult}"`로 응답 문자열을 출력한다. 임의의 HTML을 출력하는 `th:utext`로 변경하지 않는다.
- 긴 문자열에는 Bootstrap `text-break`를 사용한다. 줄바꿈 보존이 필요하면 `.response-content { white-space: pre-wrap; }`만 별도 CSS로 유지한다.
- 응답 페이지에는 전체 사이트 헤더나 메뉴를 중복해서 넣지 않는다. 직접 요청과 우측 iframe 표시 모두 가능한 완전한 HTML 문서를 생성한다.

## 확인과 완료 보고

메뉴 경로, 컨트롤러 매핑, 뷰 반환값과 HTML 위치가 일치하는지 확인한다. Java를 변경하면 프로젝트 루트에서 `./gradlew.bat compileJava --offline`(Windows) 또는 `./gradlew compileJava --offline`을 실행한다. 의존성 캐시 문제로 실패하면 원인을 확인하고 필요한 경우 일반 모드로 실행한다.

실행 중인 서버나 브라우저 검증 도구가 있으면 메뉴 선택 후 우측 응답 카드와 오류 여부를 확인한다. 컴파일만 확인했다면 브라우저 동작까지 검증했다고 보고하지 않는다.

`bin/`, `build/` 등 생성물은 직접 수정하지 않는다. Git 커밋은 사용자가 요청할 때 수행한다. 완료 보고에는 추가한 메뉴, 컨트롤러 경로, 생성한 템플릿, 실제 검증 결과를 간단히 적는다.
