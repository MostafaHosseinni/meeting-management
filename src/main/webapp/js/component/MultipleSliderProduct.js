'use strict';
app.directive('multiplesliderproduct', [function() {
  var directive = {};
  directive.restrict = 'E';
  directive.scope = {
    "item": "=item",
  };

  directive.templateUrl = "/js/component/MultipleSliderProductTemplate.html";

  directive.link = function($scope, elem, attr, ctrl) {
    
  }
  return directive;
}]);