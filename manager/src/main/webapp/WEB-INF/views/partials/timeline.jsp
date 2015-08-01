<div class ="col-md-9">
<ul class="timeline">
    <li ng-repeat="historyElem in updateHistory | reverse" ng-controller="TimeLineElementController">
        <i class="fa fa-user bg-aqua"></i>
        <div class="timeline-item">
            <span class="time"><i class="fa fa-clock-o"></i>{{historyElem.updatedOn | timeago}}</span>
            <h2 class="timeline-header"><b>{{historyElem.messsage}}</b></h2>
            <div class="timeline-body" ng-if="historyElem.comment">
                {{historyElem.comment}}
            </div>
        </div>
    </li>
</ul>
</div>