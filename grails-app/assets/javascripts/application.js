//= require jquery/jquery
//= require_tree jquery
//= require_tree jquery/plugins
//= require bootstrap/bootstrap
//= require angular/angular
//= require angular-route/angular-route
//= require angular-resource/angular-resource.min
//= require angular-table/ng-table
//= require zok/index
//= require zok/arrestedServices
//= require zok/services
//= require zok/arrestedDirectives
//= require_tree custom-zok
//= require_tree views
//= require_self
//= require_tree timeline
//= require_tree timeplot

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
            $(this).fadeOut();
        });
    })(jQuery);
}
