'use strict';
app.directive('textbox', [function() {
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

  directive.templateUrl = "/js/component/TextBoxTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
    if (attr.size)
      $scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
    else
     $scope.size = 'col-md-4 col-sm-6 col-xs-12';
    
    if(!attr.type)
      $scope.type = "text";
  }
  return directive;
}]);