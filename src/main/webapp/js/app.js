'use strict';
/* App Module */
var assessmentsApp = angular.module('assessmentsApp', [
'ngRoute',
'ngResource'
]);

assessmentsApp.constant('questionTypes', {
        'multipleChoice':'MULTIPLE_CHOICE',
        'multipleSelect':"MULTIPLE_SELECT",
        'shortAnswer':"SHORT_ANSWER"
    }
);

assessmentsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/assessments', {
                templateUrl: 'partials/assessment-list.html',
                controller: 'AssessmentListCtrl'
            }).
            when('/assessments/:assessmentId', {
                templateUrl: 'partials/assessment-detail.html',
                controller: 'AssessmentDetailCtrl'
            }).
            when('/process/:assessmentId', {
                templateUrl: 'partials/assessment-process.html',
                controller: 'AssessmentProcessCtrl'
            }).
            otherwise({
                redirectTo: '/assessments'
            });
}]);