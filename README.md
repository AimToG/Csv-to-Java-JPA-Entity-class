# Csv to Spring Jpa Entity class file

CSV 파일에서 데이터를 추출해 JPA Entity 클래스 파일 생성

## 빌드

* 프로젝트 루트의 `build.sh` 파일을 실행하면 out 디렉터리 내에 csvtojpa.jar 파일이 생성됩니다.
	* `./build.sh`

## 사용법

* 변환하려는 csv 파일을 `/out/input` 디렉터리에 놓습니다.
* `execute.sh` 파일을 실행합니다.
  * `./execute.sh`
* `/out/dist` 디렉터리가 생기고 내부에 JPA entity가 생성됩니다.