'use strict';
app.directive('fileupload', ['Labels' , function(Labels) {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "ngModel": "=model",
    "ngData" : "=data",
    "label": "=label",
    "required": "=require",
    "tab": "@tab",
  };

  directive.templateUrl = "/js/component/FileUploadTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
	  $scope.Labels = Labels;
	  
	  $scope.test = {};
	  
	  $scope.fileChooserName = attr.model;
    
    
	  $scope.chooseFile = function (){
      debugger
		  document.getElementById($scope.fileChooserName).click();
	  }
	  
	  $scope.deleteFile = function (){
		  $scope.ngModel = {};
		  $scope.test = {};
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