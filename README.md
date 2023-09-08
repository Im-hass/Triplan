# 🏝TriPlan

<br />

<div align="center">

<img width='250px' alt="TriPlan_logo" src="https://user-images.githubusercontent.com/77854486/232034326-2d2db07e-ef10-43b3-a912-8132054903a1.png"/>

</div>

<br />

## ✈️ 서비스 소개

전국 여행지 조회 기능을 바탕으로 여행 계획을 짜고, 다른 사용자들과 공유할 수 있는 여행 계획 서비스입니다.

## ✨ Member

<div align="center">

| [<img src = "https://avatars.githubusercontent.com/u/77854486?v=4" width = 100>](https://github.com/Im-hass) | [<img src = "https://avatars.githubusercontent.com/u/90020798?v=4" width = 100>](https://github.com/jjoyra) |
| :----------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: |
|                                                    임희선                                                    |                                                   조희라                                                    |
|                                                    FE, BE                                                    |                                                   FE, BE                                                    |

</div>

## 📌 기술 스택

- Vue.js, Bootstrap
- SpringBoot, MyBatis
- MySQL

## 📌 주요 기능

### 여행지 찾기

- 한국관광정보의 국문관광정보서비스 API를 활용하여 지역별 여행지 정보를 제공합니다.
- 키워드, 여행지 유형, 지역 등 다양한 필터에 맞는 여행지를 검색할 수 있습니다.
- `KakaoMap API`를 사용하여 지도 위에서 여행지 위치를 보여줍니다.

### 플랜

- 원하는 여행지들을 선택하여 여행 계획을 생성하고, 다른 사용자들과 공유할 수 있습니다.
- 여행 코스에 대해 이동 시간을 계산하여 보여줍니다.
- 여행을 다녀와서 여행 계획에 대한 후기를 남길 수 있습니다.

## 📌 기능별

|                                                                                                                      |                                                                                                                    |
| :------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------: |
|    ![메인페이지](https://github.com/jjoyra/simple-node-app/assets/90020798/22b7996e-aa7e-4534-98e7-cf6e369e4c84)     | ![여행지 둘러보기](https://github.com/jjoyra/simple-node-app/assets/90020798/e22cf90c-30a1-484d-b805-3d19332dd8af) |
|                                                     메인 페이지                                                      |                                                    여행지 찾기                                                     |
|   ![공지사항 검색](https://github.com/jjoyra/simple-node-app/assets/90020798/101c03c7-e23a-4c1a-b7e9-568286a07626)   |    ![공지사항](https://github.com/jjoyra/simple-node-app/assets/90020798/469b18de-6e6b-4fa1-aa16-963e854b7b6e)     |
|                                                    공지사항 검색                                                     |                                                   공지사항 목록                                                    |
| ![공지사항 상세보기](https://github.com/jjoyra/simple-node-app/assets/90020798/cfb82fd9-95fd-4586-8671-0d2f5877c8ea) |  ![공지사항 작성](https://github.com/jjoyra/simple-node-app/assets/90020798/7f9f77ed-db61-428f-a523-e7ebc3a223f3)  |
|                                                  공지사항 상세보기                                                   |                                                   공지사항 등록                                                    |
|  ![공지사항 수정1](https://github.com/jjoyra/simple-node-app/assets/90020798/d9d24af6-1c93-47b3-b789-b30c8c2cd948)   | ![공지사항 수정2](https://github.com/jjoyra/simple-node-app/assets/90020798/8bece180-2bd3-4ec9-b7a9-9cfb6b3c3bc3)  |
|                                                    공지사항 수정1                                                    |                                                   공지사항 수정2                                                   |

<br />

## 📌 프로젝트 구조

<details> 
<summary>frontend</summary>

```bash
├─public
├─package-lock.json # 프로젝트 의존성 관리
├─package.json # 프로젝트 의존성 관리
└─src
  ├─App.vue # 시작 컴포넌트
  ├─api
  │  └─http.js # axios 기본 설정
  ├─assets # 이미지 파일
  ├─components
  │  ├─attraction # 관광지(여행지)
  │  ├─board # 글 Input
  │  ├─common # 헤더, 푸터 등
  │  ├─community # 커뮤니티
  │  ├─mypage # 마이페이지
  │  │  ├─favoattraction # 좋아하는 관광지(여행지)
  │  │  ├─favoplan # 좋아하는 여행계획
  │  │  └─myplan # 나의 여행계획
  │  ├─notice # 공지사항
  │  ├─review # 여행계획 리뷰
  │  └─ui # UI
  ├─plugins # vue axios, bootstrap 설정
  ├─router # 라우팅
  ├─store # Vuex
  └─views # 페이지 컴포넌트
    ├─MainView.vue # 메인 컴포넌트
    └─...
```

</details>
