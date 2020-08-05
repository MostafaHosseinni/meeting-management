'use strict';
app.directive('imageupload', ['Labels' , function(Labels) {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "ngModel": "=model",
    "label": "=label",
    "ngDisabled": "=disable",
    "mask": "@mask",
    "required": "=require",
    "tab": "@tab",
    "type" : "@type"
  };

  directive.templateUrl = "/js/component/ImageUploadTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
$scope.Labels = Labels;
	  
	  $scope.fileChooserName = attr.model;
	  
	  $scope.chooseFile = function (){
		  document.getElementById($scope.fileChooserName).click();
	  }
	  
	  $scope.deleteFile = function (){
		  $scope.ngModel = null;
	  }
	  
    if (attr.size)
      $scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
    else
     $scope.size = 'col-md-4 col-sm-6 col-xs-12';
    
    if(!attr.type)
      $scope.type = "text";
  }
  return directive;
}]);