
<div data-ng-controller="PortfolioCtrl">
    <h1>Portfolio List</h1>

    <div data-ng-show="errors.showErrors" class="red">
        <div ng-repeat="error in errors.errorMessages">
            <strong></strong> <span ng-bind="error"></span>
        </div>
    </div>

    <div>
        <p></p>
        <a class="btn btn-primary btn-primary" data-ng-click="newPortfolio()"><span
                class="glyphicon glyphicon-plus"></span><g:message code="default.new.label" args="['portfolio']"
                                                                   default="New portfolio"/></a>
        <p></p>
    </div>
    <div>
        <div>
            <div>

                <button ng-click="tableParams.reload()" class="btn btn-default">Reload</button>
                <button ng-click="tableParams.sorting({})" class="btn btn-default">Clear sorting</button>

                <div loading-container="tableParams.settings().$loading">
                    <table class="table" ng-table="tableParams" show-filter="true">
                        <tr data-ng-repeat="instance in portfolios" data-ng-click="editPortfolio(instance)">

                            <td data-sortable="'description'" data-title="'description'">
                                {{instance.description}}
                            </td>

                            <td data-sortable="'name'" data-title="'name'">
                                {{instance.name}}
                            </td>

                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<div data-ng-controller="StockCtrl">

    <h1>Stock List</h1>

    <div data-ng-show="errors.showErrors" class="red">
        <div ng-repeat="error in errors.errorMessages">
            <strong></strong> <span ng-bind="error"></span>
        </div>
    </div>

    <div>
        <p></p>
        <a class="btn btn-primary btn-primary" data-ng-click="newStock()"><span
                class="glyphicon glyphicon-plus"></span><g:message code="default.new.label" args="['stock']"
                                                                   default="New stock"/></a>
        <p></p>
    </div>


    <div>
        <div>
            <div>

                <button ng-click="tableParams.reload()" class="btn btn-default">Reload</button>
                <button ng-click="tableParams.sorting({})" class="btn btn-default">Clear sorting</button>

                <div loading-container="tableParams.settings().$loading">
                    <table class="table" ng-table="tableParams" show-filter="true">
                        <tr data-ng-repeat="instance in stocks" data-ng-click="showStock(instance)">
                            <td data-sortable="'name'" data-title="'name'">{{instance.name}}</td>
                            <td data-sortable="'description'" data-title="'description'">{{instance.description}}</td>
                        </tr>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>