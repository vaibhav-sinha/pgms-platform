<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user-1
  Date: 5/7/15
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="officerApp">
<head>
  <title>Officer CRUD interface</title>
  <script src="<c:url value="/resources/lib/angular.min.js" />"></script>
  <script src="<c:url value="/resources/lib/angular-base64.min.js" />"></script>
  <script src="<c:url value="/resources/js/officer.js" />"></script>
</head>
<body ng-controller="officerController">
<button ng-click="switchToCreateMode()">Add new Officer</button>
<br>
<div ng-show="createMode">
  <form>
    <input type="text" name="Name" ng-model="newOfficer.name"/>
    <input type="text" name="Username" ng-model="newOfficer.username"/>
    <input type="text" name="Password" ng-model="newOfficer.password"/>
    <select ng-options="designation.name for designation in designations" ng-model="selectedDesignation"></select>
    <select ng-options="department.name for department in departments" ng-model="selectedDepartment"></select>
    <input type="text" name="Address" ng-model="newOfficer.address"/>
    <input type="text" name="Mobile" ng-model="newOfficer.mobile"/>
    <input type="text" name="Photo" ng-model="newOfficer.photoUrl"/>
    <select ng-options="role for role in roles" ng-model="selectedRole"></select>
    <input type="submit" ng-click="create()">
  </form>
</div>
<div ng-show="displayMode">
  <table>
    <tr ng-repeat="row in allActive">
      <td>{{row.id}}</td>
      <td>{{row.name}}</td>
      <td><button ng-click="delete(row)">Delete</button></td>
      <td><button ng-click="selectForUpdate(row)">Update</button></td>
    </tr>
  </table>
</div>
<div ng-show="updateMode">
  <input type="text" name="Name" ng-model="updateOfficer.name"/>
  <select ng-options="department.name for department in departments" ng-model="selectedDepartment"></select>
  <input type="submit" ng-click="update()">
</div>
</body>
</html>
