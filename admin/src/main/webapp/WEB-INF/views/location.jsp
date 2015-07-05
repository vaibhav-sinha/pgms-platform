<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user-1
  Date: 5/7/15
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="locationApp">
<head>
  <title>Location CRUD interface</title>
  <script src="<c:url value="/resources/lib/angular.min.js" />"></script>
  <script src="<c:url value="/resources/lib/angular-base64.min.js" />"></script>
  <script src="<c:url value="/resources/js/location.js" />"></script>
</head>
<body ng-controller="locationController">
<button ng-click="switchToCreateMode()">Add new Location</button>
<br>
<div ng-show="createMode">
  <form>
    <input type="text" name="Name" ng-model="newLocation.name"/>
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
  <input type="text" name="Name" ng-model="updateLocation.name"/>
  <input type="submit" ng-click="update()">
</div>
</body>
</html>