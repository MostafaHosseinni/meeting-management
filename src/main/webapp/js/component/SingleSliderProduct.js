'use strict';
app.directive('singlesliderproduct', [function() {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "item": "=item",
  };

  directive.templateUrl = "/js/component/SingleSliderProductTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
  }
  return directive;
}]);