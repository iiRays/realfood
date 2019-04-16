<%-- 
    Document   : studentDisplayMeals
    Created on : Mar 17, 2019, 3:45:43 PM
    Author     : mast3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Attribution: https://fonts.google.com/specimen/Montserrat?selection.family=Montserrat:100,200,400 -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:100,200,400" rel="stylesheet">
        <link href="CSS/studentDisplayMeals.css" rel="stylesheet">
        <link href="CSS/student.css" rel="stylesheet">
        <link href="CSS/commonStyles.css" rel="stylesheet">
        <link href="CSS/students.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose meals.</title>
    </head>
    <body>
        <%
            session = request.getSession(false);

            String permission = "";

            try {
                permission = (String) session.getAttribute("permission");

                if (permission == null) {
                    request.setAttribute("errorMsg", "Please login.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    return;
                }

            } catch (NullPointerException ex) {
                request.setAttribute("errorMsg", "Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            
            // Allow student only
            if (!permission.equalsIgnoreCase("student")) {
                request.setAttribute("errorMsg", "You are not allowed to visit that page.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {
                Student stud = (Student) session.getAttribute("stud");
                int credits = stud.getCredits();    // Obtain student's credits amount
                
                String nullResultsStr = request.getParameter("nullResults");
                boolean nullResults = false;
                if(nullResultsStr.equalsIgnoreCase("true"))
                    nullResults = true;
                
                if(nullResultsStr == "" || nullResultsStr == null)
                    response.sendRedirect("LoadStudentDashboard");
                
                
        %>
       
       
        <h1 class="title">Meals Menu.</h1>
        <%
        if(!nullResults){
        %>
 
        <h5 id="subtitle">Here's a summary of our meals.</h5>
        <div class="errorMsg">${errorMsg}</div>
        <!-- Search bar -->
        <form class="searchForm">
            <input type="text" name="query" placeholder="search..." class="searchBar"/>
        </form>

        <!-- Table -->
        <form action="SelectMealServlet" method="post" id="mealOrder">
            <div class="mealsContainer">
                <table>
                    <tr>
                        ${queryResult}
                </table>

            </div>
                <br/><br/>
        </form>
                <%
                } else{ 
                %>
                <h1 style="color: white; font-size: 40px; margin-top: 300px;">It seems like there's no meal available yet. Stay tuned!</h1>
                
                <%}%>
                <a class="nextButton" href="LoadStudentDashboard">Back</a>
                
             <!-- Display student's credits -->
        <h6 class="credits"><%=credits%> credits</h6>
        <%}%>
    </body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="studentDisplayMeals.js" type="text/javascript"></script>
</html>
