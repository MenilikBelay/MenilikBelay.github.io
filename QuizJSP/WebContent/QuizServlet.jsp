<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="models.Quiz" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Quiz</title>
  </head>
  <body>
    <h1>The Number Quiz</h1>
    <p>Your current score is at ${ QUIZ.score }</p>
    <br />
    
    <p>Guess the next number in the sequence</p>
    <% if ((String)request.getAttribute("hint") != null) {
    	out.print("<p>HINT: " + ((Quiz)request.getSession().getAttribute("QUIZ")).getHint() + "</p>");
    } %>
    <p>${ QUIZ.question }<form method="POST" action="hint"><input type="submit" value="Hint"/></form></p> 
    <form method="POST">
      But first, Age: <input type="number" name="age" placeholder="Age" min="4" max="110" required
      		value="${ age }"
      /> 
      <% 
      if (((String)request.getAttribute("age_exception")) != null) {
    	  out.print("<span style='color: red'>" + request.getAttribute("age_exception") + "  </span>");
      }%> <br />
      <% pageContext.setAttribute("answer", ((Quiz)request.getSession().getAttribute("QUIZ")).getAnswer()); %>
      Your answer: <input type="number" name="guess" value="${ answer }"/><br /><input
        type="submit"
      />
    </form>
  </body>
</html>
