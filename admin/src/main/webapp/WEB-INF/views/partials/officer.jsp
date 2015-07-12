<div>
    <div class="col-md-12"  ng-show="displayMode">
        <div class="panel panel-blue">
            <div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-check"></span>Officer List</div>
            <div class="panel-footer">
                <div class="input-group">
							<span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo" ng-click="switchToCreateMode()">Create</button>
							</span>
                </div>
            </div>
            <div class="panel-body">
                <ul class="todo-list">
                    <li class="todo-list-item">
                        <div>
                            <table width="100%">
                                <tr>
                                    <th text-align="center">ID</th>
                                    <th text-align="center">Name</th>
                                    <th text-align="center">Department</th>
                                    <th text-align="center">Edit</th>
                                    <th text-align="center">Delete</th>
                                </tr>
                                <tr ng-repeat="row in allActive">
                                    <td align="center">{{row.id}}</td>
                                    <td align="center">{{row.name}}</td>
                                    <td align="center">{{row.department.name}}</td>
                                    <td align="center"><span class="glyphicon glyphicon-pencil"  ng-click="selectForUpdate(row)"></span></td>
                                    <td align="center"><span class="glyphicon glyphicon-trash"  ng-click="delete(row)"></span></td>
                                </tr>
                            </table>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div><!--/.col-->
    <div class="col-md-12"  ng-show="createMode">
        <form role="form">

            <div class="form-group">
                <label>Name</label>
                <input class="form-control" placeholder="Name" ng-model="newOfficer.name">
            </div>
            <div class="form-group">
                <label>Username</label>
                <input class="form-control" placeholder="Username" ng-model="newOfficer.username">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input class="form-control" placeholder="Password" ng-model="newOfficer.password">
            </div>
            <div class="form-group">
                <label>Department</label>
                <select class="form-control" ng-options="department.name for department in departments" ng-model="selectedDepartment"></select>
            </div>
            <div class="form-group">
                <label>Designation</label>
                <select class="form-control" ng-options="designation.name for designation in designations" ng-model="selectedDesignation"></select>
            </div>
            <div class="form-group">
                <label>Address</label>
                <input class="form-control" placeholder="Address" ng-model="newOfficer.address">
            </div>
            <div class="form-group">
                <label>Mobile</label>
                <input class="form-control" placeholder="Mobile" ng-model="newOfficer.mobile">
            </div>
            <div class="form-group">
                <label>Photo</label>
                <input class="form-control" placeholder="Photo" ng-model="newOfficer.photo">
            </div>
            <div class="form-group">
                <label>Role</label>
                <select class="form-control" ng-options="role for role in roles" ng-model="selectedRole"></select>
            </div>
            <button type="submit" class="btn btn-primary"  ng-click="create()">Submit</button>
        </form>
    </div>
    <div class="col-md-12"  ng-show="updateMode">
        <form role="form">

            <div class="form-group">
                <label>Name</label>
                <input class="form-control" placeholder="Name" ng-model="updateOfficer.name">
            </div>
            <div class="form-group">
                <label>Username</label>
                <input class="form-control" placeholder="Username" ng-model="updateOfficer.username">
            </div>
            <div class="form-group">
                <label>Password</label>
                <input class="form-control" placeholder="Password" ng-model="updateOfficer.password">
            </div>
            <div class="form-group">
                <label>Department</label>
                <select class="form-control" ng-options="department.name for department in departments" ng-model="selectedDepartment"></select>
            </div>
            <div class="form-group">
                <label>Designation</label>
                <select class="form-control" ng-options="designation.name for designation in designations" ng-model="selectedDesignation"></select>
            </div>
            <div class="form-group">
                <label>Address</label>
                <input class="form-control" placeholder="Address" ng-model="updateOfficer.address">
            </div>
            <div class="form-group">
                <label>Mobile</label>
                <input class="form-control" placeholder="Mobile" ng-model="updateOfficer.mobile">
            </div>
            <div class="form-group">
                <label>Photo</label>
                <input class="form-control" placeholder="Photo" ng-model="updateOfficer.photo">
            </div>
            <div class="form-group">
                <label>Role</label>
                <select class="form-control" ng-options="role for role in roles" ng-model="selectedRole"></select>
            </div>
            <button type="submit" class="btn btn-primary"  ng-click="update()">Submit</button>
        </form>
    </div>
</div><!--/.row-->