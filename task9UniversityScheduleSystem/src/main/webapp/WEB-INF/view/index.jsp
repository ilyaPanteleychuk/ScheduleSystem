<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="https" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <spring:url value="/resources/styles/style.css" var="styleCss" />
    <link href="${styleCss}" rel="stylesheet" />
    <link href="https:https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap"
          rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <header class="header">
        <div class="header__column faculty">
            <h3 class="header__column__title">Faculty</h3>
            <select name="faculty" id="faculty" class="header__column__input">
                <option value="fit">FIT</option>
                <option value="ftp">FTP</option>
                <option value="frgtb">FRGTB</option>
            </select>
        </div>

        <div class="header__column">
            <h3 class="header__column__title">Course</h3>
            <select name="course" id="course" class="header__column__input">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>

        <div class="header__column">
            <h3 class="header__column__title">Group</h3>
            <select name="group" id="group" class="header__column__input">
                <option value="1-1">1-1</option>
                <option value="2-2">2-2</option>
                <option value="3-3">3-3</option>
            </select>
        </div>
    </header>

        <table class="table">
            <tr>
              <th>
                <a class="table__link" href="./form.html">mon</a>
              </th>
              <th>tue</th>
              <th>wed</th>
              <th>thu</th>
              <th>fri</th>
              <th>sat</th>
              <th>sun</th>
            </tr>
            <tr>
                <th class="table__date">1</th>
                <th class="table__date">2</th>
                <th class="table__date">3</th>
                <th class="table__date">4</th>
                <th class="table__date">5</th>
                <th class="table__date">6</th>
                <th class="table__date">7</th>
              </tr>
            <tr>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Huy huyevskiy jopa jopivskaya
                </div>
                <div class="table__field__rectangle">
                    Huy kehefew jopa jopivskaya
                </div>
                <div class="table__field__rectangle">
                    Huy asasfa jopa jopivskaya
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Kristina
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Kristina
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Kristina
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Kristina
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Audience 200 Teacher Lelavin Type Practice Math
                </div>
              </td>
              <td class="table__field">
                <div class="table__field__rectangle">
                    Kristina
                </div>
              </td>
            </tr>
          </table>
</body>
</html>