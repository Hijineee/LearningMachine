# Learning Machine

<h3> IT 교육 컨텐츠를 제공하는 웹 사이트</h3>
<small>제작기간 : 2022.07.11 ~ 2022.08.01</small> <br>
<small>역할 : 관리자페이지 구현, 커뮤니티 게시판 구현, My Page 구현 </small><br>
<small>개발자 : <a href = "https://github.com/IkhyeonAhn">안익현</a>, <a href="https://github.com/Hijineee">이희진</a>,  <a href = "https://github.com/skek3039">명재성</a>, <a href="https://github.com/Moonmaji">문형석</a>, <a href="https://github.com/ParkGuTy">박성균</a></small>
<hr>
- 개발 환경

    STS ver3.9.9 released / Tomcat 9.0 / mariaDB 10.7





![index](https://user-images.githubusercontent.com/101648395/182089931-e03b4948-a361-4a54-9f7f-2a7b2f639360.png)

## 서비스 기획 및 방향성 설계
>  고품질의 IT교육 컨텐츠를 제공하는 교육사이트 (참고사이트 : 인프런)    
![1](https://user-images.githubusercontent.com/101648395/186351569-35c71eda-e1ae-4b5f-85d9-0647551676db.png)
 
## SQL코드 <br>
 + [관리자게시판 SQL 코드](https://github.com/Hijineee/LearningMachine/blob/main/src/main/resources/mapper/Admin.xml)
 + [관리자 매출 SQL 코드](https://github.com/Hijineee/LearningMachine/blob/main/src/main/resources/mapper/Payment.xml)
 + [커뮤니티 게시판 SQL 코드](https://github.com/Hijineee/LearningMachine/blob/main/src/main/resources/mapper/Community.xml)
 + [My Page 게시판 SQL 코드](https://github.com/Hijineee/LearningMachine/blob/main/src/main/resources/mapper/MyService.xml)
 
<hr>
	
	
## UI <br>
+ 관리자페이지 주요기능(1/4) <br>
https://github.com/Hijineee/LearningMachine/blob/c8298dda9f8a715f64ebd40d4fa8a2bc7737117f/src/main/java/com/learning/Admin/Controller/PaymentController.java#L40
 
![image](https://user-images.githubusercontent.com/101648395/186360563-66e393dd-0212-4a4d-9f4b-e28bd19774d1.png) <br>
chart.js를 사용해서 월별 매출을 그래프화하여 구현 했고, 구매한 강좌수를 토대로 카테고리별로 원형그래프로 구현.

![image](https://user-images.githubusercontent.com/101648395/186366033-519aa9e5-edda-4f02-a9dd-330a49ccab11.png)<br>
환불신청목록을 띄어 환불 승인 할 수 있는 페이지
<br><br><br><br><br>


+ 관리자페이지 주요기능(2/4) <br>
https://github.com/Hijineee/LearningMachine/blob/49771483e6f490ddfc9b92263a5131126cf4aa2f/src/main/java/com/learning/Admin/Controller/AdminController.java#L331

>가입한 학생리스트를 보여주고 상세정보 확인 가능한 페이지.

<br><br>
![image](https://user-images.githubusercontent.com/101648395/186361467-ff0ee0c5-fe17-4e9d-9265-7458b45b031e.png)<br>

<br>
![image](https://user-images.githubusercontent.com/101648395/186366530-ecb60782-b8d9-43b1-8789-a5efa80b0892.png)<br>
<br><br><br><br><br>
	
	
+ 관리자페이지 주요기능(3/4) <br>
https://github.com/Hijineee/LearningMachine/blob/559f3706da2092a96202fc1eb22e6e6899fb7064/src/main/java/com/learning/Admin/Controller/AdminController.java#L240 

>강사관리 페이지로서, 강사로 신청들어온 내역이 있다면 승인및 거부 를 처리하고 강사들이 올릴 강의를 승인 및 거부를 하는 페이지. <br><br><br>
	
**강사신청내역 페이지** <br>
![image](https://user-images.githubusercontent.com/101648395/186366192-36e5f5b5-f451-4b4f-bb95-01543e8359b2.png) <br><br><br>

**강의신청내역 페이지**<br>
![image](https://user-images.githubusercontent.com/101648395/186370925-a07b3237-ede0-4d6d-b077-8fb90a8df163.png) <br><br><br>
>신청된 강의를 상세확인하며, 승인및 거부가 가능하도록 함.

**강의 카테고리 관리 페이지**
![image](https://user-images.githubusercontent.com/101648395/186370308-11545e34-b1aa-4de2-a0ec-7bd4f72d0647.png)
>Ajax를 이용하여 카테고리 추가, 삭제 구현
	
<br><br><br><br><br>	
+ 관리자페이지 주요기능(4/4) <br>
>공지사항 / 자유게시판 관리 페이지

![image](https://user-images.githubusercontent.com/101648395/186373337-7fe845cc-b7a2-4acf-97b0-e65293358662.png)<br>
>공지사항을 쓸수 있으며, 자유게시판에 부적절한 글이 올라온 경우 삭제 가능하도록 구현.

<br><br><br><br><br>
+ 커뮤니티페이지 / MyPage 기능 <br>
https://github.com/Hijineee/LearningMachine/blob/5347bde42e49821ca32c889745463b9db6877360/src/main/java/com/learning/Common/Contoller/HomeController.java#L132
<br>
![image](https://user-images.githubusercontent.com/101648395/186374407-ea152216-ec4a-41e7-81f0-01c1eb8a8aa7.png)

>Q&A게시판으로  강의 질문게시글을 카테고리별로 볼 수 있음.<br>
![image](https://user-images.githubusercontent.com/101648395/186374782-8411c50b-f9ef-432f-9678-5b18d86ee084.png)
>Q&A클릭 시 상세보기 화면 구현. <br><br>

![image](https://user-images.githubusercontent.com/101648395/186376485-e2ddc34f-3205-4ee2-8a2f-222c4e745981.png)
>자유게시판 상세화면으로 로그인한 사람만 댓글을 달 수 있고, 쿠키를 이용한 조회수 증가 구현. <br><br>

![image](https://user-images.githubusercontent.com/101648395/186377089-bca2384c-48ec-4d42-ae58-5f06a9ec93a0.png)
>MyPage로 비밀번호 변경 및 닉네임 변경이 가능하다. 
<br><br>

![image](https://user-images.githubusercontent.com/101648395/186385343-6eec8a28-b8a4-46b5-be6d-e997c81df59f.png)
>구매내역과 환불신청할 수 있는 페이지 입니다. 


## 프로젝트 하면서 느꼈던 점
> 어려움에 직접 부딪히면서 여러번의 시행착오끝에 코드가 완성되면 내 것이 된다는 느낌이 들었습니다. 100번 이론공부보다, 직접프로젝트를 만들면서 한번 겪어보는 것이 효과가 좋았고 협업을 통해 사람들마다 다름을 인정하고 협동하여 수행하는 점에서 많은 커뮤니케이션역량을 키웠습니다. 

## 프로젝트 하면서 아쉬웠던 점
> UI 쪽으로 조금 더 완성도가 높지 않은 점에서 아쉬웠고, 시간여유가 된다면 매출 차트 부분에서 더 상세하게 그래프를 표현하고 당기순이익등 매출관리에 더 상세하게 구현할 수 없음이 아쉬웠습니다. 더 효율적인 DATABASE 설계를 못함이 아쉬워 이부분에선 조금 더 보완을 해야겠다고 느꼈습니다.
