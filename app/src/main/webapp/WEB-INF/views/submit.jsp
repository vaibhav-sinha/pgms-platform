<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user-1
  Date: 5/7/15
  Time: 12:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="complaintApp">
<head>
  <title>Officer CRUD interface</title>
  <script src="<c:url value="/resources/lib/angular.min.js" />"></script>
  <script src="<c:url value="/resources/js/complaint.js" />"></script>
</head>
<body ng-controller="complaintController">
<div>
  Enter complaint details
  <div>
    <form>
      <input type="text" name="Description" ng-model="newComplaint.description"/>
      <input type="checkbox" name="Urgent" ng-model="newComplaint.urgent"/>
      <select ng-options="location.name for location in locations" ng-model="selectedLocation"></select>
      <select ng-options="department.name for department in departments" ng-model="selectedDepartment"></select>
      <select ng-options="category.name for category in categories" ng-model="selectedCategory"></select>
    </form>
  </div>
  <div>
    Enter your details
    <form>
      <input type="text" name="Name" ng-model="newComplaint.name"/>
      <input type="text" name="Email" ng-model="newComplaint.email"/>
      <input type="text" name="Address" ng-model="newComplaint.address"/>
      <input type="text" name="Mobile" ng-model="newComplaint.mobile"/>
      <input type="text" name="Pincode" ng-model="newComplaint.pincode"/>
    </form>
  </div>
  <input type="submit" ng-click="create()">
</div>
</body>
</html>
