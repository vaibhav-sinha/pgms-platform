<div class="box-body table-responsive no-padding">
    <table class="table table-hover">
        <thead>
        <td><b>Name</b></td>
        <td ng-repeat="key in keys"><b>{{key}}</b></td>
        </thead>
        <tbody>
        <tr ng-repeat="data in datas">
            <td>{{data.name}}</td>
            <td ng-repeat="key in keys">{{data.values[key]}}</td>
        </tr>
        </tbody>
    </table>
</div>
