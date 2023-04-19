<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h1>food.jsp입니다.</h1>
   
    <form action="/spring/food-select" method="post">
        # 음식명: <input type="text" name="foodName"> <br>
        # 음식 카테고리:
        <select name="category">
            <option value="KOREAN">한식</option>
            <option value="WESTERN">양식</option>
            <option value="CHINESE">중식</option>
        </select>
        <br>
        <button type="submit">확인</button>


        GET방식 (데이터 조회)
        : URL 길이 한정, 보안위험, 캐싱

        POST방식 (데이터 갱신)
        : 크기 제한없고, URL데이터 노출 없음. 캐싱 안함.


    </form>

</body>
</html>