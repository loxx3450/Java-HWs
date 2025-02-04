<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <style>
        div {
          margin-bottom: 60px;
        }
    </style>
</head>
<body>
<div>
    <h3>Task 1</h3>
    <a href="servlet-task1">Here</a>
</div>

<div>
    <h3>Task 2-3</h3>
    <form method="post" action="servlet-task2">
        <label for="number1">Number 1</label>
        <input type="number" id="number1" name="number1" required>

        <br>

        <label for="number2">Number 2</label>
        <input type="number" id="number2" name="number2" required>

        <br>

        <label for="number3">Number 3</label>
        <input type="number" id="number3" name="number3" required>

        <br>

        <p>Pick operation</p>
        <input type="radio" id="operation-max" name="operation" value="max" required>
        <label for="operation-max">max</label>
        <input type="radio" id="operation-min" name="operation" value="min" required>
        <label for="operation-min">min</label>
        <input type="radio" id="operation-avg" name="operation" value="avg" required>
        <label for="operation-avg">avg</label>

        <br><br>

        <button type="submit">Click here!</button>
    </form>
</div>

<div>
    <h3>Task 4</h3>
    <form method="post" action="servlet-task4">
        <label for="text">Your text:</label>
        <input type="text" id="text" name="text">

        <br><br>

        <button type="submit">Click here!</button>
    </form>
</div>

<div>
    <h3>Task 5</h3>
    <form method="post" action="calculator-servlet">
        <label for="value1">Value 1</label>
        <input type="number" id="value1" name="value1" required>

        <br>

        <label for="value2">Value 2</label>
        <input type="number" id="value2" name="value2" required>

        <br>

        <p>Pick operation</p>
        <input type="radio" id="operation-addition" name="operation" value="addition" required>
        <label for="operation-addition">addition</label><br>
        <input type="radio" id="operation-subtraction" name="operation" value="subtraction" required>
        <label for="operation-subtraction">subtraction</label><br>
        <input type="radio" id="operation-multiplication" name="operation" value="multiplication" required>
        <label for="operation-multiplication">multiplication</label><br>
        <input type="radio" id="operation-division" name="operation" value="division" required>
        <label for="operation-division">division</label><br>
        <input type="radio" id="operation-exponentiation" name="operation" value="exponentiation" required>
        <label for="operation-exponentiation">exponentiation</label><br>
        <input type="radio" id="operation-percentage" name="operation" value="percentage" required>
        <label for="operation-percentage">percentage</label><br>


        <br><br>

        <button type="submit">Click here!</button>
    </form>
</div>

</body>
</html>