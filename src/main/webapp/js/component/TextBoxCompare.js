'use strict';
app.directive('textboxcompare', [ function() {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "sourceModel" : "=source",
    "targetModel" : "=target",
    "label" : "=label",
    "ngDisabled": "=disable",
    "required": "=require"
  };

  directive.templateUrl = "js/component/TextBoxCompareTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    if (attr.size)
      $scope.size = 'col-md-' + attr.size + ' col-sm-6 col-xs-12';
    else
     $scope.size = 'col-md-4 col-sm-6 col-xs-12';
    
    if (attr.labelsize)
      $scope.labelSize = 'input-group-addon label-mine col-fix-'
              + attr.labelsize;
    else
      $scope.labelSize = 'input-group-addon label-mine col-fix-label';
  };
  return directive;
} ]);