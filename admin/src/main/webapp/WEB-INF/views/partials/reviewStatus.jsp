<div>
  <div class="col-md-12"  ng-show="displayMode">
    <div class="panel panel-blue">
      <div class="panel-heading dark-overlay"><span class="glyphicon glyphicon-check"></span>Review Status List</div>
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
                  <th text-align="center">Edit</th>
                  <th text-align="center">Delete</th>
                </tr>
                <tr ng-repeat="row in allActive">
                  <td align="center">{{row.id}}</td>
                  <td align="center">{{row.name}}</td>
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
        <input class="form-control" placeholder="Review Status name" ng-model="newReviewStatus.name">
      </div>
      <button type="submit" class="btn btn-primary"  ng-click="create()">Submit</button>
    </form>
  </div>
  <div class="col-md-12"  ng-show="updateMode">
    <form role="form">

      <div class="form-group">
        <label>Name</label>
        <input class="form-control" placeholder="Review Status name" ng-model="updateReviewStatus.name">
      </div>
      <button type="submit" class="btn btn-primary"  ng-click="update()">Submit</button>
    </form>
  </div>
</div><!--/.row-->