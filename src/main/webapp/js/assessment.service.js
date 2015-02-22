'use strict';
angular.module('assessmentsApp')
    .factory('Assessment', Assessment);

Assessment.$inject = ['$resource'];

function Assessment($resource){
        return $resource('service/assessment/:assessmentId', {}, {
            query: {method:'GET', isArray:true},
            get: {method:'GET', params:{assessmentId:'@assessmentId'}},
            remove: {method:'DELETE', params:{assessmentId:'@assessmentId'}},
            update: {method:'PUT', headers: {'Content-Type': 'application/json'}}
        });
}
