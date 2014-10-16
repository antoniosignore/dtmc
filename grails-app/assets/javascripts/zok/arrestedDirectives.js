// Password matching directive
zok.directive('passwordMatch', [function () {
    return {
        restrict: 'A',
        scope: true,
        require: 'ngModel',
        link: function (scope, elem, attrs, control) {
            var checker = function () {
                //get the value of the first password
                var e1 = scope.$eval(attrs.ngModel);
                //get the value of the other password
                var e2 = scope.$eval(attrs.passwordMatch);
                return e1 == e2;
            };
            scope.$watch(checker, function (n) {
                //set the form control to valid if both
                //passwords are the same, else invalid
                control.$setValidity('unique', n);
            });
        }
    };
}]);

//uniqueUsername directive
zok.directive('uniqueUsername', ["$http", function ($http) {
    return{
        require: 'ngModel',
        link: function (scope, element, attrs, ctrl) {
            element.bind('blur', function (e) {
                if (!ctrl || !element.val()) return;
                var currentValue = element.val();
                $http.put('auth/lookup', {username: currentValue}).success(function (res) {
                    ctrl.$setValidity('uniquser', true);
                }).error(function (res) {
                    ctrl.$setValidity('uniquser', false);
                });
            });
        }
    };
}]);

zok.directive('loadingContainer', function () {
    return {
        restrict: 'A',
        scope: false,
        link: function (scope, element, attrs) {
            var loadingLayer = angular.element('<div class="loading"></div>');
            element.append(loadingLayer);
            element.addClass('loading-container');
            scope.$watch(attrs.loadingContainer, function (value) {
                loadingLayer.toggleClass('ng-hide', !value);
            });
        }
    };
});

//zok.controller('MainController', function($scope) {
//    $scope.userId = 123;
//    $scope.ohlc = 123;
//});


//function candle(scope) {
//
//    var oo= scope.$root.stock.ohlc;
//    alert("-------------> " + oo);
//
//    $(document).ready(function(){
//        var plot2 = $.jqplot('chart',[oo],{
//            seriesDefaults:{yaxis:'y2axis'},
//            axes: {
//                xaxis: {
//                    renderer:$.jqplot.DateAxisRenderer,
//                    tickOptions:{formatString:'%b %e'},
//                    tickInterval: "6 weeks"
//                },
//                y2axis: {
//                    tickOptions:{formatString:'$%d'}
//                }
//            },
//            // To make a candle stick chart, set the "candleStick" option to true.
//            series: [
//                {
//                    renderer:$.jqplot.OHLCRenderer,
//                    rendererOptions:{ candleStick:true }
//                }
//            ],
//            highlighter: {
//                show: true,
//                showMarker:false,
//                tooltipAxes: 'xy',
//                yvalues: 4,
//                formatString:'<table class="jqplot-highlighter"> \
//      <tr><td>date:</td><td>%s</td></tr> \
//      <tr><td>open:</td><td>%s</td></tr> \
//      <tr><td>hi:</td><td>%s</td></tr> \
//      <tr><td>low:</td><td>%s</td></tr> \
//      <tr><td>close:</td><td>%s</td></tr></table>'
//            }
//        });
//
//    });
//
//    oo = [
//        ['06/15/2009 16:00:00', 136.01, 139.5, 134.53, 139.48],
//        ['06/08/2009 16:00:00', 143.82, 144.56, 136.04, 136.97],
//        ['06/01/2009 16:00:00', 136.47, 146.4, 136, 144.67],
//        ['05/26/2009 16:00:00', 124.76, 135.9, 124.55, 135.81],
//        ['05/18/2009 16:00:00', 123.73, 129.31, 121.57, 122.5],
//        ['05/11/2009 16:00:00', 127.37, 130.96, 119.38, 122.42],
//        ['05/04/2009 16:00:00', 128.24, 133.5, 126.26, 129.19],
//        ['04/27/2009 16:00:00', 122.9, 127.95, 122.66, 127.24],
//        ['04/20/2009 16:00:00', 121.73, 127.2, 118.6, 123.9],
//        ['04/13/2009 16:00:00', 120.01, 124.25, 115.76, 123.42],
//        ['04/06/2009 16:00:00', 114.94, 120, 113.28, 119.57],
//        ['03/30/2009 16:00:00', 104.51, 116.13, 102.61, 115.99],
//        ['03/23/2009 16:00:00', 102.71, 109.98, 101.75, 106.85],
//        ['03/16/2009 16:00:00', 96.53, 103.48, 94.18, 101.59],
//        ['03/09/2009 16:00:00', 84.18, 97.2, 82.57, 95.93],
//        ['03/02/2009 16:00:00', 88.12, 92.77, 82.33, 85.3],
//        ['02/23/2009 16:00:00', 91.65, 92.92, 86.51, 89.31],
//        ['02/17/2009 16:00:00', 96.87, 97.04, 89, 91.2],
//        ['02/09/2009 16:00:00', 100, 103, 95.77, 99.16],
//        ['02/02/2009 16:00:00', 89.1, 100, 88.9, 99.72],
//        ['01/26/2009 16:00:00', 88.86, 95, 88.3, 90.13],
//        ['01/20/2009 16:00:00', 81.93, 90, 78.2, 88.36],
//        ['01/12/2009 16:00:00', 90.46, 90.99, 80.05, 82.33],
//        ['01/05/2009 16:00:00', 93.17, 97.17, 90.04, 90.58],
//        ['12/29/2008 16:00:00', 86.52, 91.04, 84.72, 90.75],
//        ['12/22/2008 16:00:00', 90.02, 90.03, 84.55, 85.81],
//        ['12/15/2008 16:00:00', 95.99, 96.48, 88.02, 90],
//        ['12/08/2008 16:00:00', 97.28, 103.6, 92.53, 98.27],
//        ['12/01/2008 16:00:00', 91.3, 96.23, 86.5, 94],
//        ['11/24/2008 16:00:00', 85.21, 95.25, 84.84, 92.67],
//        ['11/17/2008 16:00:00', 88.48, 91.58, 79.14, 82.58],
//        ['11/10/2008 16:00:00', 100.17, 100.4, 86.02, 90.24],
//        ['11/03/2008 16:00:00', 105.93, 111.79, 95.72, 98.24],
//        ['10/27/2008 16:00:00', 95.07, 112.19, 91.86, 107.59],
//        ['10/20/2008 16:00:00', 99.78, 101.25, 90.11, 96.38],
//        ['10/13/2008 16:00:00', 104.55, 116.4, 85.89, 97.4],
//        ['10/06/2008 16:00:00', 91.96, 101.5, 85, 96.8],
//        ['09/29/2008 16:00:00', 119.62, 119.68, 94.65, 97.07],
//        ['09/22/2008 16:00:00', 139.94, 140.25, 123, 128.24],
//        ['09/15/2008 16:00:00', 142.03, 147.69, 120.68, 140.91]
//    ];
//}
//
//zok.directive('candlestickChart', function(){
//    return {
//        restrict: 'E',
//        require: 'ngModel',
//        link: function(scope, elem, attr, ctrl) {
//            var scr = document.createElement('script');
//            var text = document.createTextNode(candle(scope));
//            scr.appendChild(text);
//            elem.append(scr);
//        },
//        scope: true
//    }
//})

angular.module('ui.chart', [])
    .directive('uiChart', function () {
        return {
            restrict: 'EACM',
            template: '<div></div>',
            replace: true,
            link: function (scope, elem, attrs) {
                var renderChart = function () {
                    var data = scope.$eval(attrs.uiChart);
                    elem.html('');
                    if (!angular.isArray(data)) {
                        return;
                    }

                    var opts = {};
                    if (!angular.isUndefined(attrs.chartOptions)) {
                        opts = scope.$eval(attrs.chartOptions);
                        if (!angular.isObject(opts)) {
                            throw 'Invalid ui.chart options attribute';
                        }
                    }

                    elem.jqplot(data, opts);
                };

                scope.$watch(attrs.uiChart, function () {
                    renderChart();
                }, true);

                scope.$watch(attrs.chartOptions, function () {
                    renderChart();
                });
            }
        };
    });
