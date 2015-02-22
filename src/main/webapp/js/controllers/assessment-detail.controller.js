angular.module('assessmentsApp')
    .controller('AssessmentDetailCtrl', AssessmentDetailCtrl);

AssessmentDetailCtrl.$inject = ['$scope', '$routeParams', '$location', 'Assessment', 'questionTypes'];

function AssessmentDetailCtrl($scope, $routeParams, $location, Assessment, questionTypes) {
    $scope.newAssessment = function(){
             var newAssessment = {
                "id": null,
                "name": null,
                "questions": [ $scope.newQuestion() ]
             };
             return newAssessment;
    };

    $scope.newQuestion = function() {
            var number = (typeof $scope.assessment != 'undefined') ? $scope.assessment.questions.length + 1 : 1;
            var newQuestion = {
                "text": null,
                "number": number,
                "questionType": "MULTIPLE_CHOICE",
                "answers": [ $scope.newAnswer() ]
            };
            return newQuestion;
    };

    $scope.newAnswer =  function() {
       var newAnswer = {
           "text": null,
           "correct": false
       };
       return newAnswer;
    };
    $scope.submitted = false;
    $scope.assessment = $routeParams.assessmentId == 'new' ? $scope.newAssessment() : Assessment.get({assessmentId: $routeParams.assessmentId});

    $scope.questionTypes = questionTypes;
    $scope.questionTypeMap = {};
    $scope.questionTypeMap[questionTypes.multipleChoice] = "Multiple Choice";
    $scope.questionTypeMap[questionTypes.multipleSelect] = "Multiple Select";
    $scope.questionTypeMap[questionTypes.shortAnswer] = "Short Answer";

    $scope.addQuestion = function() {
        $scope.assessment.questions.push($scope.newQuestion());
    };

    $scope.addAnswer = function(index) {
        $scope.assessment.questions[index].answers.push($scope.newAnswer());
    };

    $scope.removeQuestion = function(index){
        var questions = $scope.assessment.questions;
        questions.splice(index, 1);
        for (var i=0; i < questions.length; i++){
            questions[i].number = i + 1;
        }
    };

    $scope.removeAnswer = function(index, questionNumber){
        $scope.assessment.questions[questionNumber-1].answers.splice(index, 1);
    };

    $scope.changeQuestionType = function(index){
        var question = $scope.assessment.questions[index];
        if (question.questionType == questionTypes.shortAnswer) {
            question.answers.splice(1, question.answers.length);
        }
    };

    $scope.isValidAnswers = function(questionType, answers){
        if (questionType == questionTypes.shortAnswer){
            return true;
        } else if (questionType == questionTypes.multipleChoice){
            return answers.length > 1 && $scope.getNumberOfCorrectAnswers(answers) > 0;
        } else if (questionType == questionTypes.multipleSelect){
            return answers.length > 1 && $scope.getNumberOfCorrectAnswers(answers) == 1;
        }
        return false;
    };

    $scope.getNumberOfCorrectAnswers = function(answers){
        var numberOfCorrectAnswers = 0;
         for(var i=0; i < answers.length; i++){
            if (answers[i].correct){
                numberOfCorrectAnswers++;
            }
         }
        return numberOfCorrectAnswers;
    };

    $scope.save = function(){
        $scope.submitted = true;
        var answersValid = true;
        var questions = $scope.assessment.questions;
        for (var i=0; i < questions.length; i++){
            if (!$scope.isValidAnswers(questions[i].questionType, questions[i].answers)){
                answersValid = false;
                break;
            }
        }
        if ($scope.form.$valid && answersValid){
            Assessment.update($scope.assessment);
            $scope.goBack();
        }
    };

    $scope.goBack = function(){
       $location.path( '/assessments');
    };

}