<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>EL Demo</h1>
        <%
            int number1 = 10;
            int number2 = 20;
            request.setAttribute("number1", number1);
            request.setAttribute("number2", number2);
            int sum = number1 + number2;
            //phep nhan, phep chia, phep tru
            int multiple = number1 * number2;
            int minus = number1 - number2;
            try {
                float divide = number1 / number2;
                request.setAttribute("divide", divide);
            } catch (Exception e) {
                request.setAttribute("divide", "Khong the chia cho 0");
            }
            request.setAttribute("sum", sum);
            request.setAttribute("multiple", multiple);
            request.setAttribute("minus", minus);
            boolean condition1 = true;
            boolean condition2 = false;
            request.setAttribute("con1", condition1);
            request.setAttribute("con2", condition2);
            //chuyển đoi tu dong tu integer sang double
            double result = number1 / 2;
            request.setAttribute("result", result);
            //ep kieu result1
            double number3 = 10.4345;
            int result1 = (int) number3;
            request.setAttribute("num3", number3);
            request.setAttribute("result1", result1);
        %>
        <h2>Number1 = ${number1}</h2>
        <h2>Number2 = ${number2}</h2>
        <h2>Sum = ${sum}</h2>
        <h2>Multiple = ${multiple}</h2>
        <h2>Minus = ${minus}</h2>
        <h2>Divide = ${divide}</h2>
        <h2>Condition1 = ${con1}</h2>
        <h2>Condition2 = ${con2}</h2>
        <h2>&& = ${con1 and con2}</h2>
        <h2>|| = ${con1 or con2}</h2>
        <h2>! con1 = ${!con1}</h2>
        <h2>! con2 = ${!con2}</h2>
        <h2>Result = ${result}</h2>
        <h2>num3 = ${num3}</h2>
        <h2>Result1 of num3= ${result1}</h2>
    </body>
</html>
