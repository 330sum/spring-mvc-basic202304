<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style>
    .wrapper {
        width: 500px;
        margin: auto;
        padding: 20px;
        border-radius: 4px;
        font-size: 18px;
        border: 1px solid #000;
        text-align: center;
    }

    h1 {
        font-size: 40px;
        font-weight: 700;
    }
</style>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>
    <div class="wrapper">

        <section class="board-main">
            <div class="first">

                <h1>${board.title}</h1>
            </div>
            <div class="second">

                <p>${board.content}</p>
            </div>
            <div class="z">
                <button class="del-btn">

                </button>
            </div>


        </section>



    </div>
</body>

</html>