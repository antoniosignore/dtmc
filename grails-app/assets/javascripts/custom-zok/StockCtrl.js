'use strict';

function StockCtrl(DAO, $rootScope, $scope, $filter, ngTableParams) {

//    if ($rootScope.appConfig) {
//        if (!$rootScope.appConfig.token != '') {
//            window.location.href = "#/login"
//        }
//    }

    $rootScope.flags = {save: false};
    $rootScope.errors = {loadingSite: false, showErrors: false, showServerError: false, errorMessages: []};
    $rootScope.errorValidation = function () {
        $rootScope.errors = {loadingSite: true};
    };

    if (!$rootScope.stock) {
        $rootScope.filter = ""
        $rootScope.stocks = [];
        $rootScope.stock = {};
    }

    $scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10,           // count per page
        sorting: {
            id: 'desc' // initial sorting
        }
    }, {
        getData: function ($defer, params) {
            DAO.query({appName: $scope.appConfig.appName, token: $scope.appConfig.token, controller: 'stock', action: 'list'},
                $scope.loadingSite = true,
                function (result) {
                    $scope.stocks = result;
                    var putIt = params.sorting() ? $filter('orderBy')($scope.stocks, params.orderBy()) : id;
                    putIt = params.filter ? $filter('filter')(putIt, params.filter()) : putIt;
                    params.total(putIt.length);
                    $defer.resolve(putIt.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                    $scope.stocks = (putIt.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                    $scope.loadingSite = false;
                },
                function (error) {
                    $scope.errors.showErrors = true;
                    $scope.errors.showServerError = true;
                    $scope.errors.errorMessages.push('' + error.status + ' ' + error.data);
                    $scope.loadingSite = false;
                });
        }
    });

    //Required for dependency lookup
    $rootScope.getAllStock = function () {
        //get all
        $rootScope.errors.errorMessages = [];
        DAO.query({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, controller: 'stock', action: 'list'},
            $rootScope.loadingSite = true,
            function (result) {
                $rootScope.stocks = result;
                $rootScope.loadingSite = false;

            },
            function (error) {
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    };


    $rootScope.newStock = function () {
        $rootScope.loadingSite = true;
        $rootScope.stock = {};
        $rootScope.loadingSite = false;
        window.location.href = "#/stock/create"
    }

    $rootScope.manualSaveStock = function () {
        $rootScope.loadingSite = true;
        $rootScope.flags.save = false;
        if ($rootScope.stock.id == undefined) {
            $rootScope.saveStock();
        }
        else {
            $rootScope.updateStock();
        }
    }

    $rootScope.saveStock = function () {
        $rootScope.errors.errorMessages = [];
        DAO.save({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, instance: $rootScope.stock, controller: 'stock', action: 'save'},
            function (result) {
                $rootScope.stock = result;
                $rootScope.flags.save = true;
                $rootScope.loadingSite = false;

            },
            function (error) {
                $rootScope.flags.save = false;
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    }

    $rootScope.updateStock = function () {
        $rootScope.errors.errorMessages = [];
        DAO.update({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, instance: $rootScope.stock, controller: 'stock', action: 'update'},
            $rootScope.loadingSite = true,
            function (result) {
                $rootScope.stocks = result;
                $rootScope.flags.save = true;
                $rootScope.loadingSite = false;
                window.location.href = "#/stock/list"
            },
            function (error) {
                $rootScope.flags.save = false;
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    }

    $rootScope.editStock = function (stock) {
        $rootScope.errors.errorMessages = [];
        DAO.get({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, instance: $rootScope.stock, id: stock.id, controller: 'stock', action: 'show'},
            $rootScope.loadingSite = true,
            function (result) {
                $rootScope.stock = result;
                $rootScope.flags.save = true;
                $rootScope.loadingSite = false;
                window.location.href = "#/stock/edit"
            },
            function (error) {
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('Error: ' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    }

    $rootScope.confirmDeleteStock = function () {
        $rootScope.errors.errorMessages = [];
        DAO.delete({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, instance: $rootScope.stock, id: $rootScope.stock.id, controller: 'stock', action: 'delete'},
            $rootScope.loadingSite = true,
            function (result) {
                //$rootScope.stocks = result;
                $rootScope.flags.save = true;
                $rootScope.loadingSite = false;
                window.location.href = "#/stock/list"
            },
            function (error) {
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    }

    $rootScope.showStock = function (stock) {
        $rootScope.errors.errorMessages = [];
        DAO.get({appName: $rootScope.appConfig.appName, token: $rootScope.appConfig.token, id: stock.id, controller: 'stock', action: 'show'},
            $rootScope.loadingSite = true,
            function (result) {
                $rootScope.stock = result;
                $rootScope.flags.save = true;
                $rootScope.loadingSite = false;
                window.location.href = "#/stock/show"
            },
            function (error) {
                $rootScope.errors.showErrors = true;
                $rootScope.errors.showServerError = true;
                $rootScope.errors.errorMessages.push('Error: ' + error.status + ' ' + error.data);
                $rootScope.loadingSite = false;
            });
    }

    $rootScope.candle = function (stock) {

        var ohlc = [
            ['06/15/2009 16:00:00', 136.01, 139.5, 134.53, 139.48],
            ['06/08/2009 16:00:00', 143.82, 144.56, 136.04, 136.97],
            ['06/01/2009 16:00:00', 136.47, 146.4, 136, 144.67],
            ['05/26/2009 16:00:00', 124.76, 135.9, 124.55, 135.81],
            ['05/18/2009 16:00:00', 123.73, 129.31, 121.57, 122.5],
            ['05/11/2009 16:00:00', 127.37, 130.96, 119.38, 122.42],
            ['05/04/2009 16:00:00', 128.24, 133.5, 126.26, 129.19],
            ['04/27/2009 16:00:00', 122.9, 127.95, 122.66, 127.24],
            ['04/20/2009 16:00:00', 121.73, 127.2, 118.6, 123.9],
            ['04/13/2009 16:00:00', 120.01, 124.25, 115.76, 123.42],
            ['04/06/2009 16:00:00', 114.94, 120, 113.28, 119.57],
            ['03/30/2009 16:00:00', 104.51, 116.13, 102.61, 115.99],
            ['03/23/2009 16:00:00', 102.71, 109.98, 101.75, 106.85],
            ['03/16/2009 16:00:00', 96.53, 103.48, 94.18, 101.59],
            ['03/09/2009 16:00:00', 84.18, 97.2, 82.57, 95.93],
            ['03/02/2009 16:00:00', 88.12, 92.77, 82.33, 85.3],
            ['02/23/2009 16:00:00', 91.65, 92.92, 86.51, 89.31],
            ['02/17/2009 16:00:00', 96.87, 97.04, 89, 91.2],
            ['02/09/2009 16:00:00', 100, 103, 95.77, 99.16],
            ['02/02/2009 16:00:00', 89.1, 100, 88.9, 99.72],
            ['01/26/2009 16:00:00', 88.86, 95, 88.3, 90.13],
            ['01/20/2009 16:00:00', 81.93, 90, 78.2, 88.36],
            ['01/12/2009 16:00:00', 90.46, 90.99, 80.05, 82.33],
            ['01/05/2009 16:00:00', 93.17, 97.17, 90.04, 90.58]
        ];

         var data = $rootScope.stock.ohlc;
        console.log("data:" + data);

        console.log("ohlc:" + ohlc);

        var plot2 = $.jqplot('chart',[ohlc],{
            seriesDefaults:{yaxis:'y2axis'},
            axes: {
                xaxis: {
                    renderer:$.jqplot.DateAxisRenderer,
                    tickOptions:{formatString:'%b %e'},
                    min: "09-01-2008",
                    max: "06-22-2009",
                    tickInterval: "6 weeks",
                },
                y2axis: {
                    tickOptions:{formatString:'$%d'}
                }
            },
            // To make a candle stick chart, set the "candleStick" option to true.
            series: [
                {
                    renderer:$.jqplot.OHLCRenderer,
                    rendererOptions:{ candleStick:true }
                }
            ],
            highlighter: {
                show: true,
                showMarker:false,
                tooltipAxes: 'xy',
                yvalues: 4,
                formatString:'<table class="jqplot-highlighter"> \
      <tr><td>date:</td><td>%s</td></tr> \
      <tr><td>open:</td><td>%s</td></tr> \
      <tr><td>hi:</td><td>%s</td></tr> \
      <tr><td>low:</td><td>%s</td></tr> \
      <tr><td>close:</td><td>%s</td></tr></table>'
            }
        });

//        var plot2 = $.jqplot('chart', [ohlc], {
//            seriesDefaults: {yaxis: 'yaxis'},
//            axes: {
//                xaxis: {
//                    renderer: $.jqplot.DateAxisRenderer,
//                    tickOptions: {formatString: '%b %e'}
//                },
//                yaxis: {
//                    tickOptions: {formatString: '%d'}
//                }
//            },
//            // To make a candle stick chart, set the "candleStick" option to true.
//            series: [
//                {
//                    renderer: $.jqplot.OHLCRenderer,
//                    rendererOptions: {
//                        candleStick: true,
//                        upBodyColor: 'black',
//                        downBodyColor: 'black',
//                        fillUpBody: false,
//                        fillDownBody: true
//                    },
//                    highlighter: {
//                        showMarker: false,
//                        pointLabels: { show: true, location: 's', ypadding: 3 },
//                        tooltipAxes: 'xy',
//                        yvalues: 4,
//                        formatString: '<table class="jqplot-highlighter"> \
//                  <tr><td>date:</td><td>%s</td></tr> \
//                  <tr><td>open:</td><td>%s</td></tr> \
//                  <tr><td>hi:</td><td>%s</td></tr> \
//                  <tr><td>low:</td><td>%s</td></tr> \
//                  <tr><td>close:</td><td>%s</td></tr>\
//              </table>'
//                    }
//                },
//                {
//                    showLine: false,
//                    markerOptions: { size: 10, style: "diamonds", color: 'blue' }
//                }
//            ],
//            highlighter: {
//                show: true
//            },
//            cursor: {
//                show: true
//            },
//            grid: {
//                borderColor: 'transparent',
//                shadow: false,
//                drawBorder: false
//            }
//        });

    }



}
