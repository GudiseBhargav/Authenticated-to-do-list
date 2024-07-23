<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    HttpSession mySession = request.getSession(false);
    if (mySession == null || mySession.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
	        background-image: linear-gradient(to right, rgb(0, 128, 192), rgb(0, 255, 255));
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
        }
        h2{
             text-align:center;
             
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }
        li:last-child {
            border-bottom: none;
        }
        .task {
            display: flex;
            justify-content: space-between;
            align-items: center;
            
            
        }
        .task input {
            margin-right: 10px;
        }
        .header{
        display:flex;
        justify-content:space-around;
        }
        .header button{
        	width:fit-content;
        	height:fit-content;
        	margin-top:25px;
        	font-size:larger;
        	background-color:red;
        	color:white;
        	border-radius:5px;
        	cursor:pointer;
        }
        #taskForm{
        align-items:center;
        text-align:center;
        }
        h2 a{
        text-decoration:none;
        
        }
        
    </style>
</head>
<body>
    <div class="container">
    	<div class="header">
        <h1>Welcome, <%= mySession.getAttribute("name") %>!</h1>
        <h2><a href="logout">Logout</a></h2>
        </div>
        <h2>Your To-Do List</h2>
        <form id="taskForm">
            <input type="text" id="taskInput" placeholder="Enter a new task" required>
            <button type="submit">Add Task</button>
        </form>
        <ul id="taskList"></ul>
    </div>

    <script>
        document.getElementById('taskForm').addEventListener('submit', function(event) {
            event.preventDefault();
            var taskInput = document.getElementById('taskInput');
            var taskText = taskInput.value.trim();
            if (taskText) {
                addTask(taskText);
                taskInput.value = '';
                taskInput.focus();
            }
        });

        function addTask(taskText) {
            var taskList = document.getElementById('taskList');
            var li = document.createElement('li'); 
            li.innerHTML = '<div class="task"><span>' + taskText + '</span><button onclick="removeTask(this)">Remove</button></div>';
            taskList.appendChild(li);
        }

        function removeTask(button) {
            var li = button.parentElement.parentElement;
            li.remove();
        }
    </script>
</body>
</html>
