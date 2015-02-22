angular.module('assessmentsApp')
    .controller('AssessmentProcessCtrl', AssessmentProcessCtrl);

AssessmentProcessCtrl.$inject = ['$scope', '$location', '$routeParams', 'Assessment', 'questionTypes'];

function AssessmentProcessCtrl($scope, $location, $routeParams, Assessment, questionTypes) {
     $scope.assessment = Assessment.get({assessmentId: $routeParams.assessmentId});
     $scope.questionTypes = questionTypes;

     $scope.goBack = function(){
        $location.path( '/assessments');
     };

     $scope.initGivenAnswers = function(){
        var givenAnswers = {};
        for (i = 0; i < $scope.assessment.questions.length; i++) {
            givenAnswers[i] = [];
        }
        return givenAnswers;
     };

     $scope.assessment.$promise.then(function() {
         $scope.givenAnswers = $scope.initGivenAnswers();
     });

    $scope.calculateResult = function(){
        $scope.result = 0;
        var questions = $scope.assessment.questions;
        for (var i=0; i < questions.length; i++){
            if ($scope.isCorrectAnswer(questions[i].questionType, questions[i].answers, $scope.givenAnswers[i])){
                $scope.result++;
            }
        }
    };

    $scope.isCorrectAnswer = function(questionType, answers, givenAnswers){
        var correctAnswer = false;
        if (questionType == questionTypes.shortAnswer){
            if(answers[0].text == givenAnswers[0]){
                correctAnswer = true;
            }
        } else if (questionType == questionTypes.multipleSelect){
            for (var i = 0; i < answers.length; i++){
                if(answers[i].correct && answers[i].text == givenAnswers[i]){
                    correctAnswer = true;
                    break;
                }
            }
        } else if (questionType == questionTypes.multipleChoice){
            correctAnswer = true;
            for (var i = 0; i < answers.length; i++){
                if((answers[i].correct && !givenAnswers[i]) || (!answers[i].correct && givenAnswers[i])){
                    correctAnswer = false;
                    break;
                }
            }
        }
        return correctAnswer;
    };


}


