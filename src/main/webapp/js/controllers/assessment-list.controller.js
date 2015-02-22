angular.module('assessmentsApp')
    .controller('AssessmentListCtrl', AssessmentListCtrl);

AssessmentListCtrl.$inject = ['$scope', '$location', 'Assessment'];

function AssessmentListCtrl($scope, $location, Assessment) {
    $scope.assessments = Assessment.query();

    $scope.remove = function(index){
        Assessment.remove({assessmentId : $scope.assessments[index].id});
        $scope.assessments.splice(index, 1);
    };

    $scope.openAssessment = function(id) {
         $location.path( '/assessments/' + id );
    };

    $scope.startAssessment = function(id) {
        $location.path('/process/' + id);
    };
}


