# To-Do List 서블릿 애플리케이션

Java 서블릿으로 만든 간단한 To-Do 리스트 애플리케이션입니다. 사용자는 할 일 목록을 조회하고, 새로운 할 일을 추가할 수 있으며, 접근 제어를 통해 본인 소유의 할 일만 수정하거나 삭제할 수 있습니다.

## 주요 기능

- **할 일 목록 조회**: 모든 할 일 목록을 조회합니다.
- **할 일 추가**: 사용자가 본인의 할 일을 추가할 수 있습니다.
- **권한 제어**: 각 사용자는 본인의 할 일만 수정하거나 삭제할 수 있습니다.

## 프로젝트 구조

- **Task.java**: 할 일을 나타내는 클래스입니다.
- **TaskRepository.java**: 할 일 데이터를 저장하고 관리합니다.
- **TaskServlet.java**: 할 일 목록 조회 및 할 일 생성을 처리합니다.
- **AuthorizationFilter.java**: 할 일의 소유자만 수정/삭제할 수 있도록 권한을 검증하는 필터입니다.
  
## API 목록 
### 동기처리
- **GET v1/tasks**: 모든 할 일 목록을 반환합니다. (id를 파라미터로 전달할 경우 특정 할일만 조회가 가능합니다.)
- **POST v1/tasks**: 새 할 일을 추가합니다. owner와 description 파라미터가 필요합니다.
- **PUT v1/tasks**: 할 일을 변경합니다. id와 owner, description 파라미터가 필요합니다.
- **DELETE v1/tasks**: 할 일을 삭제합니다. id 파라미터가 필요합니다.

### 비동기처리 (request.startAsync() 사용) 
- **GET v2/tasks**: 모든 할 일 목록을 반환합니다. (id를 파라미터로 전달할 경우 특정 할일만 조회가 가능합니다.)
- **POST v2/tasks**: 새 할 일을 추가합니다. owner와 description 파라미터가 필요합니다.
- **PUT v2/tasks**: 할 일을 변경합니다. id와 owner, description 파라미터가 필요합니다.
- **DELETE v2/tasks**: 할 일을 삭제합니다. id 파라미터가 필요합니다.
