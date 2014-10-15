'use strict';
angular.module('services', ['ngResource', 'ngTable', 'arrestedServices']).
    factory('DAO', function ($resource) {
        return $resource('/:appName/:controller/:action', {
            format: 'json', callback: 'JSON_CALLBACK'}, {
            'get': {method: 'GET', params: {appName: '@appName', controller: '@controller', action: '@action', token: '@token', id: '@id'}, isArray: false, timeout: 20000},
            'query': {method: 'GET', params: {appName: '@appName', controller: '@controller', action: '@action', token: '@token'}, isArray: true, timeout: 20000},
            'save': {method: 'POST', params: {appName: '@appName', controller: '@controller', action: '@action', token: '@token', instance: '@instance', username: '@username', passwordHash: '@passwordHash'}, isArray: false, timeout: 20000},
            'update': {method: 'PUT', params: {appName: '@appName', controller: '@controller', action: '@action', token: '@token', instance: '@instance'}, isArray: false, timeout: 20000},
            'delete': {method: 'DELETE', params: {appName: '@appName', controller: '@controller', action: '@action', token: '@token', id: '@id'}, isArray: false, timeout: 20000}
        });
    });

//angular.module('zok', ['ui.chart'])
//    .value('charting', {
//        pieChartOptions: {
//            seriesDefaults: {
//                renderer: jQuery.jqplot.PieRenderer,
//                rendererOptions: {
//                    showDataLabels: true
//                }
//            },
//            legend: { show:true, location: 'e' }
//        }
//    })
//    .controller('DemoCtrl', function ($scope, charting) {
//        $scope.someData = [[
//            ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],
//            ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
//        ]];
//        $scope.myChartOpts = charting.pieChartOptions;
//    });
