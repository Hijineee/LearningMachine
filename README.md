# Learning Machine

<h3> IT 교육 컨텐츠를 제공하는 웹 사이트</h3>
<small>제작기간 : 2022.07.11 ~ 2022.08.01</small> <br>
<small>역할 : 관리자페이지 구현, 커뮤니티 게시판 구현, My Page 구현
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
- 관리자페이지 주요기능(1/4) <br>
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
	
**강의 카테고리 관리 페이지**
![image](https://user-images.githubusercontent.com/101648395/186370308-11545e34-b1aa-4de2-a0ec-7bd4f72d0647.png)
>Ajax를 이용하여 카테고리 추가, 삭제 구현
	

